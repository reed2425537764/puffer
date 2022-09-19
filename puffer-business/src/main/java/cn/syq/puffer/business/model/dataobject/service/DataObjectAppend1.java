/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.dataobject.service;

import cn.hutool.core.util.StrUtil;
import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.DataObjectDomainService;
import cn.syq.puffer.business.model.dataobject.api.DataObjectAppend;
import cn.syq.puffer.business.model.dataobject.api.DataObjectMeta;
import cn.syq.puffer.dao.sql.entity.ModelDo;
import cn.syq.puffer.dao.sql.entity.ModelDoHis;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shiyuqin
 */
@Service
public class DataObjectAppend1 implements DataObjectAppend{

    @Autowired
    private DataObjectDomainService dataobjectDomainService;
    
    
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
        ModelDoHis modelDoHis = dataobjectDomainService.addDataHisObject(modelDo);
        ModelDo updateModelDo = new ModelDo();
        updateModelDo.setId(modelDo.getId());
        updateModelDo.setHisId(modelDoHis.getId());
        dataobjectDomainService.updateModelDo(updateModelDo);
        
        return dataobjectDomainService.builDataObjectMeta(modelDo);
    }
    
}
