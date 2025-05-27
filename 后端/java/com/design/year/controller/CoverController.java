package com.design.year.controller;


import com.design.year.mapper.CoverMapper;
import com.design.year.result.Result;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/cover")
public class CoverController{

    @Resource
    private CoverMapper coverMapper;

   @GetMapping("/{years}")
    public Result<?> getYeasNums(@PathVariable Integer years)
    {
        return Result.success(coverMapper.getYearsNum(years));
    }

}
