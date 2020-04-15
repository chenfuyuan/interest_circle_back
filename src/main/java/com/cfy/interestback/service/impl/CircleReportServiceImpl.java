package com.cfy.interestback.service.impl;

import com.cfy.interestback.mapper.CircleReportMapper;
import com.cfy.interestback.model.CircleReport;
import com.cfy.interestback.service.CircleReportService;
import com.cfy.interestback.vo.AjaxMessage;
import com.cfy.interestback.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircleReportServiceImpl implements CircleReportService {
    @Autowired
    private CircleReportMapper circleReportMapper;

    @Override
    public List<CircleReport> getList(SearchVo searchVo) {
        return circleReportMapper.getList();
    }

    @Override
    public List<CircleReport> getDelList(SearchVo searchVo) {
        return circleReportMapper.getDelList();
    }

    @Override
    public AjaxMessage add(CircleReport circleReport) throws Exception {
        return null;
    }

    @Override
    public AjaxMessage update(CircleReport circleReport) throws Exception {
        return null;
    }

    @Override
    public List<CircleReport> getListBySearch(String search) {
        return null;
    }

    @Override
    public List<CircleReport> getDelListBySearch(String search) {
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
