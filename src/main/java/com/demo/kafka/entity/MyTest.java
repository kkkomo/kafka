package com.demo.kafka.entity;

public class MyTest {

    public static void main(String[] args) {
        String str = "adsf    adS	DFASFSADF阿德斯防守对方asdfsadf37《？：？@%#￥%#￥%@#$%#@$%^><?1234";


        String str1 = str.replaceAll("[^a-zA-Z0-9]", "");
        System.out.println(str1);


        String str2 = str.replaceAll("[^a-zA-Z]", "");
        char[] chars = str2.toCharArray();
        System.out.println(str2);
    }
}
