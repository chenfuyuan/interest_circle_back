package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.ArticleComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {

    @Select("select count(1) from article_comment where state = 1 ")
    Integer countNum();

    @Select("select * from article_comment where aid = #{aid} and state!=0")
    @Results(id="commentMap",value={
            @Result(property = "uid", column = "uid"),
            @Result(property = "user",column = "uid",one=@One(select = "com.cfy.interestback.mapper.UserMapper" +
                    ".selectById")),
            @Result(property = "id", column = "id"),
            @Result(property ="replys",column = "id",javaType = List.class,many = @Many(select = "com.cfy" +
                    ".interestback" +
                    ".mapper.ArticleCommentReplyMapper.selectReplys"))
    })
    List<ArticleComment> selectByAid(Integer aid);

    @Update("update article_comment set state = 0 where id = #{acid}")
    int deleteByAcId(Integer acid);

    @Update("update article_comment set reply_num = reply_num -1 where id = #{acid} and state !=0")
    int deleteReply(Integer acid);
}
