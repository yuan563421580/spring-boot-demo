package com.yuansb.demo.orm.mybatis.plus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * 创建代码生成器
 *     修改数据库连接、表明、生成位置等即可
 */
public class MyBatisPlusCodeGenerator {

    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        //获得程序当前路径 E:\yuansb\my-learn-project2\spring-boot-demo
        String projectPath = System.getProperty("user.dir");
        //生成的目录
        //多模块中使用 拼接具体的模块名
        projectPath = projectPath + "/spring-boot-demo-orm-mybatis-plus";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true);
        gc.setAuthor("yuansb");//作者
        gc.setOpen(false);
        gc.setEnableCache(false);
        gc.setDateType(DateType.ONLY_DATE);
        autoGenerator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        //dataSourceConfig.setDbType(DbType.ORACLE);
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/testdb?useUnicode=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root123");
        autoGenerator.setDataSource(dataSourceConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude("orm_user");//表名称 修改修改
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("");
        strategy.entityTableFieldAnnotationEnable(true);
        autoGenerator.setStrategy(strategy);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        //packageConfig.setModuleName(scanner("模块名"));
        //packageConfig.setModuleName("sys");
        packageConfig.setParent("com.yuansb.demo.orm.mybatis.plus");
        packageConfig.setEntity("model");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setMapper("mapper");
        packageConfig.setXml("mapping");
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);

        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

        autoGenerator.execute();
    }


}
