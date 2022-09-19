/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.dataobject.service;

import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.DataObjectDomainService;
import cn.syq.puffer.business.model.api.ProjectDomainService;
import cn.syq.puffer.business.model.dataobject.api.*;
import cn.syq.puffer.business.model.field.api.FieldMeta;
import cn.syq.puffer.business.utils.StringTools;
import cn.syq.puffer.dao.sql.entity.ModelDo;
import cn.syq.puffer.dao.sql.entity.ModelDoCatalog;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shiyuqin
 * @date 2022/8/5 17:43
 */
@Service
public class DataObjectServiceImpl implements DataObjectService {

    @Autowired
    private DataObjectDomainService dataobjectDomainService;
    
    @Autowired
    private ProjectDomainService projectDomainService;
    
    @Autowired
    private Map<String, DataObjectAppend> dataObjectAppendMap;
    
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
    public DataObjectMeta addDataObject(ModelDo modelDo, String method) {
        
        projectDomainService.checkProjectExist(modelDo.getProjectId());
        dataobjectDomainService.checkDoCatalogExist(modelDo.getCatalogId());
        
        DataObjectAppend dataObjectAppend = dataObjectAppendMap.get(StringTools.firstLetter2LowerCase(DataObjectAppend.class.getSimpleName())+method);
        if (Objects.isNull(dataObjectAppend)) {
            throw new ManagerException(ManagerErrorCode.E30);
        }

        return dataObjectAppend.addDataObject(modelDo);
    }

    @Override
    public List<CatalogMeta> listDataObjects(Long projectId, String doType, boolean includeFields) {

        projectDomainService.checkProjectExist(projectId);
        Set<String> doTypes = dataobjectDomainService.getInnerDoTypes(doType);

        List<DoCatalog> catalogs = dataobjectDomainService.listDataObjects(projectId, doTypes, includeFields);

        return catalogs.stream().map(catalog -> {
            CatalogMeta catalogMeta = new CatalogMeta();
            catalogMeta.setId(catalog.getId());
            catalog.setLabel(catalog.getLabel());

            catalogMeta.setDataObjectMetas(catalog.getDataObjects().stream().map(dataObject -> {
                DataObjectMeta dataObjectMeta = new DataObjectMeta();
                dataObjectMeta.setId(dataObject.getId());
                dataObjectMeta.setName(dataObject.getName());
                dataObjectMeta.setLabel(dataObject.getLabel());
                dataObjectMeta.setType(dataObject.getDoType());
                if (includeFields) {
                    dataObjectMeta.setFieldMetas(Objects.isNull(dataObject.getFields()) ? Lists.newArrayList() :
                            dataObject.getFields().stream().map(field -> {
                                FieldMeta fieldMeta = new FieldMeta();
                                fieldMeta.setId(field.getId());
                                fieldMeta.setName(field.getName());
                                fieldMeta.setLabel(field.getLabel());
                                fieldMeta.setType(field.getClassType());
                                fieldMeta.setListFlag(field.isListFlag());
                                return fieldMeta;
                            }).collect(Collectors.toList()));
                }
                return dataObjectMeta;
            }).collect(Collectors.toList()));

            return catalogMeta;
        }).collect(Collectors.toList());
    }

}
