package com.yhs.blog.entity;

/**
 * Created by YangShuang
 * on 2018/3/20.
 */
public class UserLoginEntity extends BaseEntity{

    private String userName;
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
