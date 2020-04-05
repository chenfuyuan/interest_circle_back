package com.cfy.interestback.service;

import com.cfy.interestback.vo.AjaxMessage;

import java.util.List;

public interface CommonService<T> {

    List<T> getList();

    List<T> getDelList();

    AjaxMessage add(T t)throws Exception;

    AjaxMessage update(T t)throws Exception;

    List<T> getListBySearch(String search);

    List<T> getDelListBySearch(String search);

    AjaxMessage delete(Integer id) throws Exception;

    Integer deleteMore(Integer[] deleteList);
}
