package cn.syq.puffer.business.model.ruleset.service;

import cn.hutool.core.lang.Pair;
import cn.syq.puffer.business.model.ruleset.api.RsCatalog;
import cn.syq.puffer.business.model.ruleset.api.RuleSet;
import cn.syq.puffer.dao.sql.entity.ModelRs;
import cn.syq.puffer.dao.sql.entity.ModelRsCatalog;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.sf.jsqlparser.Model;

import java.util.List;
import java.util.Optional;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/21 11:16
 */
public interface RuleSetService {

    Page<ModelRsCatalog> listRuleSetCatalogs(long projectId, Optional<String> labelOpt, int pageNo, int pageSize);

    ModelRsCatalog addRsCatalog(ModelRsCatalog modelRsCatalog);

    ModelRsCatalog getRsCatalogMeta(long projectId, long rsCatalogId);

    ModelRsCatalog editRsCatalogMeta(ModelRsCatalog modelRsCatalog);

    ModelRs addRuleSet(ModelRs modelRs);

    List<RsCatalog> listRuleSetMeta(long projectId, Optional<Long> rsCatalogIdOpt, boolean includeRule);
}
