package com.example.springboottest.pojo.controller;

import com.example.springboottest.dao.ResourceDao;
import com.example.springboottest.pojo.Record;
import com.example.springboottest.pojo.Resource;
import com.example.springboottest.service.RecordService;
import com.example.springboottest.service.ResourceService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class ResourceController {
    @Autowired
    ResourceService resourceService;
    @Autowired
    RecordService recordService;
    @CrossOrigin
    @GetMapping(value = "api/LoadAllResources")
    @ResponseBody
    public List<Resource> list() throws Exception {
        return resourceService.list();
    }
    @CrossOrigin
    @PostMapping(value = "api/AddOrUpdateResource")
    @ResponseBody
    public Resource AddOrUpdateResource(@RequestBody Resource resource) throws Exception{
        resourceService.AddOrUpdateResource(resource);
        return resource;
    }
    @CrossOrigin
    @ResponseBody
    @PostMapping(value = "api/passRequest")
    public void passRequest(@RequestParam Map<String,Object> reqMap) throws ParseException {
        Integer recordId = Integer.parseInt(reqMap.get("recordId").toString());
        Integer deviceId = Integer.parseInt(reqMap.get("deviceId").toString());
        System.out.println(recordId);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date end_time = ft.parse(reqMap.get("end_time").toString());
        System.out.println(end_time);

        Record record = recordService.findById(recordId);
        record.setState("通过");
        recordService.AddOrUpdate(record);

        Resource resource = resourceService.findById(deviceId);
        resource.setState("使用中");
        resource.setEnd_time(end_time);
        System.out.println(resource.getEnd_time());
        resourceService.AddOrUpdateResource(resource);
    }
    @CrossOrigin
    @ResponseBody
    @PostMapping("api/unpassRequest")
    public void unpassRequest(@RequestParam Map<String,Object>reqMap){
        Integer recordId = Integer.parseInt(reqMap.get("recordId").toString());
        Record record = recordService.findById(recordId);
        record.setState("不通过");
        recordService.AddOrUpdate(record);
    }
}
