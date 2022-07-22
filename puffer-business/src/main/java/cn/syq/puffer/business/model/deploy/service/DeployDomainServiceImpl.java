/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.deploy.service;

import cn.syq.puffer.business.model.api.DeployDomainService;
import cn.syq.puffer.dao.sql.entity.ModelDeploy;
import cn.syq.puffer.dao.sql.mapper.ModelDeployMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shiyuqin
 * @date 2022/7/21 17:11
 */
@Service
public class DeployDomainServiceImpl implements DeployDomainService{
    
    @Autowired
    private ModelDeployMapper modelDeployMapper;

    @Override
    public Page<ModelDeploy> listDeployments(Page<ModelDeploy> page, Optional<Long> projectIdOpt) {
        LambdaQueryWrapper<ModelDeploy> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(projectIdOpt.isPresent(), ModelDeploy::getProjectId, projectIdOpt.get());
        return modelDeployMapper.selectPage(page, wrapper);
    }
    
}
