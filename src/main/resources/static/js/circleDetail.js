$(function () {
    //获取圈子Id
    var cid = $("#main-content").data("aid");
    //声明变量
    var pageNum;    //页数
    var search;    //查询结果
    var sort;    //排序规则
    var type;    //帖子类型
    var $btnNormal;    //动态
    var $btnEssence;    //精华
    var $btnSort;    //排序选择按钮
    var $sortMenu;    //排序菜单
    var $sortPublish;    //最新发布
    var $sortReply;    //最新回复
    var $post_list;    //帖子列表
    var $deleteModel;    //删除模态框
    var $deleteConfirm;    //删除确认按钮
    var $deleteCancel;    //删除取消按钮
    var total;    //总数据
    var $btnSearch;    //搜索按钮
    var $inputSearch;    //搜索输入框


    // 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")   ==> 2006-7-2 8:9:4.18
    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

    /**
     * 初始化变量
     */
    var init = function () {
        cid = $("#main-content").data("cid");
        pageNum = 1;
        search = "";
        sort = "create_time";
        type = "normal";
        $btnNormal = $("#normal");
        $btnEssence = $("#essence");
        $btnSort = $("#sort");
        $sortMenu = $("#sort-menu");
        $sortPublish = $("#sort-publish");
        $sortReply = $("#sort-reply");
        $post_list = $(".post-lists");
        $deleteModel = $("#article-delete-model");
        $deleteConfirm = $("#btn-delete-confirm");
        $deleteCancel = $("#btn-delete-cancel");

    };


    /**
     * 添加帖子列表各个按钮监听器
     */
    addArticleListEvent = function addArticleListEvent() {
        var $articleMenu = $(".article-operation-menu-btn");    //帖子菜单按钮

        /**
         * 帖子右侧操作菜单 点击显示菜单
         */
        $articleMenu.unbind("click");    //解除绑定点击事件
        $articleMenu.click(function () {    //设置绑定事件 菜单显示与隐藏
            $(this).siblings(".more-dropdown-menu").toggle();
            var list = $(this).parents(".post-list-item");
            list.siblings().find(".more-dropdown-menu").hide();
        });

        /**
         * 帖子删除
         */
        var $itemDelete = $(".item-delete");    //菜单 删除项

        $itemDelete.unbind("click");    //解除绑定点击事件
        $itemDelete.click(function () {
            var deleteBtn = $(this);    //获取当前要删除的帖子
            var aid = deleteBtn.parents(".post-list-item").data("aid");    //获取当前要删除的帖子Id
            $deleteConfirm.data("aid", aid);    //设置aid
            $deleteModel.show();    //显示删除窗口
        });

    };



    /**
     * 填充帖子列表html
     */
    var fillArticleList = function (articles) {
        console.log("填充帖子列表");

        //遍历帖子列表，填充到页面中
        for (var i = 0; i < articles.length; i++) {
            //获取帖子
            var article = articles[i];
            var user = article.user;
            var date = new Date(article.createTime).Format("yyyy-MM-dd hh:mm:ss");
            var aid = article.id;
            var src = "/static/image/like.png";

            //菜单栏选项
            var dropmenu ="<div class='more-dropdown-menu topic-cell-dropdown-menu' style='display: none'><div" +
                " class='more-dropdown-item-list' data-aid='"+aid+"'><div class='more-dropdown-item item-delete'><div class='handle-icon-wrapper'><img\" +\n" +
                "                    \" src='/static/image/articleDelete.svg' class='handle-icon'></div> <span>删除帖子</span></div></div></div>";

            //填充的html
            var str = "<div class='post-list-item' data-aid='" + aid + "'><div class='wrap' data-aid='" + aid + "'><div class='user-info'><div class='left-side'><div class='user-avatar'><img src='" + user.avatarPath + "' alt='' class='avatar-head'></div>" +
                "<div><p class='user-name'><span class=''>" + user.name + "</span></p>" +
                "<p class='post-time'>" + date + "</p></div></div><div>" +
                "<div class='right-side'><span class='more-dropdown-box more-dropdown'><div" +
                " class='user-img'><div class='article-operation-menu-btn'><i" +
                " class='more-icon-option option-icon icon-btn glyphicon glyphicon-list'></i></div>" + dropmenu + "</div></span></div></div></div>" +
                "<div class='topic-text'><div style='left: 0px; position: static; width: 480px;'><h3>" + article.title + "</h3>" + article.content + "</div></div><div" +
                " isopenwindow='true'>" +
                "<div class='reactions'><span class='likes' data-aid='" + aid + "'><img src='" + src + "'" +
                " class='more-icon-appreciation icon-btn'/><span" +
                " class=''>赞 <span class='likes-num'>" + article.likeNum + "</span></span></span>" +
                "             <span class='comments'>" +
                "<i class='more-icon-comment icon-btn glyphicon glyphicon-comment'></i>" +
                "                         <span class='comments-count'>评论 <span class='comments-num'>" + article.commentNum + "</span></span>" +
                "                     </span>" +
                "             <span class='views-count'></span></div></div></div></div>";

            $post_list.append(str);
        }
        addArticleListEvent();
    };
    /**
     * 初始化圈子列表
     */
    var getArticleList = function () {
        console.log("初始化圈子帖子列表");
        var data = new Object();

        //封装查询条件
        data["sort"] = sort;
        data["type"] = type;
        data["pageNum"] = pageNum;
        data["cid"] = cid;
        data["search"] = search;
        var jsonData = JSON.stringify(data);    //转化成json数据

        //异步从后台获取数据进行填充
        $.ajax({
            url: "/get/article/list",
            contentType: "application/json",
            type: "POST",
            dataType: "JSON",
            data: jsonData,
        }).done(function (re) {
            console.log("re = " + re.list);
            var articles = re.list;
            total = re.total;
            pageSize = re.pageSize;
            fillArticleList(articles);
        });

    };

    /**
     * 初始化按钮事件
     */
    var initBtnEvent = function () {
        //模态框 取消按钮点击事件
        $deleteCancel.click(function () {
            $deleteModel.hide();    //隐藏模态框
        });

        //模态框 点击确认
        $deleteConfirm.click(function () {
            var aid = $(this).data("aid");    //获取aid
            $.get("/article/delete/"+aid+"?cid="+cid,function (re) {
                if(re.success){
                    alert(re.message);
                    $(".post-list-item[data-aid='" + aid + "']").remove();
                }else{
                    alert(re.message);
                }
                $(".more-dropdown-menu ").hide();
                $deleteModel.hide();
            })

        });

        //点击动态 显示所有动态
        $btnNormal.click(function () {
            $post_list.html("");
            type = "normal";
            pageNum = 1;
            getArticleList();
            $btnNormal.addClass("active");
            $btnEssence.removeClass("active");
        });

        //点击精华 显示精华动态
        $btnEssence.click(function () {
            $post_list.html("");
            type = "essence";
            pageNum = 1;
            getArticleList();
            $btnEssence.addClass("active");
            $btnNormal.removeClass("active");
        });

        //点击排序按钮 显示排序菜单
        $btnSort.click(function () {
            $sortMenu.toggle();
        });

        //点击排序菜单按钮项
        $(".sort-menu-item").click(function () {
            $(".sort-menu-item .check-icon").remove();    //移除被选中
            //添加被
            $(this).append("<img src='/static/image/checked.svg' class='check-icon'>");

            $(this).siblings(".sort-menu-item").remove("img");
            sort = $(this).data("sort");
            console.log("sort切换为" + sort);

            var text = $(this).children("span").text();

            //更换内容
            $("#sort").text(text);

            //清空帖子列表
            $post_list.html("");
            pageNum = 1;
            getArticleList();

            //隐藏菜单
            $("#sort-menu").toggle();



        });


        //判断浏览器是否在最底部
        $(window).scroll(function(){
            var h=$(document.body).height();//网页文档的高度
            var c = $(document).scrollTop();//滚动条距离网页顶部的高度
            var wh = $(window).height(); //页面可视化区域高度

            if (Math.ceil(wh+c)>=h){
                console.log("已经到达浏览器底部，获取新的帖子");
                pageNum = pageNum + 1;
                if ((pageNum - 1) * pageSize >= total) {
                    $("#end").show();
                    return;
                } else {
                    console.log("向后台请求数据");
                    getArticleList();
                }
            }
        })
    }


    //入口
    var run = function () {
        //初始化参数
        init();
        //加载帖子数据
        getArticleList();
        //初始化按钮事件绑定
        initBtnEvent();
    };
    run();

});