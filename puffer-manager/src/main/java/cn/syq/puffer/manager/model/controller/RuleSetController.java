package cn.syq.puffer.manager.model.controller;

import cn.hutool.core.lang.Pair;
import cn.syq.puffer.business.model.ruleset.api.RsCatalog;
import cn.syq.puffer.business.model.ruleset.api.RuleSet;
import cn.syq.puffer.dao.sql.entity.ModelRs;
import cn.syq.puffer.manager.model.api.rule.RuleMeta;
import cn.syq.puffer.manager.model.api.ruleset.*;
import cn.syq.puffer.business.model.ruleset.service.RuleSetService;
import cn.syq.puffer.dao.sql.entity.ModelRsCatalog;
import cn.syq.puffer.manager.model.api.ManagerResponse;
import cn.syq.puffer.manager.model.api.PageVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/21 11:14
 */
@RestController
@RequestMapping("/model/ruleset")
public class RuleSetController {

    @Autowired
    private RuleSetService ruleSetService;


    @GetMapping("/{projectId:\\d+}/ruleset/catalog")
    @Valid
    public ManagerResponse<PageVo<RsCatalogMeta>> listRuleSetCatalogs(@RequestParam(required = false, defaultValue = "1") @Length(min = 1, max = 999) int pageNo,
                                                                      @RequestParam(required = false, defaultValue = "30") @Length(min = 1, max = 999) int pageSize,
                                                                      @RequestParam(name = "label", required = false) @Length(min = 1, max = 60) String label,
                                                                      @RequestParam(name = "projectId") @Min(1L) Long projectId) {
        Page<ModelRsCatalog> page = ruleSetService.listRuleSetCatalogs(projectId, Optional.ofNullable(label), pageNo, pageSize);

        PageVo<RsCatalogMeta> pageVo = PageVo.<RsCatalogMeta>builder()
                .pageNo((int) page.getCurrent())
                .pageSize((int) page.getSize())
                .totalPage((int) Math.ceil((double) page.getTotal() / page.getSize()))
                .totalSize((int) page.getTotal())
                .data(
                        page.getRecords().stream().map(modelRsCatalog -> {
                            RsCatalogMeta rsCatalogMeta = new RsCatalogMeta();
                            rsCatalogMeta.setId(modelRsCatalog.getId());
                            rsCatalogMeta.setLabel(modelRsCatalog.getLabel());
                            return rsCatalogMeta;
                        }).collect(Collectors.toList())
                )
                .build();

        return ManagerResponse.buildSuccess(pageVo);
    }

    @PutMapping("/{projectId:\\d+}/ruleset/catalog")
    @Valid
    public ManagerResponse<RsCatalogMeta> addRsCatalog(@RequestBody RsCatalogNew rsCatalogNew) {
        ModelRsCatalog modelRsCatalog = new ModelRsCatalog();
        modelRsCatalog.setProjectId(rsCatalogNew.getProjectId());
        modelRsCatalog.setLabel(rsCatalogNew.getLabel());

        ruleSetService.addRsCatalog(modelRsCatalog);

        RsCatalogMeta rsCatalogMeta = new RsCatalogMeta();
        rsCatalogMeta.setId(modelRsCatalog.getId());
        return ManagerResponse.buildSuccess(rsCatalogMeta);
    }

    @GetMapping("/{projectId:\\d+}/ruleset/catalog/{rsCatalogId:\\d+}/meta")
    @Valid
    public ManagerResponse<RsCatalogMeta> getRsCatalogMeta(@PathVariable("projectId") @Min(-1L) Long projectId,
                                                           @PathVariable("rsCatalogId") @Min(-1L) Long rsCatalogId) {

        ModelRsCatalog modelRsCatalog = ruleSetService.getRsCatalogMeta(projectId, rsCatalogId);
        RsCatalogMeta rsCatalogMeta = new RsCatalogMeta();
        rsCatalogMeta.setId(modelRsCatalog.getId());
        rsCatalogMeta.setName(modelRsCatalog.getName());
        rsCatalogMeta.setLabel(modelRsCatalog.getLabel());
        return ManagerResponse.buildSuccess(rsCatalogMeta);
    }

    @PostMapping("/{projectId:\\d+}/ruleset/catalog/{rsCatalogId:\\d+}/meta")
    @Valid
    public ManagerResponse<RsCatalogMeta> editRsCatalogMeta(RsCatalogEdit rsCatalogEdit) {
        ModelRsCatalog modelRsCatalog = new ModelRsCatalog();
        modelRsCatalog.setProjectId(rsCatalogEdit.getProjectId());
        modelRsCatalog.setLabel(rsCatalogEdit.getLabel());
        modelRsCatalog.setId(rsCatalogEdit.getId());

        ruleSetService.editRsCatalogMeta(modelRsCatalog);

        RsCatalogMeta rsCatalogMeta = new RsCatalogMeta();
        rsCatalogMeta.setId(modelRsCatalog.getId());
        rsCatalogMeta.setLabel(modelRsCatalog.getLabel());
        rsCatalogMeta.setName(modelRsCatalog.getName());
        return ManagerResponse.buildSuccess(rsCatalogMeta);
    }

    @DeleteMapping("/{projectId:\\d+}/ruleset/catalog/{rsCatalogId:\\d+}")
    @Valid
    public ManagerResponse<Void> deleteRsCatalog() {
        return null;
    }

    @PutMapping("/{projectId:\\d+}/ruleset")
    @Valid
    public ManagerResponse<RuleSetMeta> addRuleSet(@RequestBody RuleSetNew ruleSetNew) {
        ModelRs modelRs = new ModelRs();
        BeanCopier beanCopier = BeanCopier.create(RuleSetNew.class, ModelRs.class, false);
        beanCopier.copy(ruleSetNew, modelRs, null);

        modelRs = ruleSetService.addRuleSet(modelRs);

        RuleSetMeta ruleSetMeta = new RuleSetMeta();
        ruleSetMeta.setId(modelRs.getId());
        return ManagerResponse.buildSuccess(ruleSetMeta);
    }

    @GetMapping("/{projectId:\\d+}/ruleset")
    @Valid
    public ManagerResponse<List<RsCatalogMeta>> listRuleSetMeta(@PathVariable("projectId") @Min(-1L) Long projectId,
                                                              @RequestParam(name = "rsCatalogId", required = false) Long rsCatalogId) {

        List<RsCatalog> list = ruleSetService.listRuleSetMeta(projectId, Optional.ofNullable(rsCatalogId), false);
        return ManagerResponse.buildSuccess(list.stream().map(rsCatalog -> {
            RsCatalogMeta rsCatalogMeta = new RsCatalogMeta();
            rsCatalogMeta.setId(rsCatalog.getId());
            rsCatalogMeta.setName(rsCatalog.getName());
            rsCatalogMeta.setLabel(rsCatalog.getLabel());
            rsCatalogMeta.setRuleSetMetas(rsCatalog.getRuleSets().stream().map(ruleSet -> {
                RuleSetMeta ruleSetMeta = new RuleSetMeta();
                ruleSetMeta.setId(ruleSet.getId());
                ruleSetMeta.setName(ruleSet.getName());
                ruleSetMeta.setRuleMetas(ruleSet.getRules().stream().map(rule -> {
                    RuleMeta ruleMeta = new RuleMeta();
                    ruleMeta.setId(rule.getId());
                    ruleMeta.setName(rule.getName());
                    return ruleMeta;
                }).collect(Collectors.toList()));
                return ruleSetMeta;
            }).collect(Collectors.toList()));
            return rsCatalogMeta;
        }).collect(Collectors.toList()));
    }
}
