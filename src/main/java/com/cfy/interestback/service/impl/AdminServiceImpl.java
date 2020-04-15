package com.cfy.interestback.service.impl;

import com.cfy.interestback.mapper.AdminMapper;
import com.cfy.interestback.model.Admin;
import com.cfy.interestback.service.AdminService;
import com.cfy.interestback.utils.MD5Utils;
import com.cfy.interestback.vo.AjaxMessage;
import com.cfy.interestback.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin login(Admin admin) {
        String password = MD5Utils.MD5Encode(admin.getPassword());
        Admin resultAdmin = adminMapper.login(admin.getUsername(),password);
        return resultAdmin;
    }

    @Override
    public List<Admin> getList(SearchVo searchVo) {
        return adminMapper.getList();
    }

    @Override
    public List<Admin> getList(Integer adminId,SearchVo searchVo) {
        return adminMapper.getList(adminId);
    }

    @Override
    public List<Admin> getDelList(SearchVo searchVo) {
        return null;
    }

    @Override
    public AjaxMessage add(Admin admin) {
        return null;
    }

    @Override
    public AjaxMessage update(Admin admin) {
        return null;
    }

    @Override
    public List<Admin> getListBySearch(String search) {
        return null;
    }

    @Override
    public List<Admin> getDelListBySearch(String search) {
        return null;
    }

    @Override
    public AjaxMessage delete(Integer id) {
        return null;
    }

    @Override
    public Integer deleteMore(Integer[] deleteList) {
        return null;
    }
}
