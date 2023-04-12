package com.example.springboottest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "record")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "username")
    String  username;
    @Column(name = "device_id")
    int device_id;
    @Column(name = "request_time")
    Date request_time;
    @Column(name = "begin_time")
    Date begin_time;
    @Column(name = "end_time")
    Date end_time;
    @Column(name = "state")
    String state;//四个状态，提交中，审核中，通过，未通过
    @Column(name = "purpose")
    String purpose;

    public int getDevice_id() {
        return device_id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Date getRequest_time() {
        return request_time;
    }
    public Date getBegin_time() {
        return begin_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public String getState() {
        return state;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRequest_time(Date request_ime) {
        this.request_time = request_ime;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
