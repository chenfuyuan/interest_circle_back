package com.cfy.interestback.service;

import com.cfy.interestback.model.Article;
import com.cfy.interestback.model.Circle;

import java.util.List;


public interface CircleService extends CommonService<Circle> {
    Circle getCircleByCid(Integer cid);

    List<String> getMemberAvatars(Integer cid,Integer num);

    List<Article> getStickys(Integer cid);
}
