package com.cfy.interestback.controller;

import com.cfy.interestback.model.Article;
import com.cfy.interestback.model.ArticleComment;
import com.cfy.interestback.model.Circle;
import com.cfy.interestback.service.ArticleService;
import com.cfy.interestback.vo.AjaxMessage;
import com.cfy.interestback.vo.DeleteReplyVo;
import com.cfy.interestback.vo.GetArticleVo;
import com.cfy.interestback.vo.SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService service;


    @Value("${com.cfy.interest.pageSize}")
    private Integer pageSize;

    @GetMapping("/get/index/articleList")
    public String getList(SearchVo searchVo, Model model) {
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<Article> list = service.getList(searchVo);
            log.info("list = " +list);
            //封装分页
            PageInfo<Article> pageInfo = new PageInfo<Article>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);

            String start = searchVo.getStart();
            String end = searchVo.getEnd();
            String search = searchVo.getSearch();

            String paramsUrl = "&";
            Integer paramsNum = 0;
            if (start != null && !start.equals("")) {

                if (paramsNum != 0) {
                    paramsUrl += "&";
                }
                paramsUrl += "start=" + start;
                model.addAttribute("start", start);
                paramsNum++;
            }

            if (end != null && !end.equals("")) {
                if (paramsNum != 0) {
                    paramsUrl += "&";
                }
                paramsUrl += "end="+end;
                model.addAttribute("end", end);
                paramsNum++;
            }
            if (search != null && !search.equals("")) {
                if (paramsNum != 0) {
                    paramsUrl += "&";
                }
                paramsUrl += "search="+search;
                paramsNum++;
                model.addAttribute("search", search);
            }

            if (paramsNum != 0) {
                model.addAttribute("paramUrl", paramsUrl);
            }
        }finally {
            PageHelper.clearPage();
        }
        return "article-list";
    }

    @GetMapping("/get/index/articleListByCid")
    public String getListByCid(@RequestParam("pageNum")Integer pageNum, @RequestParam("cid") Integer cid,Model model) {
        //启动分页
        PageHelper.startPage(pageNum, pageSize);
        try {
            //查询数据
            List<Article> list = service.getList(cid);
            log.info("list = " +list);
            //封装分页
            PageInfo<Article> pageInfo = new PageInfo<Article>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);


        }finally {
            PageHelper.clearPage();
        }
        return "article-list";
    }

    @GetMapping("/get/index/articleDel")
    public String getDelList(SearchVo searchVo, Model model) {
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<Article> list = service.getDelList(searchVo);
            log.info("list = " +list);
            //封装分页
            PageInfo<Article> pageInfo = new PageInfo<>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);

            String start = searchVo.getStart();
            String end = searchVo.getEnd();
            String search = searchVo.getSearch();

            String paramsUrl = "&";
            Integer paramsNum = 0;
            if (start != null && !start.equals("")) {

                if (paramsNum != 0) {
                    paramsUrl += "&";
                }
                paramsUrl += "start=" + start;
                model.addAttribute("start", start);
                paramsNum++;
            }

            if (end != null && !end.equals("")) {
                if (paramsNum != 0) {
                    paramsUrl += "&";
                }
                paramsUrl += "end="+end;
                model.addAttribute("end", end);
                paramsNum++;
            }
            if (search != null && !search.equals("")) {
                if (paramsNum != 0) {
                    paramsUrl += "&";
                }
                paramsUrl += "search="+search;
                paramsNum++;
                model.addAttribute("search", search);
            }

            if (paramsNum != 0) {
                model.addAttribute("paramUrl", paramsUrl);
            }
        }finally {
            PageHelper.clearPage();
        }
        return "article-del";
    }

    @GetMapping("/get/index/articleDelByCid")
    public String getDelListByCid(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("cid")Integer cid, Model model) {
        //启动分页
        PageHelper.startPage(pageNum, pageSize);
        try {
            //查询数据
            List<Article> list = service.getDelList(cid);
            log.info("list = " +list);
            //封装分页
            PageInfo<Article> pageInfo = new PageInfo<>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);
        }finally {
            PageHelper.clearPage();
        }
        return "article-del";
    }

    @PostMapping("article/delete/more")
    @ResponseBody
    public AjaxMessage deleteMore(@RequestParam(value = "ids[]") Integer[] deleteList) {
        log.info("要删除得id为：" + Arrays.toString(deleteList));

        //删除
        Integer integer = null;
        try {
            integer = service.deleteMore(deleteList);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxMessage(false, "删除失败");
        }
        log.info("删除了" + integer + "个帖子");

        return new AjaxMessage(true, "删除成功");
    }

    @GetMapping("/get/article/content")
    public String getArticleContent(@RequestParam("aid")Integer aid, Model model) {
        Article article = service.getArticleById(aid);
        model.addAttribute("article", article);
        return "articleContent";
    }

    @GetMapping("/get/article/detail")
    public String getArticleDetail(@RequestParam("aid") Integer aid, Model model) {
        //搜索帖子
        Article article = service.getArticleById(aid);
        model.addAttribute("article", article);


        //获取该帖子所属的圈子信息
        Circle circle = service.getCircleByCid(article.getCid());
        model.addAttribute("circle", circle);

        //获取该圈子前4个成员头像
        List<String> avatarPaths = service.getCircleUserAvatarPath(circle.getId());
        model.addAttribute("avatarPaths", avatarPaths);
        log.info("article = " + article);
        log.info("article.user = " + article.getUser());
        log.info("circle = " + circle);
        log.info("avatarPaths = " + avatarPaths);
        return "article-detail";
    }

    @GetMapping("/article/comments/get/{aid}")
    @ResponseBody
    public PageInfo<ArticleComment> getComments(@PathVariable("aid") Integer aid,
                                                    @RequestParam(name = "pageNum") Integer pageNum
            , @RequestParam(name="pageSize",defaultValue = "10")Integer pageSize, HttpServletRequest request) {
        if (pageNum ==null||pageNum < 0) {
            pageNum = 0;
        }

        PageHelper.startPage(pageNum, pageSize,"create_time desc");
        List<ArticleComment> comments = null;
        try {
            comments = service.getComments(aid);
            PageInfo<ArticleComment> pageInfo = new PageInfo<>(comments, pageSize);
            return pageInfo;
        }finally {
            PageHelper.clearPage();
        }

    }

    @PostMapping("/article/comment/delete")
    @ResponseBody
    public AjaxMessage deleteComment(@RequestBody DeleteReplyVo deleteReplyVo, HttpServletRequest request) {
        try {
            return service.deleteComment(deleteReplyVo);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxMessage(false, e.getMessage());
        }
    }

    @GetMapping("/article/delete/{aid}")
    @ResponseBody
    public AjaxMessage delete(@PathVariable("aid") Integer aid, @RequestParam("cid") Integer cid,
                              HttpServletRequest request) {
        AjaxMessage ajaxMessage = null;
        try {
            ajaxMessage = service.delete(aid,cid);
        } catch (Exception e) {
            log.info("");
            e.printStackTrace();
            return new AjaxMessage(false, e.getMessage());
        }
        return ajaxMessage;
    }

    @GetMapping("/article/report/cancel/{id}")
    @ResponseBody
    public AjaxMessage delete(@PathVariable("id") Integer aid, HttpServletRequest request) {
        AjaxMessage ajaxMessage = null;
        try {
            ajaxMessage = service.reportCancel(aid);
        } catch (Exception e) {
            log.info("");
            e.printStackTrace();
            return new AjaxMessage(false, e.getMessage());
        }
        return ajaxMessage;
    }

    @PostMapping("/get/article/list")
    @ResponseBody
    public PageInfo<Article> getArticleList(@RequestBody GetArticleVo getArticleVo, HttpServletRequest request){
        log.info("articleVo = " + getArticleVo);
        //分页查询
        Integer pageNum = getArticleVo.getPageNum();
        //获取排序规则
        String sort = getArticleVo.getSort();

        PageHelper.startPage(pageNum, pageSize, sort + " desc");

        try {

            //根据cid查询对应帖子信息
            List<Article> articles = service.getArticles(getArticleVo);
            //封装分页
            PageInfo<Article> pageInfo = new PageInfo<>(articles, pageSize);
            log.info("pageInfo = " + pageInfo);
            log.info("list = " + pageInfo.getList());
            //获取总数据
            long count = pageInfo.getTotal();
            //判断查询是否大于总数据
            if (count == 0 || (pageNum - 1) * pageSize >= count) {
                return null;
            }
            return pageInfo;
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
    }
}
