/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.api;

import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.dataobject.api.CatalogMeta;
import cn.syq.puffer.business.model.dataobject.api.DataObject;
import cn.syq.puffer.business.model.dataobject.api.DataObjectMeta;
import cn.syq.puffer.business.model.dataobject.api.DoCatalog;
import cn.syq.puffer.dao.sql.entity.ModelDo;
import cn.syq.puffer.dao.sql.entity.ModelDoCatalog;
import cn.syq.puffer.dao.sql.entity.ModelDoHis;
import cn.syq.puffer.dao.sql.entity.ModelField;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 *
 * @author shiyuqin
 * @date 2022/8/5 17:40
 */
public interface DataObjectDomainService {
    
    ModelDoCatalog queryByLabelAndProjectId(Long projectId, String label);
    
    ModelDoCatalog checkDoCatalogExist(Long projectId, String label);
    
    ModelDoCatalog checkDoCatalogExist(Long doCataId);
    
    ModelDoCatalog addDataobjectCatalog(Long projectId, String label);
    
    List<ModelDoCatalog> listByProjectId(Long projectId, Optional<String> labelOpt);
    
    ModelDoCatalog queryById(Long doCataId);
    
    CatalogMeta buildCatalogMeta(ModelDoCatalog modelDoCatalog);
    
    int updateCatalog(Long doCataId, String label);
    
    DataObjectMeta builDataObjectMeta(ModelDo modelDo);

    ModelDo checkDoExist(Optional<Long> projectIdOpt, long doId);

    ModelDo queryDoByNameAndProjectId(Long projectId, String name);
    
    ModelDo queryDoByLabelAndProjectId(Long projectId, String label);

    List<ModelDo> queryDoByProjectId(Long projectId);
    
    ModelDo addDataObject(ModelDo modelDo);

    int updateModelDo(ModelDo modelDo);

    DataObject modelDo2DataObject(ModelDo modelDo);
    
    ModelDoHis addDataHisObject(ModelDo modelDo);

    ModelDo getSystemDo(Long projectId);
    
    String getInnerDoType(String outer);
    
    Function<String, String> DO_TYPE_2_INNER = outer -> {
        switch (outer) {
            case "1":
                return "sys";
            case "2":
                return "req";
            case "3":
                return "mid"; 
            case "4":
                return "out";
            case "5":
                return "rsp";    
            default:
                throw new ManagerException(ManagerErrorCode.E30);
        }
    };

    Set<String> getInnerDoTypes(String outerDoTypes);

    List<DoCatalog> listDataObjects(Long projectId, Set<String> doTypes, boolean includeFields);

    Map<Long, List<DataObject>> listDoGroupByCatalogId(List<ModelDo> modelDos, Set<String> doTypes);
}
