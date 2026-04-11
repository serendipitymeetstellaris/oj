package org.example.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.example.common.core.constants.Constants;
import org.example.common.core.utils.ThreadLocalUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());

        String userId = ThreadLocalUtil.get(Constants.USER_ID, String.class);
        if (userId != null && !userId.trim().isEmpty()) {
            this.strictInsertFill(metaObject, "createBy", Long.class, Long.valueOf(userId));
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

        String userId = ThreadLocalUtil.get(Constants.USER_ID, String.class);
        if (userId != null && !userId.trim().isEmpty()) {
            this.strictUpdateFill(metaObject, "updateBy", Long.class, Long.valueOf(userId));
        }
    }
}