package edu.eci.arsw.webstore.model;

public class SynchronizeResponse {

    private String userNickname;
    private String tableName;

    public SynchronizeResponse() {
    }

    public SynchronizeResponse(String userNickname, String tableName) {
        this.userNickname = userNickname;
        this.tableName = tableName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
