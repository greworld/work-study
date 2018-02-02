package com.gupaoedu.nazgd;

/**
 * @author 风骚的GRE
 * @Description 用于记录work Server（工作 服务器） 配置信息
 * @date 2018/2/2.
 */
public class ServerConfig {
    private String dbUrl;
    private String dbPwd;
    private String dbUser;

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    @Override
    public String toString() {
        return "ServerConfig [dbUrl=" + dbUrl + ", dbPwd=" + dbPwd + ", dbUser=" + dbUser + "]";
    }
}
