/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.dataobject.service;

import cn.hutool.core.util.StrUtil;
import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.DataObjectDomainService;
import cn.syq.puffer.business.model.api.FieldDomainService;
import cn.syq.puffer.business.model.dataobject.api.CatalogMeta;
import cn.syq.puffer.business.model.dataobject.api.DataObject;
import cn.syq.puffer.business.model.dataobject.api.DataObjectMeta;
import cn.syq.puffer.business.model.dataobject.api.DoCatalog;
import cn.syq.puffer.business.model.field.api.Field;
import cn.syq.puffer.business.utils.ModelUtils;
import cn.syq.puffer.dao.sql.entity.ModelDo;
import cn.syq.puffer.dao.sql.entity.ModelDoCatalog;
import cn.syq.puffer.dao.sql.entity.ModelDoHis;
import cn.syq.puffer.dao.sql.entity.ModelField;
import cn.syq.puffer.dao.sql.mapper.ModelDoCatalogMapper;
import cn.syq.puffer.dao.sql.mapper.ModelDoHisMapper;
import cn.syq.puffer.dao.sql.mapper.ModelDoMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

/**
 *
 * @author shiyuqin
 * @date 2022/8/5 17:42
 */
@Service
public class DataObjectDomainServiceImpl implements DataObjectDomainService {
    
    @Autowired
    private ModelDoCatalogMapper modelDoCatalogMapper;
    
    @Autowired
    private ModelDoMapper modelDoMapper;
    
    @Autowired
    private ModelDoHisMapper modelDoHisMapper;

    @Autowired
    private FieldDomainService fieldDomainService;

    private static final Pattern DO_TYPE_OUTER_PATTERN = Pattern.compile("([01])([01])([01])([01])([01])");

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
    public ModelDo checkDoExist(Optional<Long> projectIdOpt, long doId) {
        ModelDo modelDo = modelDoMapper.selectById(doId);
        if (Objects.isNull(modelDo) || Objects.equals(modelDo.getProjectId(), projectIdOpt.orElse(modelDo.getProjectId()))) {
            throw new ManagerException(ManagerErrorCode.E20);
        }
        return modelDo;
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
    public List<ModelDo> queryDoByProjectId(Long projectId) {
        LambdaQueryWrapper<ModelDo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelDo::getProjectId, projectId);
        return modelDoMapper.selectList(wrapper);
    }

    @Override
    public ModelDo addDataObject(ModelDo modelDo) {
        modelDo.setDoType(getInnerDoType(modelDo.getDoType()));
        modelDoMapper.insert(modelDo);
        return modelDo;
    }

    @Override
    public ModelDoHis addDataHisObject(ModelDo modelDo) {
        ModelDoHis modelDoHis = new ModelDoHis();
        BeanCopier beanCopier = BeanCopier.create(ModelDo.class, ModelDoHis.class, true);
        beanCopier.copy(modelDo, modelDoHis, null);
        modelDoHis.setDoType(getInnerDoType(modelDo.getDoType()));
        modelDoHisMapper.insert(modelDoHis);
        return modelDoHis;
    }

    @Override
    public ModelDo getSystemDo(Long projectId) {
        ModelDo modelDo = new ModelDo();
        modelDo.setId(-1L);
        modelDo.setLabel("系统参数");
//        modelDo.setClassName();
        modelDo.setDoType(getInnerDoType("1"));
        modelDo.setDescription("系统参数");
        modelDo.setHisId(0L);
        modelDo.setCatalogId(0L);
        modelDo.setProjectId(projectId);
        return modelDo;
    }

    @Override
    public String getInnerDoType(String outer) {
        return DO_TYPE_2_INNER.apply(outer);
    }

