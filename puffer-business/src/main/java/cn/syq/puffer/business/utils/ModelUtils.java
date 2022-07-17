package cn.syq.puffer.business.utils;

import java.util.UUID;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/17 17:55
 */
public class ModelUtils {

    private ModelUtils() {

    }

    public static String generateModelName() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", "").length());
        System.out.println(UUID.randomUUID());
    }
}
