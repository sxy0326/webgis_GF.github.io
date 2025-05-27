package com.design.year.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.design.year.pojo.Geoserver;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface GeoserverMapper extends BaseMapper<Geoserver> {

}
