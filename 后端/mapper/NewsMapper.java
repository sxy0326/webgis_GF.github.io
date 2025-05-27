package com.design.year.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.design.year.pojo.News;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface NewsMapper extends BaseMapper<News> {

}
