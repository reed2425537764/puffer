/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.manager.model.api.project;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author shiyuqin
 * @date 2022/7/21 13:24
 */
@Data
public class ProjectEditRequest {
    
    @NotBlank
    private Long id;
    
    @NotBlank
    @Length(max = 60)
    private String label;
    
    @NotBlank
    @Length(max = 60)
    private String description;
}
