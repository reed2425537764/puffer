/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.manager.model.api.dataobject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author shiyuqin
 * @date 2022/8/9 17:40
 */
@Data
public class DataObjectNew {
    
    @NotNull
    @Min(1L)
    private Long projectId;

    @NotNull
    @Min(1L)
    private Long catalogId;
    
    @NotBlank
    @Pattern(regexp = "[1-2]")
    private String method;
    
    @Size(max = 32)
    private String name;
    
    @Size(max = 60)
    private String label;

    @Pattern(regexp = "[2-5]")
    private String type;

    @Size(max = 90)
    private String description;
    
    private Long templateId;
}
