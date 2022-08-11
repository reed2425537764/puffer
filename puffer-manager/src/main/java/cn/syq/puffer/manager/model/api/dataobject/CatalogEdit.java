/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.manager.model.api.dataobject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author shiyuqin
 * @date 2022/8/9 16:35
 */
@Data
public class CatalogEdit {
    
    @NotNull
    @Min(1L)
    private Long id;
    
    @NotNull
    @Min(1L)
    private Long projectId;

    @NotBlank
    private String label;
}
