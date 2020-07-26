package com.gang.etl.git.logic;

import cn.hutool.core.io.FileUtil;
import com.gang.etl.engine.api.exception.SyncException;
import com.gang.etl.git.to.GitConfig;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * @Classname GitOperation
 * @Description TODO
 * @Date 2020/7/26 21:53
 * @Created by zengzg
 */
public class GitOperation {


    /**
     * 拉取远程仓库内容到本地
     */
    public void pullGit(String path) throws IOException, GitAPIException {

        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new
                UsernamePasswordCredentialsProvider("username", "password");
        String localPath = createGitFile();

        //git仓库地址
        Git git = new Git(new FileRepository(localPath + "/.git"));
        git.pull().setRemoteBranchName("master").
                setCredentialsProvider(usernamePasswordCredentialsProvider).call();
    }


    /**
     * 克隆远程库
     *
     * @throws IOException
     * @throws GitAPIException
     */
    public String cloneGit(GitConfig config) throws IOException, GitAPIException {


        //设置远程服务器上的用户名和密码
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new
                UsernamePasswordCredentialsProvider("username", "password");
        String localPath = createGitFile();

        if (FileUtil.isDirectory(localPath + ".git")) {
            Git git = new Git(new FileRepository(localPath + "/.git"));
            git.pull().call();
        } else {
            //克隆代码库命令
            CloneCommand cloneCommand = Git.cloneRepository();

            Git git = cloneCommand.setURI(config.getGitPath()) //设置远程URI
                    .setBranch("master") //设置clone下来的分支
                    .setDirectory(new File(localPath)) //设置下载存放路径
                    .setCredentialsProvider(usernamePasswordCredentialsProvider) //设置权限验证
                    .call();
        }
        return localPath;

    }

    /**
     * 创建 Git File 文件
     */
    public static String createGitFile() {
        try {
            String path = getProgramPath();
            String fileSeparator = System.getProperty("file.separator");
            String newDir = path + fileSeparator + "blogDoc" + fileSeparator;
            FileUtil.mkdir(newDir);
            return newDir;
        } catch (Exception e) {
            throw new SyncException("Create Git File Error");
        }
    }

    /**
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getProgramPath() throws UnsupportedEncodingException {
        URL url = GitOperation.class.getProtectionDomain().getCodeSource().getLocation();
        String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
        String parentPath = new File(jarPath).getParentFile().getPath();
        return parentPath;
    }
}
