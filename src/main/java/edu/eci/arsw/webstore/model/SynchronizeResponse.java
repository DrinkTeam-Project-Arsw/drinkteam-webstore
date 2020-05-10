package edu.eci.arsw.webstore.model;

public class SynchronizeResponse {

    private String userNickname;
    private String function;

    public SynchronizeResponse() {
    }

    public SynchronizeResponse(String userNickname, String function) {
        this.userNickname = userNickname;
        this.function = function;
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
}
