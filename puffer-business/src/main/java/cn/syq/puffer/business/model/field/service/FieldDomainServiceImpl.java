package cn.syq.puffer.business.model.field.service;

import cn.syq.puffer.business.dict.DataType;
import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.FieldDomainService;
import cn.syq.puffer.business.model.api.His;
import cn.syq.puffer.business.model.api.HisType;
import cn.syq.puffer.business.model.dataobject.api.SystemDo;
import cn.syq.puffer.business.model.field.api.Field;
import cn.syq.puffer.business.utils.StringTools;
import cn.syq.puffer.dao.sql.entity.ModelDo;
import cn.syq.puffer.dao.sql.entity.ModelField;
import cn.syq.puffer.dao.sql.entity.ModelFieldHis;
import cn.syq.puffer.dao.sql.mapper.ModelFieldHisMapper;
import cn.syq.puffer.dao.sql.mapper.ModelFieldMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/16 17:29
 */
@Service
public class FieldDomainServiceImpl implements FieldDomainService {

    @Autowired
    private ModelFieldMapper modelFieldMapper;

    @Autowired
    private ModelFieldHisMapper modelFieldHisMapper;


    @Override
    public List<ModelField> queryByProjectId(Long projectId) {
        LambdaQueryWrapper<ModelField> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelField::getProjectId, projectId);
        return modelFieldMapper.selectList(wrapper);
    }

    @Override
    public ModelField queryByName(long projectId, long doId, String name) {
        LambdaQueryWrapper<ModelField> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelField::getProjectId, projectId).eq(ModelField::getDoId, doId).eq(ModelField::getName, name);
        return modelFieldMapper.selectOne(wrapper);
    }

    @Override
    public ModelField queryByLabel(long projectId, long doId, String label) {
        LambdaQueryWrapper<ModelField> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelField::getProjectId, projectId).eq(ModelField::getDoId, doId).eq(ModelField::getLabel, label);
        return modelFieldMapper.selectOne(wrapper);
    }

    @Override
    public ModelField checkFieldExist(long projectId, long doId, long fieldId) {
        ModelField modelField = modelFieldMapper.selectById(fieldId);
        if (Objects.isNull(modelField) || !Objects.equals(projectId, modelField.getProjectId()) || !Objects.equals(doId, modelField.getDoId())) {
            throw new ManagerException(ManagerErrorCode.E20);
        }
        return modelField;
    }

    @Override
    public Page<ModelField> listFieldsPaging(long projectId, long doId, Optional<String> labelOpt, Optional<String> typeOpt, Optional<String> listFlagOpt, Page<ModelField> page) {
        LambdaQueryWrapper<ModelField> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelField::getProjectId, projectId).eq(ModelField::getDoId, doId);
        wrapper.eq(labelOpt.isPresent(), ModelField::getLabel, labelOpt.get());
        wrapper.eq(listFlagOpt.isPresent(), ModelField::getListFlag, listFlagOpt.get());
        wrapper.eq(typeOpt.isPresent(), ModelField::getClassType, typeOpt.get());
        return modelFieldMapper.selectPage(page, wrapper);
    }

    @Override
    public ModelField addField(ModelField modelField) {
        modelField.setClassType(DataType.FILE_CLASS_TYPE_2_INNER.apply(modelField.getClassType()));
        modelFieldMapper.insert(modelField);
        return modelField;
    }

    @Override
    public int updateModelField(ModelField modelField) {
        LambdaUpdateWrapper<ModelField> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ModelField::getId, modelField.getId());
        wrapper.set(StringTools.isNotBlank(modelField.getName()), ModelField::getName, modelField.getName());
        wrapper.set(StringTools.isNotBlank(modelField.getLabel()), ModelField::getLabel, modelField.getLabel());
        wrapper.set(StringTools.isNotBlank(modelField.getDescription()), ModelField::getDescription, modelField.getDescription());
        wrapper.set(StringTools.isNotBlank(modelField.getClassType()), ModelField::getClassType, modelField.getClassType());
        wrapper.set(StringTools.isNotBlank(modelField.getListFlag()), ModelField::getListFlag, modelField.getListFlag());
        wrapper.set(Objects.nonNull(modelField.getHisId()), ModelField::getHisId, modelField.getHisId());
        return modelFieldMapper.update(null, wrapper);
    }

    @Override
    public void deleteField(ModelField modelField) {
        addFieldHis(modelField, His.of(HisType.D, "删除"));
        modelFieldMapper.deleteById(modelField.getId());
    }

    @Override
    public ModelField getSystemDoField(Long projectId, Long fieldId) {
        ModelField modelField = new ModelField();
        modelField.setId(fieldId);
        modelField.setName(SystemDo.FIELD_NAME.get(fieldId));
        modelField.setLabel(SystemDo.FIELD_LABEL.get(fieldId));
        modelField.setDescription(SystemDo.FIELD_DESC.get(fieldId));
        modelField.setClassType(SystemDo.FIELD_CLASS_TYPE.get(fieldId));
        modelField.setListFlag("N");
        modelField.setHisId(0L);
        modelField.setDoId(-1L);
        return modelField;
    }

    @Override
    public ModelFieldHis addFieldHis(ModelField modelField, His his) {
        ModelFieldHis modelFieldHis = new ModelFieldHis();
        BeanCopier beanCopier = BeanCopier.create(ModelField.class, ModelFieldHis.class, false);
        beanCopier.copy(modelField, modelFieldHis, null);
        modelFieldHis.setForeignId(modelField.getId());
        modelFieldHis.setType(his.getType().name());
        modelFieldHis.setComment(his.getRemark());
        modelFieldHisMapper.insert(modelFieldHis);
        return modelFieldHis;
    }

    @Override
    public List<ModelField> listSystemDoFields(Long projectId) {
        return SystemDo.FIELD_CLASS_TYPE.keySet().stream().map(field -> getSystemDoField(projectId, field)).collect(Collectors.toList());
    }

    @Override
    public Map<Long, List<Field>> listFieldGroupByDoId(List<ModelField> modelFields) {
        return modelFields.stream().map(this::modelField2Field).collect(Collectors.groupingBy(
                Field::getDoId,
                () -> new TreeMap<>(Comparator.naturalOrder()),
                Collectors.toList()
        ));
    }

    @Override
    public Field modelField2Field(ModelField modelField) {
        Field field = new Field();
        BeanCopier beanCopier = BeanCopier.create(ModelField.class, Field.class, false);
        beanCopier.copy(modelField, field, null);
        return field;
    }
}
