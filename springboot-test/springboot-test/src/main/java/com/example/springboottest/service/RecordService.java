package com.example.springboottest.service;

import com.example.springboottest.dao.RecordDao;
import com.example.springboottest.pojo.Record;
import com.example.springboottest.pojo.controller.LibraryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecordService {
    @Autowired
    RecordDao recordDao;

    public List<Record> listbyUsername(String username) {
        return recordDao.findAllByUsername(username);
    }

    public void AddOrUpdate(Record record) {
        recordDao.save(record);
    }
    public void deleteById(int id){
        recordDao.deleteById(id);
    }

    public Record findById(int id){
        return recordDao.findById(id).get();
    }

    public List<Record> listByApproving() {
       return recordDao.findByStateLike("提交中");
    }

    public List<Record> listByApproved(){
        return recordDao.findByStateNotLike("提交中");
    }
}
