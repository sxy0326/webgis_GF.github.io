package com.design.year.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.design.year.pojo.Files;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface FilesMapper extends BaseMapper<Files> {

    @Select("select filename,id from files")
    List<Files> getFilePath();
}
