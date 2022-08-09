/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.dataobject.service;

import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.DataobjectDomainService;
import cn.syq.puffer.business.utils.ModelUtils;
import cn.syq.puffer.dao.sql.entity.ModelDoCatalog;
import cn.syq.puffer.dao.sql.entity.ModelProject;
import cn.syq.puffer.dao.sql.mapper.ModelDoCatalogMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
    
}
