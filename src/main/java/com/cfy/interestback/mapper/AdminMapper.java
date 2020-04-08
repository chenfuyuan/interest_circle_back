package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.Admin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {


    @Select("select * from admin where state = 1 and username = #{username} and password = #{password}")
    Admin login(String username,String password);


    @Select("select * from admin where state = 1 and id != #{adminId}")
    List<Admin> getList(Integer adminId);

    @Select("select * from admin where state = 1")
    List<Admin> getList();



}
