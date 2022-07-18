package cn.syq.puffer.business.model.project.service;

import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.ProjectDomainService;
import cn.syq.puffer.dao.sql.entity.ModelProject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/16 21:00
 */
@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectDomainService projectDomainService;

    /*@Autowired
    private ModelProjectMapper modelProjectMapper;*/

    @Override
    public void newProject(String label, String description) {
        ModelProject modelProject = projectDomainService.queryByLabel(label);
        if (Objects.nonNull(modelProject)) {
            throw new ManagerException(ManagerErrorCode.E10, "该项目名已存在");
        }

        modelProject = projectDomainService.addProject(label, description);

        long projectHisId = projectDomainService.addProjectHis(label, description, modelProject);

        projectDomainService.updateById(modelProject.getId(), Optional.of(projectHisId), Optional.empty());
    }

    @Override
    public Page<ModelProject> listProjects(int pageNo, int pageSize) {
        Page<ModelProject> page = new Page<>(pageNo, pageSize);
        return projectDomainService.listAllProjects(page);
    }
}
