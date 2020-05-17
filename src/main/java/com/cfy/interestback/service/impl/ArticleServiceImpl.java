package com.cfy.interestback.service.impl;

import com.cfy.interestback.mapper.*;
import com.cfy.interestback.model.Article;
import com.cfy.interestback.model.ArticleComment;
import com.cfy.interestback.model.Circle;
import com.cfy.interestback.service.ArticleService;
import com.cfy.interestback.vo.AjaxMessage;
import com.cfy.interestback.vo.DeleteReplyVo;
import com.cfy.interestback.vo.GetArticleVo;
import com.cfy.interestback.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper mapper;

    @Autowired
    private CircleMapper circleMapper;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Autowired
    private ArticleCommentReplyMapper articleCommentReplyMapper;

    @Autowired
    private ArticleStarMapper articleStarMapper;

    @Autowired
    private ArticleLikeMapper articleLikeMapper;

    @Autowired
    private ArticleReportMapper articleReportMapper;

    @Override
    public List<Article> getList(Integer cid) {
        return mapper.getListByCid(cid);
    }

    @Override
    public List<Article> getDelList(SearchVo searchVo) {
        return mapper.getDelList(searchVo);
    }


    @Override
    public List<Article> getDelList(Integer cid) {
        return mapper.getDelListByCid(cid);
    }

    @Override
    public Article getArticleById(Integer aid) {
        return mapper.getArticleDetail(aid);
    }

    @Override
    public Circle getCircleByCid(Integer cid) {
        return circleMapper.selectById(cid);
    }

    @Override
    public List<String> getCircleUserAvatarPath(Integer cid) {
        return circleMapper.getCircleUserAvatarPath(cid);
    }

    @Override
    public List<ArticleComment> getComments(Integer aid) {
        List<ArticleComment> articleCommentShows = articleCommentMapper.selectByAid(aid);

        return articleCommentShows;
    }

    @Override
    public List<Article> getList(SearchVo searchVo) {
        return mapper.getList(searchVo);
    }


    @Override
    public AjaxMessage add(Article article) throws Exception {
        return null;
    }

    @Override
    public AjaxMessage update(Article article) throws Exception {
        return null;
    }

    @Override
    public List<Article> getListBySearch(String search) {
        return null;
    }

    @Override
    public List<Article> getDelListBySearch(String search) {
        return null;
    }

    @Override
    public AjaxMessage delete(Integer id) throws Exception {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteMore(Integer[] deleteList) throws Exception {
        Integer integer = mapper.deleteMore(deleteList);
        if (integer != deleteList.length) {
            throw new Exception("未删除完全");
        }
        //删除与帖子相关得评论和回复
        // 获取所要删除的评论
        List<Integer> commnetIdList = mapper.selectCommentByAids(deleteList);
        log.info("commentIdList = " + commnetIdList);
        //删除评论
        Integer deleteCommentSize = mapper.deleteCommentByAids(deleteList);
        log.info("根据帖子删除的评论数量为" + deleteCommentSize);
        //删除回复
        Integer deleteReplySize = mapper.deleteReplyByRuid(commnetIdList);
        log.info("根据评论删除的回复数为："+deleteReplySize);
        return integer;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxMessage deleteComment(DeleteReplyVo deleteReplyVo) throws Exception {
        Integer aid = deleteReplyVo.getAid();
        Integer acid = deleteReplyVo.getAcid();
        Integer cid = deleteReplyVo.getCid();
        //删除评论
        int changeRow = articleCommentMapper.deleteByAcId(acid);

        if (changeRow < 1) {
            throw new Exception("该评论不存在");
        }

        //删除与评论相关的回复
        int deleteNum = articleCommentReplyMapper.deleteByAcid(acid);

        //更新帖子评论数据
        changeRow = mapper.deleteComment(aid, deleteNum + 1);
        if (changeRow < 1) {
            throw new Exception("评论与帖子不匹配");
        }



        return new AjaxMessage(true, "删除评论成功");

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxMessage delete(Integer aid, Integer cid) throws Exception {
        int changeCircleRow = circleMapper.deleteArticle(cid);
        if (changeCircleRow < 1) {
            throw new Exception("该帖子所属的圈子不存在");
        }

        int changeArticleRow =  mapper.deleteByAid(aid);
        if (changeArticleRow < 1) {
            throw new Exception("该帖子不存在");
        }

        //删除收藏，置顶,点赞等信息
        articleStarMapper.cancelStarByAid(aid);
        articleLikeMapper.cancelLikeByAid(aid);

        //处理与之有关的相关举报信息
        int i = articleReportMapper.dealALLByAid(aid);
        log.info("处理了" + i + "条举报数据");

        return new AjaxMessage(true, "删除帖子成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxMessage reportCancel(Integer id) throws Exception {
        int changeRow = articleReportMapper.cancel(id);
        if (changeRow == 0) {
            throw new Exception("处理失败");
        }

        return new AjaxMessage(true, "处理成功");
    }

    @Override
    public List<Article> getArticles(GetArticleVo getArticleVo) {
        String type = getArticleVo.getType();
        int cid = getArticleVo.getCid();
        String search = getArticleVo.getSearch();
        List<Article> articles;
        //设置帖子类型
        if (type.equals("essence")) {
            //设置查找帖子类型为精华贴
            if (search.equals("")) {
                articles = mapper.findEssenceByCid(cid);
            } else {
                search = "%" + search + "%";
                articles = mapper.findEssenceSearchByCid(cid, search);
            }
        } else {
            if (search.equals("")) {
                articles = mapper.findByCid(cid);
            } else {
                search = "%" + search + "%";
                articles = mapper.findSearchByCid(cid, search);
            }
        }
        return articles;
    }
}
