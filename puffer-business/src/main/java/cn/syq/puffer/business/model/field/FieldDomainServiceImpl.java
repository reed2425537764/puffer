package cn.syq.puffer.business.model.field;

import cn.syq.puffer.business.model.api.FieldDomainService;
import cn.syq.puffer.business.model.dataobject.api.SystemDo;
import cn.syq.puffer.business.model.field.api.Field;
import cn.syq.puffer.dao.sql.entity.ModelField;
import cn.syq.puffer.dao.sql.mapper.ModelFieldHisMapper;
import cn.syq.puffer.dao.sql.mapper.ModelFieldMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
