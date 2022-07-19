package cn.syq.puffer.manager.model.controller;

import cn.syq.puffer.business.model.project.service.ProjectService;
import cn.syq.puffer.dao.sql.entity.ModelProject;
import cn.syq.puffer.manager.model.api.ManagerResponse;
import cn.syq.puffer.manager.model.api.PageVo;
import cn.syq.puffer.manager.model.api.ProjectMetaResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/16 20:37
 */
@RestController
@RequestMapping("/model/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PutMapping
    @Valid
    public ManagerResponse<ProjectMetaResponse> newProject(@NotBlank @Length(max = 60) String label,
                                                           @NotBlank @Length(max = 90) String description) {

        projectService.newProject(label, description);

        ProjectMetaResponse response = new ProjectMetaResponse();
        response.setLabel(label);
        response.setDescription(description);

        return ManagerResponse.buildSuccess(response);
    }

    @GetMapping
    @Valid
    public ManagerResponse<PageVo<ProjectMetaResponse>> listProjects(@RequestParam(required = false, defaultValue = "1") @Length(min = 1, max = 999) int pageNo,
                                                                     @RequestParam(required = false, defaultValue = "30") @Length(min = 1, max = 999) int pageSize,
                                                                     @RequestParam(required = false) @Length(min = 1, max = 60) String label) {
        Page<ModelProject> page = projectService.listProjects(pageNo, pageSize, Optional.ofNullable(label));

        PageVo<ProjectMetaResponse> pageVo = PageVo.<ProjectMetaResponse>builder()
                .pageNo((int) page.getCurrent())
                .pageSize((int) page.getSize())
                .totalPage((int) Math.ceil((double) page.getTotal() / page.getSize()))
                .totalSize((int) page.getTotal())
                .data(
                        page.getRecords().stream().map(modelProject -> {
                            ProjectMetaResponse response = new ProjectMetaResponse();
                            response.setLabel(modelProject.getLabel());
                            response.setDescription(modelProject.getDescription());
                            return response;
                        }).collect(Collectors.toList())
                )
                .build();

        return ManagerResponse.buildSuccess(pageVo);
    }

    public static void main(String[] args) {
        System.out.println(Math.ceil(((double) 7) / 2));
    }
}
