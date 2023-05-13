package ru.javadiploma.restaurantvoting.util;

public class SecurityUtil {

    private static int id = 1;

    private SecurityUtil() {
    }

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

}