package com.example.mybatisgen;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class CodeGeneratorCola {

    private static final String ARTIFACT_ID = "workorder-order-service";
    private static final String GROUP_ID = "com.xh.order";
    private static final String MODULE_NAME = "test";


    private static final String ADAPTER_MODULE_NAME = ARTIFACT_ID + "-adapter";
    private static final String APP_MODULE_NAME = ARTIFACT_ID + "-app";
    private static final String CLIENT_MODULE_NAME = ARTIFACT_ID + "-client";
    private static final String DOMAIN_MODULE_NAME = ARTIFACT_ID + "-domain";
    private static final String INFRA_MODULE_NAME = ARTIFACT_ID + "-infrastructure";
    private static final String START_MODULE_NAME = ARTIFACT_ID + "-starter";


    public static void main(String[] args) {
        // 数据源配置
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/tester?serverTimezone=GMT%2B8", "root", "12345678")
                .globalConfig(builder -> {
                    builder.author("xh")        // 设置作者
                            .enableSpringdoc()
                            .disableOpenDir()       // 禁止打开输出目录 默认值:true
                            .commentDate("yyyy-MM-dd") // 注释日期
                            .dateType(DateType.ONLY_DATE)   //定义生成的实体类中日期类型 DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                            .outputDir("/Users/hongxiao/IdeaProjects/workorder/" + ARTIFACT_ID + "/"); // 指定输出目录
                })

                .packageConfig(builder -> {
                    builder.parent(GROUP_ID) // 父包模块名
                            .controller("controller")   //Controller 包名 默认值:controller
                            .entity("entity")           //Entity 包名 默认值:entity
                            .service("service")         //Service 包名 默认值:service
                            .mapper("mapper")           //Mapper 包名 默认值:mapper
                            .xml("mappers")
                            .moduleName(MODULE_NAME) // 设置父包模块名 默认值:无
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "src/main/resources/mapper")); // 设置mapperXml生成路径
                    //默认存放在mapper的xml下
                })

                .injectionConfig(consumer -> {
                    // --- client ---
                    consumer.customFile(cf -> cf.fileName("DTO.java").filePath(CLIENT_MODULE_NAME + "/src/main/java/").packageName(GROUP_ID + ".client.dto.dto." + MODULE_NAME).templatePath("/templates/dto.java.ftl").enableFileOverride().build());
                    consumer.customFile(cf -> cf.fileName("VO.java").filePath(CLIENT_MODULE_NAME + "/src/main/java/").packageName(GROUP_ID + ".client.dto.vo." + MODULE_NAME).templatePath("/templates/vo.java.ftl").enableFileOverride().build());
                    consumer.customFile(cf -> cf.fileName("CreateRequest.java").filePath(CLIENT_MODULE_NAME + "/src/main/java/").packageName(GROUP_ID + ".client.dto.request." + MODULE_NAME).templatePath("/templates/create_request.java.ftl").enableFileOverride().build());
                    consumer.customFile(cf -> cf.fileName("UpdateRequest.java").filePath(CLIENT_MODULE_NAME + "/src/main/java/").packageName(GROUP_ID + ".client.dto.request." + MODULE_NAME).templatePath("/templates/update_request.java.ftl").enableFileOverride().build());
                    consumer.customFile(cf -> cf.fileName("Service.java").filePath(CLIENT_MODULE_NAME + "/src/main/java/").packageName(GROUP_ID + ".client.api." + MODULE_NAME).templatePath("/templates/client-service.java.ftl").enableFileOverride().build());

                    // --- adapter ---
                    // controller
                    consumer.customFile(cf -> cf.fileName("Controller.java").filePath(ADAPTER_MODULE_NAME + "/src/main/java/").packageName(GROUP_ID + ".adapter.web." + MODULE_NAME).templatePath("/templates/controller.java.ftl").enableFileOverride().build());

                    //--- infra ---
                    // entity
                    consumer.customFile(cf -> cf.fileName("DO.java").filePath(INFRA_MODULE_NAME + "/src/main/java/").packageName(GROUP_ID + ".infra." + MODULE_NAME).templatePath("/templates/entity.java.ftl").enableFileOverride().build());
                    // mapper
                    consumer.customFile(cf -> cf.fileName("Mapper.java").filePath(INFRA_MODULE_NAME + "/src/main/java/").packageName(GROUP_ID + ".infra." + MODULE_NAME + ".mapper").templatePath("/templates/mapper.java.ftl").enableFileOverride().build());
                    // repository
                    consumer.customFile(cf -> cf.fileName("RepositoryImpl.java").filePath(INFRA_MODULE_NAME + "/src/main/java/").packageName(GROUP_ID + ".infra." + MODULE_NAME + ".repository").templatePath("/templates/serviceImpl.java.ftl").enableFileOverride().build());
                    // xml
                    consumer.customFile(cf -> cf.fileName("Mapper.xml").filePath(INFRA_MODULE_NAME + "/src/main/resources/mapper/" + MODULE_NAME).templatePath("/templates/mapper.xml.ftl").enableFileOverride().build());

                    // --- domain ---
                    // repository
                    consumer.customFile(cf -> cf.fileName("Repository.java").filePath(DOMAIN_MODULE_NAME + "/src/main/java/").packageName(GROUP_ID + ".domain." + MODULE_NAME +".repository").templatePath("/templates/service.java.ftl").enableFileOverride().build());

                    // --- app ---
                    consumer.customFile(cf -> cf.fileName("ServiceImpl.java").filePath(APP_MODULE_NAME + "/src/main/java/").packageName(GROUP_ID + ".app.api." + MODULE_NAME).templatePath("/templates/client-service-Impl.java.ftl").enableFileOverride().build());

                })

                .strategyConfig(builder -> {
                    builder.addInclude("cm_trace_log") // 设置需要生成的表名 可边长参数“user”, “user1”
                            .addTablePrefix("cm_") // 设置过滤表前缀
                            .serviceBuilder()//service策略配置
                            .disableService()
                            .disableServiceImpl()
                            .entityBuilder()// 实体类策略配置
                            .disable()
                            .controllerBuilder() //controller 策略配置
                            .disable()
                            .mapperBuilder()// mapper策略配置
                            .disableMapper()
                            .disableMapperXml();
                })


                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }

    /**
     * 代码生成器支持自定义[DTO\VO等]模版
     */
    public static class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

        @Override
        protected void outputCustomFile(@NotNull List<CustomFile> customFiles, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
            super.outputCustomFile(customFiles, tableInfo, objectMap);
            String entityName = tableInfo.getEntityName();
            String otherPath = this.getPathInfo(OutputFile.other);
            for (CustomFile customFile : customFiles) {
                String fileName = String.format(otherPath + File.separator + entityName + "%s", customFile.getFileName());
                this.outputFile(new File(fileName), objectMap, customFile.getTemplatePath(), true);
            }
        }

    }

}
