/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.dataobject.api;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Data;

/**
 *
 * @author shiyuqin
 * @date 2022/8/15 17:21
 */
@Data
public class DoCatalog {
    
    private Long id;

    private String name;

    private String label;
    
    private List<DataObject> dataObjects = Lists.newArrayList();
}
