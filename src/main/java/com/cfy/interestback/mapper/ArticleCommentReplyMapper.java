package com.cfy.interestback.mapper;

import com.cfy.interestback.model.ArticleCommentReply;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleCommentReplyMapper {

    @Select("select * from article_comment_reply where acid =#{acid} and state!=0 order by create_time asc")
    @Results(id = "replyMap",value = {
            @Result(property = "uid", column = "uid"),
            @Result(property = "user",column = "uid",one=@One(select = "com.cfy.interestback.mapper.UserMapper" +
                    ".selectById")),
            @Result(property = "ruid", column = "ruid"),
            @Result(property = "replyUser",column = "ruid",one=@One(select = "com.cfy.interestback.mapper.UserMapper" +
                    ".selectById")),
    })
    List<ArticleCommentReply> selectReplys(int acid);

    @Update("update article_comment_reply set state = 0 where acid = #{acid}")
    int deleteByAcid(Integer acid);
}
