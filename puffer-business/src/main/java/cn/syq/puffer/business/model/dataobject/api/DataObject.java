/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.dataobject.api;

import cn.syq.puffer.business.model.field.api.Field;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 *
 * @author shiyuqin
 * @date 2022/8/15 17:22
 */
@Data
public class DataObject {
    
    private long id;

    private String name;

    private String label;

    private String description;

    private String doType;
    
    private long hisId;
    
    private long catalogId;
    
    private List<Field> fields = Lists.newArrayList();
}
