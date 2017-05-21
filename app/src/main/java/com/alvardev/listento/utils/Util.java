package com.alvardev.listento.utils;

/**
 * Created by alvardev on 21/05/17.
 * Utils
 */

public class Util {
    public static String manageThrowable(Throwable t){
        return "onFailure send answers: " + (t != null && t.getMessage() != null ? t.getMessage() : "Throwable is null");
    }
}
