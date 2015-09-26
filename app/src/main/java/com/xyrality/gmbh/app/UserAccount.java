package com.xyrality.gmbh.app;

/**
 * Created by yarik on 26.09.15.
 */
public class UserAccount {

    private static UserAccount instance;

    private String login;

    private String password;

    private UserDevice device;

    public static UserAccount getInstance() {
        if (instance == null) {
            instance = new UserAccount();
        }

        return instance;
    }

    public static void setInstance(UserAccount instance) {
        UserAccount.instance = instance;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDevice getDevice() {
        return device;
    }

    public void setDevice(UserDevice device) {
        this.device = device;
    }
}
