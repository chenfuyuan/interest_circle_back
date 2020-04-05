package com.cfy.interestback.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("admin_operation")
public class AdminOperation {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer aid;
    @TableField(exist = false)
    private Admin admin;

    private Integer oType;

    private Integer oid;

    @TableField(exist = false)
    private String operationObject;

    private Integer type;


    private LocalDateTime createTime;

    private String message;


    public void setoType(Integer oType) {
        this.oType = oType;
        switch (oType) {
            case 1:
                operationObject ="用户";
                break;
            case 2:
                operationObject = "圈子";
                break;
            case 3:
                operationObject = "帖子";
                break;
            case 4:
                operationObject = "评论";
                break;
            case 5:
                operationObject = "回复";
                break;
            case 6:
                operationObject = "圈子举报";
                break;
            case 7:
                operationObject = "帖子举报";
                break;
            case 8:
                operationObject = "管理员";
                break;
        }
    }

    private void setOperationObject(String operationObject) {

    }


}
