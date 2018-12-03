package cn.com.hqep.templet.model;

public class tblBbgsUser {
    private String username;
    private String userpassword;
    private String realname;
    private String roletype;

    public tblBbgsUser() {

    }

    public tblBbgsUser(String username, String userpassword, String realname, String roletype) {
        this.username = username;
        this.userpassword = userpassword;
        this.realname = realname;
        this.roletype = roletype;
    }

    @Override
    public String toString() {
        return "tblBbgsUser{" +
                "username='" + username + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", realname='" + realname + '\'' +
                ", roletype='" + roletype + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRoletype() {
        return roletype;
    }

    public void setRoletype(String roletype) {
        this.roletype = roletype;
    }
}
