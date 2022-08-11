/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cn.syq.puffer.business.model.dataobject.api;

import cn.syq.puffer.dao.sql.entity.ModelDo;

/**
 *
 * @author shiyuqin
 * @date 2022/8/9 18:39
 */
public interface DataObjectAppend {
    
    DataObjectMeta addDataObject(ModelDo modelDo);
}
