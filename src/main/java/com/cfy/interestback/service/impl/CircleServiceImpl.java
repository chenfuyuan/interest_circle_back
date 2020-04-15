package com.cfy.interestback.service.impl;

import com.cfy.interestback.mapper.CircleMapper;
import com.cfy.interestback.model.Circle;
import com.cfy.interestback.service.CircleService;
import com.cfy.interestback.vo.AjaxMessage;
import com.cfy.interestback.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircleServiceImpl implements CircleService {


    @Autowired
    private CircleMapper mapper;


    @Override
    public List<Circle> getList(SearchVo searchVo) {
        return mapper.getList();
    }

    @Override
    public List<Circle> getDelList(SearchVo searchVo) {
        return mapper.getDelList();
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

    @Override
    public Integer deleteMore(Integer[] deleteList) {
        return null;
    }
}
