package com.cfy.interestback.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenfuyuan
 * @since 2020-02-14
 */
@Data
@TableName("article_operation_message")
public class ArticleOperationMessage implements Serializable {

    public static final int CREATE = 1;
    public static final int ESSENCE = 2;
    public static final int STICKY = 3;
    public static final int LIKE = 4;
    public static final int CANCELLIKE = 5;
    public static final int STAR = 6;
    public static final int CANCELSTAR = 7;
    public static final int COMMENT = 8;
    public static final int CANCELSTICKY = 9;
    public static final int CANCELESSENCE = 10;
    public static final int DELETE = 11;
    public static final int REPORT = 12;
    public static final int DEALREPORT = 13;
    public static final int ACOMMENT = 14;
    public static final int REPLY = 15;
    public static final int DELETECOMMENT = 16;
    public static final int DELETEREPLY = 17;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    @TableId(type = IdType.AUTO)
    private Integer id;
    private Long uid;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Circle circle;
    private Integer cid;

    private Integer aid;
    @TableField(exist = false)
    private Article article;


    private String message;

    private Integer type;

    private Date createTime;

    public ArticleOperationMessage (Long uid,Integer aid,Integer type,Integer cid) {
        this.uid = uid;
        this.aid = aid;
        setType(type);
        this.cid = cid;
    }
    public void setType(Integer type) {
        this.type = type;
        switch (type) {
            case CREATE:message = "发布帖子";
                break;
            case ESSENCE:
                message = "加精帖子";
                break;
            case STICKY:
                message = "置顶帖子";
                break;
            case LIKE:
                message = "点赞帖子";
                break;
            case CANCELLIKE:
                message = "取消点赞帖子";
                break;
            case STAR:
                message = "收藏帖子";
                break;
            case CANCELSTAR:
                message = "取消收藏帖子";
                break;
            case COMMENT:
                message = "评论帖子";
                break;
            case CANCELSTICKY:
                message = "取消置顶帖子";
                break;
            case CANCELESSENCE:
                message = "取消加精帖子";
                break;
            case DELETE:
                message = "删除帖子";
                break;
            case REPORT:
                message = "举报帖子";
                break;
            case DEALREPORT:
                message = "处理举报帖子";
                break;
            case ACOMMENT:
                message = "评论帖子";
                break;
            case REPLY:
                message = "回复评论";
                break;
            case DELETECOMMENT:
                message = "删除评论";
                break;
            case DELETEREPLY:
                message = "删除回复";
                break;
        }
    }



}
