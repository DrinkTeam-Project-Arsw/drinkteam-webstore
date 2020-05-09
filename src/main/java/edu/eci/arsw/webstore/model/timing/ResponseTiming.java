package edu.eci.arsw.webstore.model.timing;

public class ResponseTiming {
    private String userNickname;
    private String function;
    private String tableName;

    public ResponseTiming() {
    }

    public ResponseTiming(String userNickname, String function, String tableName) {
        this.userNickname = userNickname;
        this.function = function;
        this.tableName = tableName;
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    
}