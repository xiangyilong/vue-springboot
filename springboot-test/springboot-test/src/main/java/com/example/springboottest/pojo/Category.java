package com.example.springboottest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "category")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;
    public int getId(){
        return id;
    }
    public void setId(){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(){
        this.name = name;
    }
}
