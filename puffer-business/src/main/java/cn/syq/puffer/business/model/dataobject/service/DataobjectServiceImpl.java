/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.dataobject.service;

import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.DataobjectDomainService;
import cn.syq.puffer.business.model.api.ProjectDomainService;
import cn.syq.puffer.business.model.dataobject.api.CatalogMeta;
import cn.syq.puffer.business.model.dataobject.api.DataObjectMeta;
import cn.syq.puffer.dao.sql.entity.ModelDo;
import cn.syq.puffer.dao.sql.entity.ModelDoCatalog;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author shiyuqin
 * @date 2022/8/5 17:43
 */
public class DataobjectServiceImpl implements DataobjectService{

    @Autowired
    private DataobjectDomainService dataobjectDomainService;
    
    @Autowired
    private ProjectDomainService projectDomainService;
    
    @Override
    public CatalogMeta addDataobjectCatalog(Long projectId, String label) {
        CatalogMeta catalogMeta = new CatalogMeta();
        
        projectDomainService.checkProjectExist(projectId);
        dataobjectDomainService.checkDoCatalogExist(projectId, label);
        ModelDoCatalog modelDoCatalog = dataobjectDomainService.addDataobjectCatalog(projectId, label);
        
        catalogMeta.setId(modelDoCatalog.getId());
        catalogMeta.setLabel(label);
        catalogMeta.setProjectId(projectId);
        catalogMeta.setName(modelDoCatalog.getName());
        return catalogMeta;
    }

    @Override
    public List<CatalogMeta> listDataobjectCatalogs(Long projectId, String label) {
        ArrayList<CatalogMeta> list = Lists.newArrayList();
        
        projectDomainService.checkProjectExist(projectId);
        
        List<ModelDoCatalog> modelDoCatalogs = dataobjectDomainService.listByProjectId(projectId, Optional.of(label));
        
        modelDoCatalogs.forEach(modelDoCatalog -> {
            CatalogMeta catalogMeta = new CatalogMeta();
            catalogMeta.setId(modelDoCatalog.getId());
            catalogMeta.setLabel(modelDoCatalog.getLabel());
            list.add(catalogMeta);
        });
        
        return list;
    }

    @Override
    public CatalogMeta getDataobjectCatalog(Long projectId, Long doCataId) {
        
        projectDomainService.checkProjectExist(projectId);
        
        ModelDoCatalog modelDoCatalog = dataobjectDomainService.queryById(doCataId);
        
        if (!Objects.equals(projectId, modelDoCatalog.getProjectId())) {
            throw new ManagerException(ManagerErrorCode.E20);
        }
        
        return dataobjectDomainService.buildCatalogMeta(modelDoCatalog);
    }

    @Override
    public CatalogMeta editCatalogMeta(Long projectId, Long doCataId, String label) {
        
        projectDomainService.checkProjectExist(projectId);
        ModelDoCatalog modelDoCatalog = dataobjectDomainService.checkDoCatalogExist(doCataId);
        
        if (Objects.equals(label, modelDoCatalog.getLabel())) {
            return dataobjectDomainService.buildCatalogMeta(modelDoCatalog);
        }
        
        if (Objects.nonNull(dataobjectDomainService.queryByLabelAndProjectId(projectId, label))) {
            throw new ManagerException(ManagerErrorCode.E30);
        }
        
        dataobjectDomainService.updateCatalog(doCataId, label);
        modelDoCatalog.setLabel(label);
        
        return dataobjectDomainService.buildCatalogMeta(modelDoCatalog);
    }

    @Override
    public DataObjectMeta addDataObject(ModelDo modelDo) {
        
        projectDomainService.checkProjectExist(modelDo.getProjectId());
        dataobjectDomainService.checkDoCatalogExist(modelDo.getCatalogId());
        
        
        return dataobjectDomainService.builDataObjectMeta(modelDo);
    }
    
}
