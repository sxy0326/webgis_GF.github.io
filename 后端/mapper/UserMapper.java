package com.design.year.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.design.year.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;



@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
//所有的CRUD都已经完成了

}
