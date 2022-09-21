package cn.syq.puffer.business.model.field.service;

import cn.syq.puffer.business.model.field.api.FieldMeta;
import cn.syq.puffer.dao.sql.entity.ModelField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Optional;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/19 16:00
 */
public interface FieldService {

    FieldMeta addField(ModelField modelField);

    Page<FieldMeta> listFields(long projectId, long doId, String method
            , Optional<String> labelOpt, Optional<String> typeOpt, Optional<Boolean> listFlagOpt
            , int pageNo, int pageSize);

    FieldMeta modelField2FieldMeta(ModelField modelField);

    FieldMeta getFieldMeta(long projectId, long doId, long fieldId);

    FieldMeta editField(ModelField modelField);

    void deleteField(ModelField modelField);
}
