package cn.syq.puffer.manager.model.controller;

import cn.syq.puffer.business.model.project.service.ProjectService;
import cn.syq.puffer.manager.model.api.ManagerResponse;
import cn.syq.puffer.manager.model.api.ProjectMetaResponse;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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

    @GetMapping("test")
    public String string() {
        return "1213";
    }
}
