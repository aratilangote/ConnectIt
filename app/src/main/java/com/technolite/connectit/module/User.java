package com.technolite.connectit.module;

public class User {
    String userName;
    String userAddress;
    String userEmail;
    String userMobileNo;
    String userPass;
    String userConPass;

    String userReferalId;

    public User(){

    }

    public User(String userName, String userAddress, String userEmail, String userMobileNo, String userPass, String userConPass, String userReferalId) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.userEmail = userEmail;
        this.userMobileNo = userMobileNo;
        this.userPass = userPass;
        this.userConPass = userConPass;
        this.userReferalId = userReferalId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobileNo() {
        return userMobileNo;
    }

    public void setUserMobileNo(String userMobileNo) {
        this.userMobileNo = userMobileNo;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserConPass() {
        return userConPass;
    }

    public void setUserConPass(String userConPass) {
        this.userConPass = userConPass;
    }

    public String getUserReferalId() {
        return userReferalId;
    }

    public void setUserReferalId(String userReferalId) {
        this.userReferalId = userReferalId;
    }
}
