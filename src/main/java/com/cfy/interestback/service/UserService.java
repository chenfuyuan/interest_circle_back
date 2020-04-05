package com.cfy.interestback.service;

import com.cfy.interestback.model.User;
import com.cfy.interestback.vo.AjaxMessage;


public interface UserService extends CommonService<User> {
    AjaxMessage stop(Integer id) throws Exception;

    AjaxMessage star(Integer id) throws Exception;
}
