package com.cfy.interestback.service;

import com.cfy.interestback.model.Admin;
import org.springframework.stereotype.Service;


public interface AdminService extends CommonService<Admin> {

    Admin login(Admin admin);
}
