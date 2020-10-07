package com.gang.etl.engine.api.request;

/**
 * @Classname SyncBusinessRequest
 * @Description TODO
 * @Date 2020/10/7 17:29
 * @Created by zengzg
 */
public class SyncBusinessRequest {

    /**
     * 业务 Id
     */
    private String businessCode;

    /**
     * 生产者配置信息
     */
    private String produceSetting;

    /**
     * 消费者配置信息
     */
    private String consumerSetting;


    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getProduceSetting() {
        return produceSetting;
    }

    public void setProduceSetting(String produceSetting) {
        this.produceSetting = produceSetting;
    }

    public String getConsumerSetting() {
        return consumerSetting;
    }

    public void setConsumerSetting(String consumerSetting) {
        this.consumerSetting = consumerSetting;
    }

    @Override
    public String toString() {
        return "SyncBusinessRequest{" +
                "businessCode='" + businessCode + '\'' +
                ", produceSetting='" + produceSetting + '\'' +
                ", consumerSetting='" + consumerSetting + '\'' +
                '}';
    }
}
