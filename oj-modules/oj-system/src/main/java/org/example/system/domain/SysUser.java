package org.example.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.common.core.domain.BaseEntity;

@TableName("tb_sys_user")
@Getter
@Setter
@ToString
public class SysUser extends BaseEntity {

    @TableId(value = "USER_ID", type = IdType.ASSIGN_ID)
    private Long userId;

    private String userAccount;

    private String password;
}

