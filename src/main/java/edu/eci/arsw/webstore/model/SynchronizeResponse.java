package edu.eci.arsw.webstore.model;

public class SynchronizeResponse {

    private String userNickname;
    private String function;
    private String seller;

    public SynchronizeResponse() {
    }

    public SynchronizeResponse(String userNickname, String function, String seller) {
        this.userNickname = userNickname;
        this.function = function;
        this.seller = seller;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

}
