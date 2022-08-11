/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.dataobject.service;

import cn.hutool.core.util.StrUtil;
import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.DataobjectDomainService;
import cn.syq.puffer.business.model.api.ProjectDomainService;
import cn.syq.puffer.business.model.dataobject.api.DataObjectAppend;
import cn.syq.puffer.business.model.dataobject.api.DataObjectMeta;
import cn.syq.puffer.dao.sql.entity.ModelDo;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author shiyuqin
 */
public class DataObjectAppend1 implements DataObjectAppend{

    @Autowired
    private DataobjectDomainService dataobjectDomainService;
    
    
    @Override
    public DataObjectMeta addDataObject(ModelDo modelDo) {
        if (StrUtil.isBlank(modelDo.getClassName()) || StrUtil.isBlank(modelDo.getLabel()) || StrUtil.isBlank(modelDo.getDescription())) {
            throw new ManagerException(ManagerErrorCode.E10);
        }
        
        ModelDo oldModelDo = dataobjectDomainService.queryDoByLabelAndProjectId(modelDo.getProjectId(), modelDo.getLabel());
        if (Objects.nonNull(oldModelDo) && Objects.equals(modelDo.getLabel(), oldModelDo.getLabel())) {
            throw new ManagerException(ManagerErrorCode.E30);
        }
        
        oldModelDo = dataobjectDomainService.queryDoByNameAndProjectId(modelDo.getProjectId(), modelDo.getClassName());
        if (Objects.nonNull(oldModelDo) && Objects.equals(modelDo.getLabel(), oldModelDo.getLabel())) {
            throw new ManagerException(ManagerErrorCode.E30);
        }
        
        modelDo = dataobjectDomainService.addDataObject(modelDo);
        
        return dataobjectDomainService.builDataObjectMeta(modelDo);
    }
    
}
