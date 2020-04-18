package com.cfy.interestback.service.impl;

import com.cfy.interestback.mapper.CircleMapper;
import com.cfy.interestback.model.Circle;
import com.cfy.interestback.service.CircleService;
import com.cfy.interestback.vo.AjaxMessage;
import com.cfy.interestback.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CircleServiceImpl implements CircleService {


    @Autowired
    private CircleMapper mapper;


    @Override
    public List<Circle> getList(SearchVo searchVo) {
        return mapper.getList(searchVo);
    }

    @Override
    public List<Circle> getDelList(SearchVo searchVo) {
        return mapper.getDelList(searchVo);
    }

    @Override
    public AjaxMessage add(Circle circle) throws Exception {
        return null;
    }

    @Override
    public AjaxMessage update(Circle circle) throws Exception {
        return null;
    }

    @Override
    public List<Circle> getListBySearch(String search) {
        return null;
    }

    @Override
    public List<Circle> getDelListBySearch(String search) {
        return null;
    }

    @Override
    public AjaxMessage delete(Integer id) throws Exception {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteMore(Integer[] deleteList) throws Exception {
        //更改圈子状态
        Integer integer = mapper.deleteMore(deleteList);
        if (integer != deleteList.length) {
            throw new Exception("未删除完全");
        }
        Integer deleteArticleListSize = mapper.deleteArticleListByCid(deleteList);

        log.info("删除了多少个帖子："+deleteArticleListSize);
        return integer;
    }
}
