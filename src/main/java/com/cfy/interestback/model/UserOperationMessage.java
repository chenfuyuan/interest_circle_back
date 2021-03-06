package com.cfy.interestback.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_operation_message")
public class UserOperationMessage {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(exist = false)
    private User user;

    private Long uid;

    private String message;

    private Date datetime;

    private Integer type;

    public static final Integer CREATE = 1;
    public static final Integer UPDATE = 2;
    public static final Integer CHANGEPASSWORD = 3;
}
