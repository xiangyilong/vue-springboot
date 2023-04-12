package com.example.springboottest.result;

public class Result {
    //响应码
    private int code;
    private boolean previlege;

    public Result(int code) {
        this.code = code;
        this.previlege = false;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setPrevilege(boolean previlege) {
        this.previlege = previlege;
    }

    public boolean isPrevilege() {
        return previlege;
    }
}
