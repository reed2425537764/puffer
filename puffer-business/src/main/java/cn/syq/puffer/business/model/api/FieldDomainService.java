package cn.syq.puffer.business.model.api;

import cn.syq.puffer.business.model.field.api.Field;
import cn.syq.puffer.dao.sql.entity.ModelField;
import cn.syq.puffer.dao.sql.entity.ModelFieldHis;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author shiyuqin
 * @date 2022/9/16 17:57
 */
public interface FieldDomainService {

    List<ModelField> queryByProjectId(Long projectId);

    ModelField queryByName(long projectId, long doId, String name);

    ModelField queryByLabel(long projectId, long doId, String label);

    ModelField checkFieldExist(long projectId, long doId, long fieldId);

    Page<ModelField> listFieldsPaging(long projectId, long doId,
                                      Optional<String> labelOpt, Optional<String> typeOpt, Optional<String> listFlagOpt,
                                      Page<ModelField> page);

    ModelField addField(ModelField modelField);

    int updateModelField(ModelField modelField);

    void deleteField(ModelField modelField);

    ModelField getSystemDoField(Long projectId, Long fieldId);

    ModelFieldHis addFieldHis(ModelField modelField, His his);

    List<ModelField> listSystemDoFields(Long projectId);

    Map<Long, List<Field>> listFieldGroupByDoId(List<ModelField> modelFields);

    Field modelField2Field(ModelField modelField);
}
