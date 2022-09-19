/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.utils;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author shiyuqin
 * @date 2022/8/15 16:44
 */
public class StringTools extends StringUtils{
    
    public static String firstLetter2LowerCase(String str){
        if (isNotBlank(str)) {
            char[] charArr = str.toCharArray();
            if (Character.isUpperCase(charArr[0])) {
                charArr[0] += 32;
                return String.valueOf(charArr);
            }
        } 
        return str;
    }
}
