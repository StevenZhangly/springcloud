package com.example.demo.dto;

import java.io.Serializable;

/**
 * @ClassName CountryDTO
 * @Description: TODO
 * @Author zly
 * @Date 2020/9/24
 **/
public class CountryDTO implements Serializable {

    private String COUNTRY_CODE;

    private String CONTINENT;

    private String COUNTRY_NM;

    private String DIST_CODE;

    private String COUNTRY_FCODE;

    private String COUNTRY_DCODE;

    private String COUNTRY_CNM;

    private String DIST_DHL;

    public String getCOUNTRY_CODE() {
        return COUNTRY_CODE;
    }

    public void setCOUNTRY_CODE(String COUNTRY_CODE) {
        this.COUNTRY_CODE = COUNTRY_CODE;
    }

    public String getCONTINENT() {
        return CONTINENT;
    }

    public void setCONTINENT(String CONTINENT) {
        this.CONTINENT = CONTINENT;
    }

    public String getCOUNTRY_NM() {
        return COUNTRY_NM;
    }

    public void setCOUNTRY_NM(String COUNTRY_NM) {
        this.COUNTRY_NM = COUNTRY_NM;
    }

    public String getDIST_CODE() {
        return DIST_CODE;
    }

    public void setDIST_CODE(String DIST_CODE) {
        this.DIST_CODE = DIST_CODE;
    }

    public String getCOUNTRY_FCODE() {
        return COUNTRY_FCODE;
    }

    public void setCOUNTRY_FCODE(String COUNTRY_FCODE) {
        this.COUNTRY_FCODE = COUNTRY_FCODE;
    }

    public String getCOUNTRY_DCODE() {
        return COUNTRY_DCODE;
    }

    public void setCOUNTRY_DCODE(String COUNTRY_DCODE) {
        this.COUNTRY_DCODE = COUNTRY_DCODE;
    }

    public String getCOUNTRY_CNM() {
        return COUNTRY_CNM;
    }

    public void setCOUNTRY_CNM(String COUNTRY_CNM) {
        this.COUNTRY_CNM = COUNTRY_CNM;
    }

    public String getDIST_DHL() {
        return DIST_DHL;
    }

    public void setDIST_DHL(String DIST_DHL) {
        this.DIST_DHL = DIST_DHL;
    }
}
