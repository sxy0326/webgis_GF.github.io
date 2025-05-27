package com.design.year.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.design.year.pojo.Infortable;
import com.design.year.pojo.NewInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NewInfoMapper extends BaseMapper<NewInfo> {

    @Select("select geom from gf_info_new where satid = #{satid}")
    List<NewInfo> getNewInfoGeom(String satid);
}
