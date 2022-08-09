/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.dataobject.api;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author shiyuqin
 * @date 2022/8/5 17:47
 */
@Data
public class CatalogMeta {
    
    private Long id;
    
    private String name;
    
    private String label;
    
    private Long projectId;

    private Integer createUid;
    
    private LocalDateTime createTime;
    
    private Integer updateUid;

    private LocalDateTime updateTime;
}
