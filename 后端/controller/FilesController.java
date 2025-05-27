package com.design.year.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.design.year.mapper.FilesMapper;
import com.design.year.pojo.Files;
import com.design.year.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.baomidou.mybatisplus.core.toolkit.Wrappers.lambdaQuery;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FilesController {

    @Resource
    FilesMapper filesMapper;

    @PostMapping
    public Result<?> save(@RequestBody Files Files)
    {
        filesMapper.insert(Files);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Files Files)
    {
        filesMapper.updateById(Files);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id)
    {
        return Result.success(filesMapper.deleteById(id));
    }
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "5") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Files> wrapper = Wrappers.<Files>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Files::getFormat, search);
        }
        Page<Files> FilesPage = filesMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(FilesPage);
    }
}
