package cn.syq.puffer.manager.model.controller;

import cn.syq.puffer.business.model.field.api.FieldMeta;
import cn.syq.puffer.business.model.field.service.FieldService;
import cn.syq.puffer.dao.sql.entity.ModelField;
import cn.syq.puffer.manager.model.api.ManagerResponse;
import cn.syq.puffer.manager.model.api.PageVo;
import cn.syq.puffer.manager.model.api.field.FieldDelete;
import cn.syq.puffer.manager.model.api.field.FieldEdit;
import cn.syq.puffer.manager.model.api.field.FieldNew;
import cn.syq.puffer.manager.model.api.project.ProjectMetaResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/19 15:59
 */
@RestController
@RequestMapping("/model/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;


    @PostMapping("/{projectId:\\d+}/dataobject/{doId:\\d+}/field")
    @Valid
    public ManagerResponse<FieldMeta> addField(@RequestBody FieldNew fieldNew) {
        ModelField modelField = new ModelField();
        BeanCopier beanCopier = BeanCopier.create(FieldNew.class, ModelField.class, false);
        beanCopier.copy(fieldNew, modelField, null);
        return ManagerResponse.buildSuccess(fieldService.addField(modelField));
    }

    @GetMapping("/{projectId:\\d+}/dataobject/{doId:\\d+}/field")
    @Valid
    public ManagerResponse<PageVo<FieldMeta>> listFields(@RequestParam(required = false, defaultValue = "1") @Length(min = 1, max = 999) int pageNo,
                                                         @RequestParam(required = false, defaultValue = "30") @Length(min = 1, max = 999) int pageSize,
                                                         @PathVariable("projectId") @Min(1L) Long projectId,
                                                         @PathVariable("doId") @Min(1L) Long doId,
                                                         @RequestParam(name = "method") @Pattern(regexp = "[1-2]") String method,
                                                         @RequestParam(name = "type", required = false) @Pattern(regexp = "[1-7]") String type,
                                                         @RequestParam(name = "listFlag", required = false) Boolean listFlag,
                                                         @RequestParam(name = "label", required = false) @Length(min = 1, max = 60) String label) {
        Page<FieldMeta> page = fieldService.listFields(
                projectId, doId, method,
                Optional.ofNullable(label), Optional.ofNullable(type), Optional.ofNullable(listFlag),
                pageNo, pageSize);

        PageVo<FieldMeta> pageVo = PageVo.<FieldMeta>builder()
                .pageNo((int) page.getCurrent())
                .pageSize((int) page.getSize())
                .totalPage((int) Math.ceil((double) page.getTotal() / page.getSize()))
                .totalSize((int) page.getTotal())
                .data(page.getRecords())
                .build();
        return ManagerResponse.buildSuccess(pageVo);
    }

    @GetMapping("/{projectId:\\d+}/dataobject/{doId:\\d+}/field/{fieldId:\\d+}/meta")
    @Valid
    public ManagerResponse<FieldMeta> getFieldMeta(@PathVariable("projectId") @Min(1L) Long projectId,
                                                   @PathVariable("doId") @Min(1L) Long doId,
                                                   @PathVariable("fieldId") @Min(1L) Long fieldId) {
        return ManagerResponse.buildSuccess(fieldService.getFieldMeta(projectId, doId, fieldId));
    }

    @PostMapping("/{projectId:\\d+}/dataobject/{doId:\\d+}/field/{fieldId:\\d+}/meta")
    @Valid
    public ManagerResponse<FieldMeta> editFieldMeta(@RequestBody FieldEdit fieldEdit) {
        ModelField modelField = new ModelField();
        BeanCopier beanCopier = BeanCopier.create(FieldEdit.class, ModelField.class, false);
        beanCopier.copy(fieldEdit, modelField, null);
        return ManagerResponse.buildSuccess(fieldService.editField(modelField));
    }

    @DeleteMapping("/{projectId:\\d+}/dataobject/{doId:\\d+}/field/{fieldId:\\d+}")
    @Valid
    public ManagerResponse<Void> deleteField(@RequestBody FieldDelete fieldDelete) {
        return ManagerResponse.buildSuccess(null);
    }
}
