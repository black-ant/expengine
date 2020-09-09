//CHECKSTYLE:OFF
package com.gang.etl.ldap.logic;

import com.para.commons.core.error.CommonErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import para.sdk.common.sync.exception.SyncException;
import para.sdk.sync.ldap.to.ExchangeGlobalBookTO;
import para.sdk.sync.ldap.to.ExchangeTO;
import para.sdk.sync.ldap.to.config.MailSystemConfig;

import javax.net.SocketFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Classname ExchangeConnect
 * @Description TODO
 * @Date 2020/2/28 18:57
 * @Created by zengzg
 */
@Component
public class ExchangeConnect {


    private static Logger LOG = LoggerFactory.getLogger(ExchangeConnect.class);

    private static final String CHARSETNAME = "UTF-8";
    private static final Integer NETWORK_PORT = 8885;
    private final static Integer MAX_POOL_CONNECT = 5;
    private static final Integer NETWORK_TIMEOUT = 30000;

    private static Map<Integer, ExchangeConnect> connectMap = new HashMap<>();


    private ExchangeOperationSource operationSource = new ExchangeOperationSource();
    private ExchangeAddressBookSource globeSource = new ExchangeAddressBookSource();

    private Socket socket = null;
    private MailSystemConfig config;

    public static ExchangeConnect build(MailSystemConfig config) {
        ExchangeConnect connect = null;

        //        Integer hashKey = getKeyHash(config);
        //        Boolean needRefush = Boolean.TRUE;
        //        if (!connectMap.isEmpty() && connectMap.containsKey(hashKey)) {
        //            connect = connectMap.get(hashKey);
        //            needRefush = connect.getSocket().isConnected();
        //        }
        //
        //        if (needRefush) {
        //TODO : Socket 无需连接池  ,会导致超时情况
        try {
            connect = new ExchangeConnect();
            connect.setConfig(config);
            connect.setSocket(connect.createServerSocket(config.getHost(), config.getPort()));

            //            connectMap.put(hashKey, connect);
        } catch (Exception e) {
            LOG.error("E----> 创建连接异常 :" + config.getHost());
            throw new SyncException("Exchange Socket Create Error");
        }
        //        }

        if (connect == null || !connect.getSocket().isConnected()) {
            throw new SyncException(CommonErrorEnum.INIT_ERROR, "Exchnage Socker init error");
        }

        return connect;
    }


    /**
     * 获取函数Hash值
     *
     * @return
     */
    public static int getKeyHash(MailSystemConfig config) {
        String hashKey = config.getHost() + config.getPort();
        int hash = 0;

        for (int i = 0; i < hashKey.length(); i++) {
            hash ^= (hash << 5) + (int) hashKey.charAt(i) + (hash >> 2);
        }
        hash += new Random().nextInt(MAX_POOL_CONNECT);
        return hash & 0x7FFFFFFF;
    }


    /**
     * 启用邮箱
     *
     * @param exchangeTO
     * @return
     */
    public String doEnableMail(ExchangeTO exchangeTO) {
        return operationSource.enableMailBox(this, exchangeTO);
    }

    /**
     * 禁用邮箱
     *
     * @param exchangeTO
     * @return
     */
    public String doDisableMail(ExchangeTO exchangeTO) {
        return operationSource.disableMailBox(this, exchangeTO);
    }


    /**
     * 启用邮箱联系人
     *
     * @param exchangeTO
     * @return
     */
    public String doEnableMailUser(ExchangeTO exchangeTO) {
        return operationSource.enableMailUser(this, exchangeTO);
    }


    /**
     * 禁用邮箱联系人
     *
     * @param exchangeTO
     * @return
     */
    public String doDisableMailUser(ExchangeTO exchangeTO) {
        return operationSource.disableMailUser(this, exchangeTO);
    }

    /**
     * 修改邮箱
     *
     * @param exchangeTO
     * @return
     */
    public String doUpdateMailBox(ExchangeTO exchangeTO) {
        return operationSource.setMailBox(this, exchangeTO);
    }


    /**
     * 修改邮箱用户
     *
     * @param exchangeTO
     * @return
     */
    public String doUpdateMailUser(ExchangeTO exchangeTO) {
        return operationSource.setMailUser(this, exchangeTO);
    }

    /**
     * 修改邮箱用户
     *
     * @param exchangeTO
     * @return
     */
    public String doGetGlonb(ExchangeGlobalBookTO exchangeTO) {
        return globeSource.getGlobalBook(this, exchangeTO);
    }


    /**
     * 创建全球通讯录
     *
     * @param exchangeTO
     * @return
     */
    public String doCreateGlobe(ExchangeGlobalBookTO exchangeTO) {
        return globeSource.createGlobalBook(this, exchangeTO);
    }

    /**
     * 更新全球通讯录
     *
     * @param exchangeTO
     * @return
     */
    public String doUpdateGlobe(ExchangeGlobalBookTO exchangeTO) {
        return globeSource.updateGlobalBook(this, exchangeTO);
    }


    /**
     * 更新全球通讯录
     *
     * @param exchangeTO
     * @return
     */
    public String doDeleteGlobe(ExchangeGlobalBookTO exchangeTO) {
        return globeSource.deleteGlobalBook(this, exchangeTO);
    }

    /**
     * 建立 Server Socket 连接
     *
     * @param host
     * @param port
     * @return
     * @throws UnknownHostException
     * @throws IOException
     */
    private Socket createServerSocket(String host, int port) throws UnknownHostException, IOException {

        // Step 1 : Get Factory
        SocketFactory factory = SocketFactory.getDefault();

        // Step 2 : Create Socket
        Socket socket = factory.createSocket(host, port);
        socket.setSoTimeout(NETWORK_TIMEOUT);

        return socket;
    }

    /**
     * 对 Exchange Server 进行操作
     *
     * @param str
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public String doOperation(String str) throws IOException, InterruptedException {
        return doOperation(this.socket, str);
    }

    /**
     * 对 Exchange Server 进行操作
     *
     * @param socket
     * @param str
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private String doOperation(Socket socket, String str) throws IOException, InterruptedException {
        // 向本机的52000端口发出客户请求

        // 构建IO
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, CHARSETNAME));
        // 向服务器端发送一条消息
        bw.write(str);
        bw.flush();

        // 读取服务器返回的消息
        BufferedReader br = new BufferedReader(new InputStreamReader(is, CHARSETNAME));
        //Thread.sleep(timeout_network);
        char[] readLine = new char[5];
        br.read(readLine);
        return String.valueOf(readLine).trim();
    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            this.getSocket().close();
        } catch (IOException e) {
            LOG.info("------>  <-------");
        }
    }

    public MailSystemConfig getConfig() {
        return config;
    }

    public String getSimpleConfig() {
        return config.getUsername() + ";" + config.getPassword() + ";" + config.getHost() + ";";
    }

    public void setConfig(MailSystemConfig config) {
        this.config = config;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
