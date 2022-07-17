package cn.syq.puffer.dao.sql.autoconfig;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/16 22:34
 */
public class MetaAutoInjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        setFieldValByName("createTime", now, metaObject);
        setFieldValByName("createUid", -1, metaObject);
        setFieldValByName("updateTime", now, metaObject);
        setFieldValByName("updateUid", -1, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        setFieldValByName("updateTime", now, metaObject);
        setFieldValByName("updateUid", -1, metaObject);
    }
}
