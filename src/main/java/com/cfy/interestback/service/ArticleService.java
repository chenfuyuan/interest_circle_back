package com.cfy.interestback.service;

import com.cfy.interestback.model.Article;
import com.cfy.interestback.model.ArticleComment;
import com.cfy.interestback.model.Circle;
import com.cfy.interestback.vo.AjaxMessage;
import com.cfy.interestback.vo.DeleteReplyVo;

import java.util.List;


public interface ArticleService extends CommonService<Article> {

    List<Article> getList(Integer cid);

    List<Article> getDelList(Integer cid);

    Article getArticleById(Integer aid);

    Circle getCircleByCid(Integer cid);

    List<String> getCircleUserAvatarPath(Integer id);

    List<ArticleComment> getComments(Integer aid);

    AjaxMessage deleteComment(DeleteReplyVo deleteReplyVo) throws Exception;

    AjaxMessage delete(Integer aid, Integer cid) throws Exception;

    AjaxMessage reportCancel(Integer aid) throws Exception;
}
