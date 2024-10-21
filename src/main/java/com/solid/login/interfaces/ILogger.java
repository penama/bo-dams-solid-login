package com.solid.login.interfaces;

public interface ILogger {

    public void info(Class classs, String msg);

    public void debug(Class classs, String msg);

    public void warn(Class classs, String msg);

    public void error(Class classs, String msg);

}
