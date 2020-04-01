package com.carl.mvpdemo.module.home.event;

public class LoginEvent {
    public long time;
    public String mobile;
    public boolean force;

    public LoginEvent(String mobile) {
        this.mobile = mobile;
    }

    public LoginEvent(long time, String mobile) {
        this.time = time;
        this.mobile = mobile;
    }

    public LoginEvent(long time, String mobile, boolean force) {
        this.time = time;
        this.mobile = mobile;
        this.force = force;
    }


    @Override
    public String toString() {
        return "LoginEvent{" +
                "time=" + time +
                ", mobile='" + mobile + '\'' +
                ", force='" + force + '\'' +
                '}';
    }
}
