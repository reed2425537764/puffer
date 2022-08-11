/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.dataobject.api;

import lombok.Data;



/**
 *
 * @author shiyuqin
 * @date 2022/8/9 17:31
 */
@Data
public class DataObjectMeta {
    
    private Long id;

    private Long projectId;
    
    private Long catalogId;

    private String catalogLabel;
    
    private String name;

    private String label;
    
    private String type;

    private String description;
    
    private Long version;
    
    //private List<FieldMeta> fieldMetas;
}
