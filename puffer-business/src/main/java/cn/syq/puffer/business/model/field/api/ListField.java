package cn.syq.puffer.business.model.field.api;

import cn.syq.puffer.dao.sql.entity.ModelField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Optional;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/20 11:18
 */
public interface ListField {

    Page<ModelField> listField(long projectId, long doId
            , Optional<String> labelOpt, Optional<String> typeOpt, Optional<Boolean> listFlagOpt
            , int pageNo, int pageSize);
}
