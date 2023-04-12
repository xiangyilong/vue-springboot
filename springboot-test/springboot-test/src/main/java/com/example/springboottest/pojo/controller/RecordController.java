package com.example.springboottest.pojo.controller;

import com.example.springboottest.pojo.Record;
import com.example.springboottest.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class RecordController {
    @Autowired
    RecordService recordService;

    @CrossOrigin
    @PostMapping(value = "api/addRequest")
    @ResponseBody
    public Record addOrUpdate(@RequestBody Record record) throws Exception{
        record.setRequest_time(new Date(System.currentTimeMillis()));
        recordService.AddOrUpdate(record);
        return record;
    }
    @CrossOrigin
    @PostMapping(value = "api/cancelRequest")
    @ResponseBody
    public void delete(@RequestParam Map<String,Object> reqMap) throws Exception{
        Integer index = Integer.parseInt(reqMap.get("id").toString());
        recordService.deleteById(index);
    }
    @CrossOrigin
    @GetMapping(value = "api/getAllRecord")
    @ResponseBody
    public List<Record> listByUsername(@RequestParam("username") String username) throws Exception{
        if("".equals(username)) {
            return null;
        }else {
            return recordService.listbyUsername(username);
        }
    }
    @CrossOrigin
    @GetMapping(value = "api/getRecordsApproving")
    @ResponseBody
    public List<Record> listByApprroving() {
        return recordService.listByApproving();
    }

    @CrossOrigin
    @GetMapping(value = "api/getRecordsApproved")
    @ResponseBody
    public List<Record> listByApproved() {
        return recordService.listByApproved();
    }

}