package com.gang.etl.git.to;

/**
 * @Classname GitConfig
 * @Description TODO
 * @Date 2020/7/3 12:46
 * @Created by zengzg
 */
public class GitConfig {

    private String gitPath;

    private String account;

    private String password;

    private String writePath;

    public String getGitPath() {
        return gitPath;
    }

    public void setGitPath(String gitPath) {
        this.gitPath = gitPath;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWritePath() {
        return writePath;
    }

    public void setWritePath(String writePath) {
        this.writePath = writePath;
    }
}
