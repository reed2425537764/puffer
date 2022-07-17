package cn.syq.puffer.business.model.project.service;

import cn.syq.puffer.business.model.api.HisType;
import cn.syq.puffer.business.model.api.ProjectDomainService;
import cn.syq.puffer.business.utils.ModelUtils;
import cn.syq.puffer.dao.sql.entity.ModelProject;
import cn.syq.puffer.dao.sql.entity.ModelProjectHis;
import cn.syq.puffer.dao.sql.mapper.ModelProjectHisMapper;
import cn.syq.puffer.dao.sql.mapper.ModelProjectMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/16 20:52
 */
@Service
public class ProjectDomainServiceImpl implements ProjectDomainService {

    @Autowired
    private ModelProjectMapper modelProjectMapper;

    @Autowired
    private ModelProjectHisMapper modelProjectHisMapper;

    @Override
    public ModelProject queryByLabel(String label) {
        LambdaQueryWrapper<ModelProject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ModelProject::getLabel, label);
        return modelProjectMapper.selectOne(wrapper);
    }

    @Override
    public ModelProject addProject(String label, String description) {
        ModelProject modelProject = new ModelProject();
        modelProject.setArtifactId(ModelUtils.generateModelName());
        modelProject.setGroupId(ModelUtils.generateModelName());
        modelProject.setLabel(label);
        modelProject.setDescription(description);
        modelProject.setHisId(-1L);
        modelProject.setDeployId(-1L);
        modelProjectMapper.insert(modelProject);
        return modelProject;
    }

    @Override
    public long addProjectHis(String label, String description, ModelProject modelProject) {
        ModelProjectHis modelProjectHis = new ModelProjectHis();
        BeanCopier beanCopier = BeanCopier.create(ModelProject.class, ModelProjectHis.class, false);
        beanCopier.copy(modelProject, modelProjectHis, null);
        modelProjectHis.setId(null);
        modelProjectHis.setComment("new");
        modelProjectHis.setType(HisType.A.name());
        modelProjectHis.setForeignId(modelProject.getId());
        modelProjectHisMapper.insert(modelProjectHis);
        return modelProjectHis.getId();
    }

    @Override
    public void updateById(long id, Optional<Long> hisId, Optional<Long> deployId) {
        /*LambdaUpdateWrapper<ModelProject> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(hisId.isPresent(), ModelProject::getHisId, hisId.orElse(null));
        updateWrapper.set(deployId.isPresent(), ModelProject::getDeployId, deployId.orElse(null));
        updateWrapper.eq(ModelProject::getId, id);*/
        ModelProject modelProject = new ModelProject();
        modelProject.setId(id);
        hisId.ifPresent(modelProject::setHisId);
        deployId.ifPresent(modelProject::setDeployId);
        modelProjectMapper.updateById(modelProject);
    }
}
