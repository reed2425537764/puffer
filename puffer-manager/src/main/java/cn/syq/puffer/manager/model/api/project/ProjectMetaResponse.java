package cn.syq.puffer.manager.model.api.project;

import lombok.Data;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/16 21:44
 */
@Data
public class ProjectMetaResponse {

    private Long id;
    
    private String name;
    
    private String label;

    private String description;
}
