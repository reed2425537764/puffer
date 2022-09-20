package cn.syq.puffer.business.model.field.service;

import cn.syq.puffer.business.dict.DataType;
import cn.syq.puffer.business.dict.YNEnum;
import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.DataObjectDomainService;
import cn.syq.puffer.business.model.api.FieldDomainService;
import cn.syq.puffer.business.model.api.ProjectDomainService;
import cn.syq.puffer.business.model.dataobject.api.DataObjectAppend;
import cn.syq.puffer.business.model.field.api.FieldMeta;
import cn.syq.puffer.business.model.field.api.ListField;
import cn.syq.puffer.business.utils.StringTools;
import cn.syq.puffer.dao.sql.entity.ModelField;
import cn.syq.puffer.dao.sql.entity.ModelFieldHis;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/19 16:01
 */
public class FieldServiceImpl implements FieldService{

    @Autowired
    private DataObjectDomainService dataobjectDomainService;

    @Autowired
    private ProjectDomainService projectDomainService;

    @Autowired
    private FieldDomainService fieldDomainService;

    @Autowired
    private Map<String, ListField> listFieldMap;

    @Override
    public FieldMeta addField(ModelField modelField) {

        projectDomainService.checkProjectExist(modelField.getProjectId());
        dataobjectDomainService.checkDoExist(Optional.of(modelField.getProjectId()), modelField.getDoId());

        ModelField modelFieldByName = fieldDomainService.queryByName(modelField.getProjectId(), modelField.getDoId(), modelField.getName());
        if (Objects.nonNull(modelFieldByName) && !Objects.equals(modelFieldByName.getName(), modelField.getName())) {
            throw new ManagerException(ManagerErrorCode.E20);
        }

        ModelField modelFieldByLabel = fieldDomainService.queryByName(modelField.getProjectId(), modelField.getDoId(), modelField.getLabel());
        if (Objects.nonNull(modelFieldByLabel) && !Objects.equals(modelFieldByLabel.getLabel(), modelField.getLabel())) {
            throw new ManagerException(ManagerErrorCode.E20);
        }

        ModelField modelFieldNew = fieldDomainService.addField(modelField);
        ModelFieldHis modelFieldHis = fieldDomainService.addFieldHis(modelField);
        ModelField modelFieldUpdate = new ModelField();
        modelFieldUpdate.setId(modelFieldNew.getId());
        modelFieldUpdate.setHisId(modelFieldHis.getId());
        fieldDomainService.updateModelField(modelFieldUpdate);

        FieldMeta fieldMeta = new FieldMeta();
        fieldMeta.setId(modelField.getId());
        return fieldMeta;
    }

    @Override
    public Page<FieldMeta> listFields(long projectId, long doId, String method, Optional<String> labelOpt, Optional<String> typeOpt, Optional<Boolean> listFlagOpt, int pageNo, int pageSize) {

        projectDomainService.checkProjectExist(projectId);

        if (doId == -1L) {
            List<ModelField> systemDoFields = fieldDomainService.listSystemDoFields(projectId);
            List<ModelField> modelFieldsNew = Lists.newArrayList();
            Optional<String> typeInnerOpt = typeOpt.map(DataType.FILE_CLASS_TYPE_2_INNER);
            Optional<String> listFlagInnerOpt = listFlagOpt.map(b -> b ? YNEnum.Y.getName() : YNEnum.N.getName());

            if (typeInnerOpt.isPresent() && listFlagInnerOpt.isPresent()) {
                systemDoFields.stream()
                        .filter(field -> Objects.equals(field.getClassType(), typeInnerOpt.get()) && Objects.equals(field.getListFlag(), listFlagInnerOpt.get()))
                        .forEach(modelFieldsNew::add);
            } else if (typeInnerOpt.isPresent()) {
                systemDoFields.stream()
                        .filter(field -> Objects.equals(field.getClassType(), typeInnerOpt.get()))
                        .forEach(modelFieldsNew::add);
            } else if (listFlagInnerOpt.isPresent()) {
                systemDoFields.stream()
                        .filter(field -> Objects.equals(field.getListFlag(), listFlagInnerOpt.get()))
                        .forEach(modelFieldsNew::add);
            } else {
                modelFieldsNew = systemDoFields;
            }

            return new Page<FieldMeta>(pageSize, modelFieldsNew.size()).setRecords(
                    modelFieldsNew.stream()
                            .filter(modelField -> modelField.getClassType().contains(labelOpt.orElse("")))
                            .map(this::modelField2FieldMeta)
                            .collect(Collectors.toList())
            );
        }

        ListField listField = listFieldMap.get(StringTools.firstLetter2LowerCase(ListField.class.getSimpleName()) + method);
        if (Objects.isNull(listField)) {
            throw new ManagerException(ManagerErrorCode.E30);
        }

        Page<ModelField> modelFieldPage = listField.listField(projectId, doId, labelOpt, typeOpt, listFlagOpt, pageNo, pageSize);

        return new Page<FieldMeta>(modelFieldPage.getCurrent(), modelFieldPage.getSize()).setRecords(modelFieldPage.getRecords().stream().map(modelField -> {
            FieldMeta fieldMeta = new FieldMeta();
            fieldMeta.setId(modelField.getId());
            fieldMeta.setName(modelField.getName());
            fieldMeta.setLabel(modelField.getLabel());
            return fieldMeta;
        }).collect(Collectors.toList()));
    }

    @Override
    public FieldMeta modelField2FieldMeta(ModelField modelField) {
        FieldMeta fieldMeta = new FieldMeta();
        BeanCopier beanCopier = BeanCopier.create(ModelField.class, FieldMeta.class, false);
        beanCopier.copy(modelField, fieldMeta, null);
        return fieldMeta;
    }

    @Override
    public FieldMeta getFieldMeta(long projectId, long doId, long fieldId) {

        projectDomainService.checkProjectExist(projectId);
        dataobjectDomainService.checkDoExist(Optional.of(projectId), doId);
        ModelField modelField = fieldDomainService.checkFieldExist(projectId, doId, fieldId);

        FieldMeta fieldMeta = new FieldMeta();
        fieldMeta.setId(modelField.getId());
//        fieldMeta.setType(DataType.);
        return fieldMeta;
    }
}
