package com.design.year.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.design.year.mapper.FilesMapper;
import com.design.year.mapper.NewInfoMapper;
import com.design.year.pojo.Files;
import com.design.year.pojo.JsonResult;
import com.design.year.pojo.NewInfo;
import com.design.year.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.design.year.utils.GeoUtils.wktToJson;

@CrossOrigin
@RestController
@RequestMapping("/newinfo")
public class NewInfoController {
    @Resource
    private NewInfoMapper infoMapper;

    @Resource
    private FilesMapper filesMapper;

    @CrossOrigin
    @GetMapping("/{satid}")
    public Result<?> getNewinfo(@PathVariable String satid)
    {
        List<NewInfo>  infoNum = infoMapper.getNewInfoGeom("GF1");
//        System.out.println(infoNum);.
        Map<String, Object> feature = new HashMap<>();
//        System.out.println(feature);
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(infoNum);
        System.out.println(jsonArray.size());
//        HashMap<Object, Object> feature1 = new HashMap<>();
//        Map<String, Object> map = new HashMap<>();
//        System.out.println(jsonArray.);
//        infoNum = infoNum.toArray().toString()
//        feature.put("type", "FeatureCollection");
//
//        feature.put("properties", map);
        List test = new ArrayList();
        for(int i=0;i<jsonArray.size();i++)
        {
            String wkt = jsonArray.getString(i).toString();
//            System.out.println(wkt);
            String strwkt = wkt.substring(9,wkt.length()-2);
//            System.out.println(strwkt);
            HashMap<Object, Object> jsonObject = wktToJson(strwkt,feature);
//            feature.put("features", jsonObject);
            test.add(jsonObject);
        }
        JsonResult jsonResult = new JsonResult("FeatureCollection", test);
        System.out.println("jsonResult = " + jsonResult);
        return Result.success(jsonResult);
    }
    @GetMapping("/filepath")
    public Result<?> getFilePath()
    {
        List<Files> filesList = filesMapper.getFilePath();
//        System.out.println(filesList);
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(filesList);
        System.out.println(jsonArray.size());
        System.out.println(jsonArray);
//        List test = new ArrayList();
//        for(int i=0;i<jsonArray.size();i++)
//        {
//            String filelist = jsonArray.getString(i).toString();
////            System.out.println(wkt);
//            String strwkt = filelist.substring(13,filelist.length()-11);
//            System.out.println(strwkt);
//            test.add(strwkt);
//        }
//
        return Result.success(jsonArray);
    }

}
