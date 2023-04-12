package com.example.springboottest.service;

import com.example.springboottest.dao.ResourceDao;
import com.example.springboottest.pojo.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
@Service
public class ResourceService {
    @Autowired
    ResourceDao resourceDao;

    public List<Resource> list(){
        return resourceDao.findAll();
    }

    public Resource findById(Integer id){
        return resourceDao.findById(id).get();
    }

    public void AddOrUpdateResource(Resource resource){
        resourceDao.save(resource);
    }
}
