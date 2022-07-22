/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.manager.model.api.project;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author shiyuqin
 * @date 2022/7/21 14:25
 */
@Data
public class ProjectDeployResponse {
    
    private Long deployId;
    
    private String deployDesc;
    
    private Boolean latest;
    
    private Integer deployUid;
    
    private LocalDateTime deployTime;
}
