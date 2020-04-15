package com.cfy.interestback.service;

import com.cfy.interestback.model.Admin;
import com.cfy.interestback.vo.SearchVo;

import java.util.List;


public interface AdminService extends CommonService<Admin> {

    Admin login(Admin admin);
    List<Admin> getList(Integer adminId, SearchVo searchVo);
}
