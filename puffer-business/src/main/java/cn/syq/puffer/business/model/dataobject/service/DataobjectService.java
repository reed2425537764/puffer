/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.dataobject.service;


import cn.syq.puffer.business.model.dataobject.api.CatalogMeta;
import cn.syq.puffer.business.model.dataobject.api.DataObjectMeta;
import cn.syq.puffer.dao.sql.entity.ModelDo;
import java.util.List;


/**
 *
 * @author shiyuqin
 * @date 2022/8/5 17:43
 */
public interface DataobjectService {
    
    CatalogMeta addDataobjectCatalog(Long projectId, String label);
    
    List<CatalogMeta> listDataobjectCatalogs(Long projectId, String label);
    
    CatalogMeta getDataobjectCatalog(Long projectId, Long doCataId);
    
    CatalogMeta editCatalogMeta(Long projectId, Long doCataId, String label);
    
    DataObjectMeta addDataObject(ModelDo modelDo);
    
     
}
