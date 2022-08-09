/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.api;

import cn.syq.puffer.dao.sql.entity.ModelDoCatalog;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author shiyuqin
 * @date 2022/8/5 17:40
 */
public interface DataobjectDomainService {
    
    ModelDoCatalog queryByLabelAndProjectId(Long projectId, String label);
    
    ModelDoCatalog checkDoCatalogExist(Long projectId, String label);
    
    ModelDoCatalog addDataobjectCatalog(Long projectId, String label);
    
    List<ModelDoCatalog> listByProjectId(Long projectId, Optional<String> labelOpt);
    
    ModelDoCatalog queryById(Long doCataId);
}
