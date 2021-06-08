package com.example.demo.util;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.demo.dto.IndustryCodeDTO;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @ClassName ExcelUtil
 * @Description: TODO
 * @Author zly
 * @Date 2020/9/24
 **/
public class FileUtil {

    public static <T> List<T> parseExcel(String filePath, Class<T> cls){
        ExcelReader reader = ExcelUtil.getReader(filePath);
        return reader.readAll(cls);
    }





    public static void main(String[] args) {
        //List<CountryDTO> list = parseExcel("C://国别.xlsx", CountryDTO.class);
        //insert into t_data_dic (GROUP_CODE, DATA_CODE, DATA_VALUE, REMARK) values ('BUY_EXCHANGE_CODE', '310', '货物贸易', null);
        /*String template = "insert into t_data_dic (GROUP_CODE, DATA_CODE, DATA_VALUE, REMARK) values ('COUNTRY_CODE', 'COUNTRY_FCODE', 'COUNTRY_CNM', null);";
        for (CountryDTO dto : list) {
            if(StringUtils.isNotBlank(dto.getCOUNTRY_FCODE())){
                String sql = template.replace("COUNTRY_FCODE", dto.getCOUNTRY_FCODE()).replace("COUNTRY_CNM", dto.getCOUNTRY_CNM());
                System.out.println(sql);
            }
        }*/

        /*String template = "insert into t_data_dic (GROUP_CODE, DATA_CODE, DATA_VALUE, REMARK) values ('INDUSTRY_CODE', 'INDUSTRY_NO', 'INDUSTRY_NAME', null);";
        List<String> lines = new FileReader("C://行业属性代码.txt").readLines();
        for (String line : lines) {
            String industryStr = line.substring(line.indexOf("\">")+2, line.indexOf("</"));
            String[] industryStrs = industryStr.split("-");
            String sql = template.replace("INDUSTRY_NO", industryStrs[0]).replace("INDUSTRY_NAME", industryStrs[1]);
            System.out.println(sql);
        }*/

        /*String template = "insert into t_data_dic (GROUP_CODE, DATA_CODE, DATA_VALUE, REMARK) values ('ECONOMY_CODE', 'ECONOMY_NO', 'ECONOMY_NAME', null);";
        List<String> lines = new FileReader("C://经济类型.txt").readLines();
        for (String line : lines) {
            String industryStr = line.substring(line.indexOf("\">")+2, line.indexOf("</"));
            String[] industryStrs = industryStr.split("-");
            String sql = template.replace("ECONOMY_NO", industryStrs[0]).replace("ECONOMY_NAME", industryStrs[1]);
            System.out.println(sql);
        }*/

        /*String template = "insert into t_data_dic (GROUP_CODE, DATA_CODE, DATA_VALUE, REMARK) values ('TAX_FREE_CODE', 'TAX_FREE_NO', 'TAX_FREE_NAME', null);";
        List<String> lines = new FileReader("C://特殊经济区内企业类型代码.txt").readLines();
        for (String line : lines) {
            String industryStr = line.substring(line.indexOf("\">")+2, line.indexOf("</"));
            String[] industryStrs = industryStr.split("-");
            String sql = template.replace("TAX_FREE_NO", industryStrs[0]).replace("TAX_FREE_NAME", industryStrs[1]);
            System.out.println(sql);
        }*/

        /*List<LocationCodeDTO> list = parseExcel("C://住所-经营场所代码.xlsx", LocationCodeDTO.class);
        String template = "insert into t_data_dic (GROUP_CODE, DATA_CODE, DATA_VALUE, REMARK) values ('BUSSINESS_LOCATION_CODE', 'AREA_CODE', 'AREA_NAME', null);";
        for (LocationCodeDTO dto : list) {
            if(StringUtils.isNotBlank(dto.getAREA_CODE())){
                String sql = template.replace("AREA_CODE", dto.getAREA_CODE()).replace("AREA_NAME", dto.getAREA_NAME());
                System.out.println(sql);
            }
        }*/

        List<IndustryCodeDTO> list = parseExcel("C://行业属性代码.xlsx", IndustryCodeDTO.class);
        String template = "insert into t_data_dic (GROUP_CODE, DATA_CODE, DATA_VALUE, REMARK) values ('INDUSTRY_CODE', 'INDUSTRY_NO', 'INDUSTRY_NAME', null);";
        for (IndustryCodeDTO dto : list) {
            if(StringUtils.isNotBlank(dto.getINDUSTRY_CODE())){
                String sql = template.replace("INDUSTRY_NO", dto.getINDUSTRY_CODE()).replace("INDUSTRY_NAME", dto.getCODE_NAME());
                System.out.println(sql);
            }
        }
    }
}