    @Override
    public int updateModelDo(ModelDo modelDo) {
        LambdaUpdateWrapper<ModelDo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ModelDo::getId, modelDo.getId());
        wrapper.set(StrUtil.isNotBlank(modelDo.getClassName()), ModelDo::getClassName, modelDo.getClassName());
        wrapper.set(Objects.nonNull(modelDo.getCatalogId()), ModelDo::getCatalogId, modelDo.getCatalogId());
        wrapper.set(StrUtil.isNotBlank(modelDo.getLabel()), ModelDo::getLabel, modelDo.getLabel());
        wrapper.set(StrUtil.isNotBlank(modelDo.getDoType()), ModelDo::getDoType, modelDo.getDoType());
        wrapper.set(StrUtil.isNotBlank(modelDo.getDescription()), ModelDo::getDescription, modelDo.getDescription());
        wrapper.set(Objects.nonNull(modelDo.getHisId()), ModelDo::getHisId, modelDo.getHisId());
        return modelDoMapper.update(null, wrapper);
    }

    @Override
    public DataObject modelDo2DataObject(ModelDo modelDo) {
        DataObject dataObject = new DataObject();
        BeanCopier beanCopier = BeanCopier.create(ModelDo.class, DataObject.class, false);
        beanCopier.copy(modelDo, dataObject, null);
        dataObject.setName(modelDo.getClassName());
        return dataObject;
    }

    @Override
    public Set<String> getInnerDoTypes(String outerDoTypes) {
        Set<String> set = Sets.newHashSet();
        Matcher matcher = DO_TYPE_OUTER_PATTERN.matcher(outerDoTypes);
        if (matcher.matches()) {
            for (int i = 0; i < 5; i++) {
                if (Objects.equals("1", matcher.group(i))) {
                    set.add(getInnerDoType(i + ""));
                }
            }
        }
        return set;
    }

    @Override
    public List<DoCatalog> listDataObjects(Long projectId, Set<String> doTypes, boolean includeFields) {
        List<DoCatalog> catalogs = Lists.newArrayList();

        Map<Long, ModelDoCatalog> modelDoCatalogMap = listByProjectId(projectId, Optional.empty())
                .stream().collect(Collectors.toMap(ModelDoCatalog::getId, Function.identity()));

        List<ModelDo> modelDos = queryDoByProjectId(projectId);
        modelDos.add(getSystemDo(projectId));
        Map<Long, List<DataObject>> dataObjectMap = listDoGroupByCatalogId(modelDos, doTypes);

        List<ModelField> modelFields = fieldDomainService.queryByProjectId(projectId);
        modelFields.addAll(fieldDomainService.listSystemDoFields(projectId));
        Map<Long, List<Field>> fieldMap = includeFields ? fieldDomainService.listFieldGroupByDoId(modelFields) : null;

        dataObjectMap.forEach((catalogId, dos) -> {
            DoCatalog catalog = new DoCatalog();
            catalogs.add(catalog);

            ModelDoCatalog modelDoCatalog = modelDoCatalogMap.get(catalogId);
            catalog.setId(catalogId);
            if (Objects.nonNull(modelDoCatalog)) {
                catalog.setName(modelDoCatalog.getName());
                catalog.setLabel(modelDoCatalog.getLabel());
            }
            catalog.setDataObjects(dos);
            dos.forEach(dataObject -> {
                if (includeFields && fieldMap.containsKey(dataObject.getId())) {
                    dataObject.setFields(fieldMap.get(dataObject.getId()));
                }
            });
        });

        modelDoCatalogMap.values().stream()
                .filter(modelDoCatalog -> !dataObjectMap.containsKey(modelDoCatalog.getId()))
                .forEach(modelDoCatalog -> {
                    DoCatalog catalog = new DoCatalog();
                    catalogs.add(catalog);

                    catalog.setId(modelDoCatalog.getId());
                    catalog.setName(modelDoCatalog.getName());
                    catalog.setLabel(modelDoCatalog.getLabel());
                    catalog.setDataObjects(Collections.emptyList());
                });

        return catalogs;
    }

    @Override
    public Map<Long, List<DataObject>> listDoGroupByCatalogId(List<ModelDo> modelDos, Set<String> doTypes) {
        return modelDos.stream()
                .filter(modelDo -> doTypes.contains(modelDo.getDoType()))
                .map(this::modelDo2DataObject)
                .collect(Collectors.groupingBy(DataObject::getCatalogId, Collectors.toList()));
    }
}
