package com.demo.user;

/**
 * ***************************************************************************
 * (lpt) V
 * ===========================================================================
 * 文 件 名：com.lycheepay.lpt.lpb.MyBatisPlusGenerator.java
 * 模块名称：
 * 功能说明：
 * 作    者：huwf@kftpay.com.cn
 * 创建日期：2020/9/3 14:22
 * 版 本 号：1.0
 * 修改历史：
 * 修改日期                 姓名                         内容
 * ---------------------------------------------------------------------------
 * 2020/9/3 14:22     huwf@kftpay.com.cn               初始版本
 * /**************************************************************************
 */

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * MyBatis Plus Generator 配置执行类示例
 * </p>
 *
 * @author XiaoPengwei.com
 * @since 2019-04-07
 */
public class MyBatisPlusGenerator {
    private static String projectPath = MyBatisPlusGenerator.class.getClassLoader().getResource("").getPath().substring(1,MyBatisPlusGenerator.class.getClassLoader().getResource("").getPath().indexOf("user-server")+11);
    private static String[] tableInclude = {
            "user"
    };


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("zly");
        globalConfig.setOpen(false);
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置 需配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai");
        // dataSourceConfig.setSchemaName("public");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        //数据类型转换
        /*dataSourceConfig.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                System.out.println("转换类型：" + fieldType);
                //tinyint转换成Boolean
                if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                    return DbColumnType.SHORT;
                }
                //将数据库中datetime转换成date
                if ( fieldType.toLowerCase().contains( "datetime" ) ) {
                    return DbColumnType.DATE;
                }
                
                //将数据库中datetime转换成date
                if ( fieldType.toLowerCase().contains( "timestamp" ) ) {
                    return DbColumnType.DATE;
                }
                
                //将数据库中datetime转换成date
                if ( fieldType.toLowerCase().contains( "date" ) ) {
                    return DbColumnType.DATE;
                }
                
                //将数据库中datetime转换成date
                if ( fieldType.toLowerCase().contains( "smallint" ) ) {
                    return DbColumnType.SHORT;
                }
                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }
        });*/
        autoGenerator.setDataSource(dataSourceConfig);

        // 生成包配置
        PackageConfig packageConfig = new PackageConfig();
        //如果需要手动输入模块名
//        packageConfig.setModuleName("模块名");
        packageConfig.setParent("com.demo.user");
        autoGenerator.setPackageInfo(packageConfig);

        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/src/main/resources/sqlmap/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(focList);
        autoGenerator.setCfg(injectionConfig);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity-test.java");
        // templateConfig.setService();
        //templateConfig.setController("templates/mybatis/controller.java");
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategyConfig.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        //strategyConfig.setSuperControllerClass("com.lycheepay.lpt.lpb.controller.base.BaseController");
        // 如果 setInclude() 不加参数, 会自定查找所有表
        // 如需要制定单个表, 需填写参数如: strategyConfig.setInclude("user_info);
        strategyConfig.setInclude(tableInclude);
        // strategyConfig.setSuperEntityColumns("id");
        strategyConfig.setControllerMappingHyphenStyle(true);
        //自动将数据库中表名为 user_info 格式的专为 UserInfo 命名
        strategyConfig.setTablePrefix("t_");//表前缀
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setTemplateEngine(new VelocityTemplateEngine());
        System.out.println("===================== MyBatis Plus Generator ==================");

        autoGenerator.execute();

        System.out.println("================= MyBatis Plus Generator Execute Complete ==================");
    }
}

