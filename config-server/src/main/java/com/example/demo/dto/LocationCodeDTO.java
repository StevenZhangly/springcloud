package com.example.demo.dto;

import java.io.Serializable;

/**
 * @ClassName LocationCodeDTO
 * @Description: TODO
 * @Author zly
 * @Date 2020/10/13
 **/
public class LocationCodeDTO implements Serializable {

    private String AREA_CODE;

    private String AREA_NAME;

    public String getAREA_CODE() {
        return AREA_CODE;
    }

    public void setAREA_CODE(String AREA_CODE) {
        this.AREA_CODE = AREA_CODE;
    }

    public String getAREA_NAME() {
        return AREA_NAME;
    }

    public void setAREA_NAME(String AREA_NAME) {
        this.AREA_NAME = AREA_NAME;
    }
}
