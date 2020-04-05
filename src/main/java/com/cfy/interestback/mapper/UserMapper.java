package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Select("select count(1) from user where state = 1")
    Integer countNum();

    @Select("select * from user where state != 0")
    List<User> getList();

    @Update({
            "<script>"
                    + "update user set state = 0 where state !=0 and id in "
                    + "<foreach item = 'item' index = 'index' collection = 'uid' open='(' separator = ',' close=')'>"
                    + "#{item}"
            +"</foreach>"
            +"</script>"
    })
    Integer deleteMore(@Param("uid") Integer[] deleteList);

    @Update("update user set state = 2 where id = #{id} and state = 1")
    Integer stopById(Integer id);

    @Update("update user set state = 1 where id = #{id} and state = 2")
    Integer starById(Integer id);

    @Update("update user set state = 0 where id = #{id} and state != 0")
    Integer deleteByUid(Integer id);

    @Select("select * from user where state = 0")
    List<User> getDelList();

    @Select("select * from user where state !=0 and (name like #{search} or id = #{search})")
    List<User> getListBySearch(String search);

    @Select("select * from user where state = 0 and (name like #{search} or id = #{search})")
    List<User> getDelListBySearch(String search);
}
