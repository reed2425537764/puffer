package cn.syq.puffer.business.model.field.service;

import cn.syq.puffer.business.dict.DataType;
import cn.syq.puffer.business.dict.YNEnum;
import cn.syq.puffer.business.model.api.FieldDomainService;
import cn.syq.puffer.business.model.field.api.ListField;
import cn.syq.puffer.dao.sql.entity.ModelField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/20 11:20
 */
public class ListField1 implements ListField {

    @Autowired
    private FieldDomainService fieldDomainService;


    @Override
    public Page<ModelField> listField(long projectId, long doId, Optional<String> labelOpt, Optional<String> typeOpt, Optional<Boolean> listFlagOpt, int pageNo, int pageSize) {

        Optional<String> typeInnerOpt = typeOpt.map(DataType.FILE_CLASS_TYPE_2_INNER);
        Optional<String> listFlagInnerOpt = listFlagOpt.map(listFlag -> listFlag ? YNEnum.Y.getName() : YNEnum.N.getName());

        return fieldDomainService.listFieldsPaging(projectId, doId, labelOpt, typeInnerOpt, listFlagInnerOpt, Page.of(pageNo, pageSize));
    }
}
