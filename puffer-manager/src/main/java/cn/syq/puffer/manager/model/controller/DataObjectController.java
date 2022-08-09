/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.manager.model.controller;

import cn.syq.puffer.business.model.dataobject.service.DataobjectService;
import cn.syq.puffer.manager.model.api.ManagerResponse;
import cn.syq.puffer.business.model.dataobject.api.CatalogMeta;
import cn.syq.puffer.manager.model.api.dataobject.CatalogNew;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author shiyuqin
 * @date 2022/8/5 17:30
 */
@RestController
@RequestMapping("/model/dataobject")
public class DataObjectController {
    
    @Autowired
    private DataobjectService dataobjectService;
    
    @PutMapping("/{projectId:\\d+}/dataobject/catalog")
    public ManagerResponse<CatalogMeta> addDataobjectCatalog(@Valid @RequestBody CatalogNew catalogNew){    
        return ManagerResponse.buildSuccess(dataobjectService.addDataobjectCatalog(catalogNew.getProjectId(), catalogNew.getLabel()));
    }
    
    @GetMapping("/{projectId:\\d+}/dataobject/catalog")
    @Valid
    public ManagerResponse<List<CatalogMeta>> listDataobjectCatalogs(@PathVariable("projectId") @Min(1L) Long projectId,
            @RequestParam(required = false) @Length(min = 1, max = 60) String label){
        return ManagerResponse.buildSuccess(dataobjectService.listDataobjectCatalogs(projectId, label));
    }
    
    @GetMapping("/{projectId:\\d+}/dataobject/catalog/{doCataId:\\d+}/meta")
    @Valid
    public ManagerResponse<CatalogMeta> getCatalogMeta(@PathVariable("projectId") @Min(1L) Long projectId, @PathVariable("doCataId") @Min(1L) Long doCataId){
        return ManagerResponse.buildSuccess(dataobjectService.getDataobjectCatalog(projectId, doCataId));
    }
}
