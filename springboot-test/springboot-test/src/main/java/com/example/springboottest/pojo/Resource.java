package com.example.springboottest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "resource")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "state")
    String state;//三个状态，空闲，使用中，维修中
    @Column(name = "end_time")
    Date end_time;
    @Column(name = "cpu")
    int cpu;
    @Column(name = "gpu")
    int gpu;

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public int getCpu() {
        return cpu;
    }

    public int getGpu() {
        return gpu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public void setGpu(int gpu) {
        this.gpu = gpu;
    }
}
