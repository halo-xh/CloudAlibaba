package com.example.cloudstatemachinedemo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.example.common.AbstractEntity;

import java.util.Collections;


public class CodeGenerator {
    public static void main(String[] args) {
        // 数据源配置
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("xh")        // 设置作者
                            .enableSwagger()        // 开启 swagger 模式 默认值:false
                            .disableOpenDir()       // 禁止打开输出目录 默认值:true
                            .commentDate("yyyy-MM-dd") // 注释日期
                            .dateType(DateType.ONLY_DATE)   //定义生成的实体类中日期类型 DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })

                .packageConfig(builder -> {
                    builder.parent("com.example") // 父包模块名
                            .controller("controller")   //Controller 包名 默认值:controller
                            .entity("entity")           //Entity 包名 默认值:entity
                            .service("service")         //Service 包名 默认值:service
                            .mapper("mapper")           //Mapper 包名 默认值:mapper
                            .moduleName("task") // 设置父包模块名 默认值:无
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/mapper")); // 设置mapperXml生成路径
                    //默认存放在mapper的xml下
                })

//                .injectionConfig(consumer -> {
//                    Map<String, String> customFile = new HashMap<>();
//                    // DTO、VO
//                    customFile.put("DTO.java", "/templates/entityDTO.java.ftl");
//                    customFile.put("VO.java", "/templates/entityVO.java.ftl");
//
//                    consumer.customFile(customFile);
//                })

                .strategyConfig(builder -> {
                    builder.addInclude("fsm_task","fsm_task_type") // 设置需要生成的表名 可边长参数“user”, “user1”
                            .addTablePrefix("fsm_") // 设置过滤表前缀
                            .serviceBuilder()//service策略配置
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()// 实体类策略配置
                            .idType(IdType.ASSIGN_ID)//主键策略 雪花算法自动生成的id
                            .addTableFills(new Property("createdAt", FieldFill.INSERT)) // 自动填充配置
                            .addTableFills(new Property("modifiedAt", FieldFill.INSERT_UPDATE))
                            .enableLombok() //开启lombok
                            .logicDeleteColumnName("is_deleted")// 说明逻辑删除是哪个字段
                            .versionPropertyName("version")
                            .superClass(AbstractEntity.class)
                            .enableTableFieldAnnotation()// 属性加上注解说明
                            .controllerBuilder() //controller 策略配置
                            .formatFileName("%sController")
                            .enableRestStyle() // 开启RestController注解
                            .mapperBuilder()// mapper策略配置
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()//@mapper注解开启
                            .formatXmlFileName("%sMapper");
                })


                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }

    /**
     * 代码生成器支持自定义[DTO\VO等]模版
     */
//    public static class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {
//
//        @Override
//        protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
//            String entityName = tableInfo.getEntityName();
//            String otherPath = this.getPathInfo(OutputFile.other);
//            customFile.forEach((key, value) -> {
//                String fileName = String.format(otherPath + File.separator + entityName + "%s", key);
//                this.outputFile(new File(fileName), objectMap, value, true);
//            });
//        }
//    }

}
