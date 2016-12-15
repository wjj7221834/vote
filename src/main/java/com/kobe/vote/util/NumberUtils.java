package com.kobe.vote.util;

/**
 * @author: kobe.wu
 * @since : 15-1-10
 */
public class NumberUtils {

    public static int parseQuitely(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }
}
