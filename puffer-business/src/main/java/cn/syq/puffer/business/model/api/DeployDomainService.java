/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.api;

import cn.syq.puffer.dao.sql.entity.ModelDeploy;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Optional;

/**
 *
 * @author shiyuqin
 * @date 2022/7/21 17:00
 */
public interface DeployDomainService {
    
    Page<ModelDeploy> listDeployments(Page<ModelDeploy> page, Optional<Long> projectIdOpt);
}
