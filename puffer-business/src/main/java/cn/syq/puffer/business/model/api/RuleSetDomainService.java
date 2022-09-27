package cn.syq.puffer.business.model.api;

import cn.syq.puffer.business.model.ruleset.api.RuleSet;
import cn.syq.puffer.dao.sql.entity.ModelRs;
import cn.syq.puffer.dao.sql.entity.ModelRsCatalog;
import cn.syq.puffer.dao.sql.entity.ModelRsHis;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/21 11:15
 */
public interface RuleSetDomainService {

    Page<ModelRsCatalog> listRsCatalogPaging(long projectId, Optional<String> labelOpt, int pageNo, int pageSize);

    ModelRsCatalog queryRsCatalogByLabel(long projectId, String label);

    List<ModelRsCatalog> listRsCatalog(long projectId);

    ModelRsCatalog checkRsCatalogExist(long projectId, long rsCatalogId);

    ModelRsCatalog addRsCatalog(ModelRsCatalog modelRsCatalog);

    int updateRsCatalog(ModelRsCatalog modelRsCatalog);

    ModelRs queryRsByLabel(long projectId, String label);

    ModelRs checkRuleSetExist(long projectId, long rsId);

    List<ModelRs> listRsByCatalogId(long projectId, Optional<Long> rsCatalogId);

    ModelRs addRuleSet(ModelRs modelRs);

    int updateRuleSet(ModelRs modelRs);

    ModelRsHis addRuleSetHis(ModelRs modelRs, His his);

    RuleSet modelRs2Rs(ModelRs modelRs);

    Map<Long, List<RuleSet>> listRuleSetGroupByRsCatalogId(long projectId);

}
