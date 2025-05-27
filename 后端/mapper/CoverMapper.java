package com.design.year.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.design.year.pojo.Cover;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface CoverMapper extends BaseMapper<Cover> {

    @Select("select * from cover where years = #{years}")
    List<Cover> getYearsNum(Integer years);
}
