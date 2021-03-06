package cn.syq.puffer.business.model.api;

import cn.syq.puffer.dao.sql.entity.ModelProject;
import cn.syq.puffer.dao.sql.entity.ModelProjectHis;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Optional;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/16 20:54
 */
public interface ProjectDomainService {
    ModelProject queryByLabel(String label);

    ModelProject addProject(String label, String description);

    ModelProjectHis addProjectHis(String label, String description, ModelProject modelProject);

    void updateById(long id, Optional<Long> hisId, Optional<Long> deployId);

    Page<ModelProject> listAllProjects(Page<ModelProject> page, Optional<String> label);
    
    ModelProject checkProjectExist(Long id);
    
    
}
