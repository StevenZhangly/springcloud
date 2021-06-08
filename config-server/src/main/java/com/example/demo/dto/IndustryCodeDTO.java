package com.example.demo.dto;

import java.io.Serializable;

/**
 * @ClassName IndustryCodeDTO
 * @Description: TODO
 * @Author zly
 * @Date 2020/10/22
 **/
public class IndustryCodeDTO implements Serializable {

    private String INDUSTRY_CODE;

    private String CODE_NAME;

    public String getINDUSTRY_CODE() {
        return INDUSTRY_CODE;
    }

    public void setINDUSTRY_CODE(String INDUSTRY_CODE) {
        this.INDUSTRY_CODE = INDUSTRY_CODE;
    }

    public String getCODE_NAME() {
        return CODE_NAME;
    }

    public void setCODE_NAME(String CODE_NAME) {
        this.CODE_NAME = CODE_NAME;
    }
}
