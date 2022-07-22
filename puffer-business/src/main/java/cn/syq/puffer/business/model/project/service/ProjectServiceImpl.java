package cn.syq.puffer.business.model.project.service;

import cn.syq.puffer.business.exception.ManagerErrorCode;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.business.model.api.DeployDomainService;
import cn.syq.puffer.business.model.api.ProjectDomainService;
import cn.syq.puffer.dao.sql.entity.ModelDeploy;
import cn.syq.puffer.dao.sql.entity.ModelProject;
import cn.syq.puffer.dao.sql.entity.ModelProjectHis;
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
    
    @Autowired
    private DeployDomainService deployDomainService;

    @Override
    public void newProject(String label, String description) {
        ModelProject modelProject = projectDomainService.queryByLabel(label);
        if (Objects.nonNull(modelProject)) {
            throw new ManagerException(ManagerErrorCode.E10, "该项目名已存在");
        }

        modelProject = projectDomainService.addProject(label, description);

        ModelProjectHis modelProjectHis = projectDomainService.addProjectHis(label, description, modelProject);

        projectDomainService.updateById(modelProject.getId(), Optional.of(modelProjectHis.getId()), Optional.empty());
    }

    @Override
    public Page<ModelProject> listProjects(int pageNo, int pageSize, Optional<String> labelOpt) {
        Page<ModelProject> page = new Page<>(pageNo, pageSize);
        //TODO EXCEPT QUERY WITH LABEL, SHOULD ALSO only QUERY the project that user have this relate the table of user and privileged so get it done later
        return projectDomainService.listAllProjects(page, labelOpt/*labelOpt.map(label -> String.format("%%%s%%", label))*/);
    }

    public static void main(String[] args) {
        System.out.println(String.format("%%%s%%", "label"));
    }

    @Override
    public void editProjectMeta(Long id, Optional<String> labelOpt, Optional<String> descriptionOpt) {
        final ModelProject modelProject = projectDomainService.checkProjectExist(id);

        if (labelOpt.isPresent()) {
            if (!Objects.equals(modelProject.getLabel(), labelOpt.get())) {
                ModelProject modelProjectByLabel = projectDomainService.queryByLabel(labelOpt.get());
                if (Objects.isNull(modelProjectByLabel)) {
                    throw new ManagerException(ManagerErrorCode.E30);
                }
            } else {
                labelOpt = Optional.empty();
            }
        }
        
        if (Objects.equals(modelProject.getDescription(), descriptionOpt.orElse(null))) {
            descriptionOpt = Optional.empty();
        }
        
        if (labelOpt.isPresent() || descriptionOpt.isPresent()) {
            final ModelProjectHis modelProjectHis = projectDomainService.addProjectHis(labelOpt.get(), descriptionOpt.get(), modelProject);
            projectDomainService.updateById(id, Optional.of(modelProjectHis.getId()), Optional.empty());
        } else {
            throw new ManagerException(ManagerErrorCode.E30);
        }
    }

    @Override
    public Page<ModelDeploy> listProjectDeployments(int pageNo, int pageSize, Optional<Long> projectId) {
        if (projectId.isPresent()) {
            projectDomainService.checkProjectExist(projectId.get());
        }
        
        return deployDomainService.listDeployments(Page.of(pageNo, pageSize), projectId);
    }
}
