package com.sucheng.test;

import com.sucheng.common.HashUtils;
import com.sucheng.enums.HashEncodeEnum;

public class TestMD5 extends BaseTest {

    public static void main(String[] args) {
        System.out.println(HashUtils.md5("123456","sucheng", HashEncodeEnum.HEX));
    }
}
