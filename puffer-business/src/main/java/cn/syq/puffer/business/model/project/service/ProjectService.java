package cn.syq.puffer.business.model.project.service;

import cn.syq.puffer.dao.sql.entity.ModelProject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/16 21:01
 */
public interface ProjectService {
    void newProject(String label, String description);

    Page<ModelProject> listProjects(int pageNo, int pageSize);
}
