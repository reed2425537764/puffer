/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.dataobject.service;

import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.DataobjectDomainService;
import cn.syq.puffer.business.model.dataobject.api.CatalogMeta;
import cn.syq.puffer.business.model.dataobject.api.DataObjectMeta;
import cn.syq.puffer.business.utils.ModelUtils;
import cn.syq.puffer.dao.sql.entity.ModelDo;
import cn.syq.puffer.dao.sql.entity.ModelDoCatalog;
import cn.syq.puffer.dao.sql.entity.ModelDoHis;
import cn.syq.puffer.dao.sql.entity.ModelProject;
import cn.syq.puffer.dao.sql.mapper.ModelDoCatalogMapper;
import cn.syq.puffer.dao.sql.mapper.ModelDoHisMapper;
import cn.syq.puffer.dao.sql.mapper.ModelDoMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

/**
 *
 * @author shiyuqin
 * @date 2022/8/5 17:42
 */
@Service
public class DataobjectDomainServiceImpl implements DataobjectDomainService{
    
    @Autowired
    private ModelDoCatalogMapper modelDoCatalogMapper;
    
    @Autowired
    private ModelDoMapper modelDoMapper;
    
    @Autowired
    private ModelDoHisMapper modelDoHisMapper;

    @Override
    public ModelDoCatalog queryByLabelAndProjectId(Long projectId, String label) {
        LambdaQueryWrapper<ModelDoCatalog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelDoCatalog::getProjectId, projectId);
        wrapper.eq(ModelDoCatalog::getLabel, label);
        return modelDoCatalogMapper.selectOne(wrapper);
    }

    @Override
    public ModelDoCatalog checkDoCatalogExist(Long projectId, String label) {
        ModelDoCatalog modelDoCatalog = queryByLabelAndProjectId(projectId, label);
        if (Objects.isNull(modelDoCatalog)) {
            throw new ManagerException(ManagerErrorCode.E20);
        }
        return modelDoCatalog;
    }
    
    @Override
    public ModelDoCatalog checkDoCatalogExist(Long doCataId) {
        ModelDoCatalog modelDoCatalog = modelDoCatalogMapper.selectById(doCataId);
        if (Objects.isNull(modelDoCatalog)) {
            throw new ManagerException(ManagerErrorCode.E20);
        }
        return modelDoCatalog;
    }

    @Override
    public ModelDoCatalog addDataobjectCatalog(Long projectId, String label) {
        ModelDoCatalog modelDoCatalog = new ModelDoCatalog();
        modelDoCatalog.setProjectId(projectId);
        modelDoCatalog.setLabel(label);
        modelDoCatalog.setName(ModelUtils.generateModelName());
        modelDoCatalogMapper.insert(modelDoCatalog);
        return modelDoCatalog;
    }

    @Override
    public List<ModelDoCatalog> listByProjectId(Long projectId, Optional<String> labelOpt) {
        LambdaQueryWrapper<ModelDoCatalog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelDoCatalog::getProjectId, projectId);
        wrapper.like(labelOpt.isPresent(), ModelDoCatalog::getLabel, labelOpt.get());
        return modelDoCatalogMapper.selectList(wrapper);
    }

    @Override
    public ModelDoCatalog queryById(Long doCataId) {
        return modelDoCatalogMapper.selectById(doCataId);
    }

    @Override
    public CatalogMeta buildCatalogMeta(ModelDoCatalog modelDoCatalog) {
        CatalogMeta catalogMeta = new CatalogMeta();
        catalogMeta.setId(modelDoCatalog.getId());
        catalogMeta.setName(modelDoCatalog.getName());
        catalogMeta.setLabel(modelDoCatalog.getLabel());
        catalogMeta.setProjectId(modelDoCatalog.getProjectId());
        catalogMeta.setCreateUid(modelDoCatalog.getCreateUid());
        catalogMeta.setCreateTime(modelDoCatalog.getCreateTime());
        catalogMeta.setUpdateUid(modelDoCatalog.getUpdateUid());
        catalogMeta.setUpdateTime(modelDoCatalog.getUpdateTime());
        return catalogMeta;
    }

    @Override
    public int updateCatalog(Long doCataId, String label) {
        LambdaUpdateWrapper<ModelDoCatalog> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ModelDoCatalog::getId, doCataId).set(ModelDoCatalog::getLabel, label);
        return modelDoCatalogMapper.update(null, wrapper);
    }

    @Override
    public DataObjectMeta builDataObjectMeta(ModelDo modelDo) {
        DataObjectMeta dataObjectMeta = new DataObjectMeta();
        dataObjectMeta.setId(modelDo.getId());
        dataObjectMeta.setName(modelDo.getClassName());
        dataObjectMeta.setLabel(modelDo.getLabel());
        dataObjectMeta.setProjectId(modelDo.getProjectId());
        
        
        return dataObjectMeta;
    }

    @Override
    public ModelDo queryDoByNameAndProjectId(Long projectId, String name) {
        LambdaQueryWrapper<ModelDo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelDo::getProjectId, projectId).eq(ModelDo::getClassName, name);
        return modelDoMapper.selectOne(wrapper);
    }

    @Override
    public ModelDo queryDoByLabelAndProjectId(Long projectId, String label) {
        LambdaQueryWrapper<ModelDo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelDo::getProjectId, projectId).eq(ModelDo::getLabel, label);
        return modelDoMapper.selectOne(wrapper);
    }   

    @Override
    public ModelDo addDataObject(ModelDo modelDo) {
        modelDo.setDoType(getDoTypeInner(modelDo.getDoType()));
        modelDoMapper.insert(modelDo);
        return modelDo;
    }

    @Override
    public ModelDoHis addDataHisObject(ModelDo modelDo) {
        ModelDoHis modelDoHis = new ModelDoHis();
        BeanCopier beanCopier = BeanCopier.create(ModelDo.class, ModelDoHis.class, true);
        beanCopier.copy(modelDo, modelDoHis, null);
        modelDoHisMapper.insert(modelDoHis);
        return modelDoHis;
    }

    @Override
    public String getDoTypeInner(String outer) {
        return DO_TYPE_2_INNER.apply(outer);
    }
}
