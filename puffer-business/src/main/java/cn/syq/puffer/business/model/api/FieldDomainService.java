package cn.syq.puffer.business.model.api;

import cn.syq.puffer.business.model.field.api.Field;
import cn.syq.puffer.dao.sql.entity.ModelField;

import java.util.List;
import java.util.Map;

/**
 *
 * @author shiyuqin
 * @date 2022/9/16 17:57
 */
public interface FieldDomainService {

    List<ModelField> queryByProjectId(Long projectId);

    ModelField getSystemDoField(Long projectId, Long fieldId);

    List<ModelField> listSystemDoFields(Long projectId);

    Map<Long, List<Field>> listFieldGroupByDoId(List<ModelField> modelFields);

    Field modelField2Field(ModelField modelField);
}
