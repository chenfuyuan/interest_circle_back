package com.cfy.interestback.service.impl;

import com.cfy.interestback.mapper.UserMapper;
import com.cfy.interestback.model.User;
import com.cfy.interestback.service.UserService;
import com.cfy.interestback.vo.AjaxMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> getList() {
        return mapper.getList();
    }

    @Override
    public List<User> getDelList() {
        return mapper.getDelList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxMessage add(User user) throws Exception {
        Integer changRow = mapper.insert(user);
        if (changRow == 1) {
            return new AjaxMessage(true, "添加成功");
        }
        throw new Exception("添加失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxMessage update(User user) throws Exception {
        int changRow = mapper.updateById(user);
        if (changRow == 1) {
            return new AjaxMessage(true, "修改成功");
        }
        throw new Exception("修改失败");
    }

    @Override
    public List<User> getListBySearch(String search) {
        return mapper.getListBySearch(search);
    }

    @Override
    public List<User> getDelListBySearch(String search) {
        return mapper.getDelListBySearch(search);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxMessage delete(Integer id) throws Exception {
        int changeRow = mapper.deleteByUid(id);
        if (changeRow == 1) {
            return new AjaxMessage(true,"删除成功");
        } else {
            throw new Exception("数据不存在");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteMore(Integer[] deleteList) {
        //删除用户
        Integer changeRow = mapper.deleteMore(deleteList);
        return changeRow;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxMessage stop( Integer id) throws Exception {
        Integer changRow = mapper.stopById(id);
        if (changRow == 1) {
            return new AjaxMessage(true, "停用成功");
        }
        throw new Exception("停用失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxMessage star(Integer id) throws Exception {
        Integer changRow = mapper.starById(id);
        if (changRow == 1) {
            return new AjaxMessage(true, "启用成功");
        }
        throw  new Exception("启用失败");
    }


}
