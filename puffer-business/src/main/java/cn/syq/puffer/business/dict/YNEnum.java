/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package cn.syq.puffer.business.dict;

import lombok.Getter;

/**
 *
 * @author shiyuqin
 * @date 2022/7/21 17:30
 */
@Getter
public enum YNEnum {
    
    Y("Y", "Y"),
    
    N("N", "N")
    ;

    private YNEnum() {
        this.name = null;
        this.label = null;
    }
        
    private final String name;
    
    private final String label;
    
    YNEnum(String name, String label) {
        this.name = name;
        this.label = label;
    }
}
