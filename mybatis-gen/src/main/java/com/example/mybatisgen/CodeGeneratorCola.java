package com.example.mybatisgen;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.annotations.Mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CodeGeneratorCola {

    private static final String ARTIFACT_ID = "workorder-admin-service";
    private static final String GROUP_ID = "com.xh.admin";
    private static final String MODULE_NAME = "res";
    private static final String outputDir = "/Users/fanyi/Downloads/workorder-main/" + ARTIFACT_ID + "/";
    private static final String TABLES = "adm_resource,adm_role,adm_role_relation";


    private static final String ADAPTER_MODULE_NAME = ARTIFACT_ID + "-adapter";
    private static final String APP_MODULE_NAME = ARTIFACT_ID + "-app";
    private static final String CLIENT_MODULE_NAME = ARTIFACT_ID + "-client";
    private static final String DOMAIN_MODULE_NAME = ARTIFACT_ID + "-domain";
    private static final String INFRA_MODULE_NAME = ARTIFACT_ID + "-infrastructure";
    private static final String START_MODULE_NAME = ARTIFACT_ID + "-starter";


    private static final Map<String, Object> customMap = new HashMap<>();

    static {
        // --- client ---
        customMap.put("client_dto_package", GROUP_ID + ".client.dto." + MODULE_NAME);
        customMap.put("client_vo_package", GROUP_ID + ".client.vo." + MODULE_NAME);
        customMap.put("client_request_package", GROUP_ID + ".client.request." + MODULE_NAME);
        customMap.put("client_response_package", GROUP_ID + ".client.response." + MODULE_NAME);
        customMap.put("client_api_package", GROUP_ID + ".client.api." + MODULE_NAME);
        customMap.put("client_common_package", GROUP_ID + ".client.common");

        // --- adapter ---
        customMap.put("adapter_web_package", GROUP_ID + ".adapter.web." + MODULE_NAME);

        // --- infra ---
        customMap.put("infra_entity_package", GROUP_ID + ".infra." + MODULE_NAME);
        customMap.put("infra_mapper_package", GROUP_ID + ".infra." + MODULE_NAME + ".mapper");
        customMap.put("infra_repository_package", GROUP_ID + ".infra." + MODULE_NAME + ".repository");
        customMap.put("infra_manager_package", GROUP_ID + ".infra." + MODULE_NAME + ".manager");
        customMap.put("infra_common_package", GROUP_ID + ".infra.common");


        // --- domain ---
        customMap.put("domain_repository_package", GROUP_ID + ".domain." + MODULE_NAME + ".repository");

        // --- app ---
        customMap.put("app_api_package", GROUP_ID + ".app.api." + MODULE_NAME);


    }


    public static void main(String[] args) {
        // 数据源配置
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/admin_service?serverTimezone=GMT%2B8", "root", "12345678")
                .globalConfig(builder -> {
                    builder.author("xh")        // 设置作者
                            .enableSpringdoc()
                            .disableOpenDir()       // 禁止打开输出目录 默认值:true
                            .commentDate("yyyy-MM-dd") // 注释日期
                            .dateType(DateType.ONLY_DATE)   //定义生成的实体类中日期类型 DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                            .outputDir(outputDir); // 指定输出目录
                })

                .injectionConfig(consumer -> {

                    consumer.customMap(customMap);

                    // --- common ---
                    consumer.customFile(cf -> cf.formatNameFunction((tb) -> "AbstractVO.java").fileName("").filePath(outputDir + CLIENT_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("client_common_package")).templatePath("/templates/AbstractVO.java.ftl").build());
                    consumer.customFile(cf -> cf.formatNameFunction((tb) -> "AbstractDTO.java").fileName("").filePath(outputDir + CLIENT_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("client_common_package")).templatePath("/templates/AbstractDTO.java.ftl").build());
                    consumer.customFile(cf -> cf.formatNameFunction((tb) -> "AbstractEntity.java").fileName("").filePath(outputDir + INFRA_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("infra_common_package")).templatePath("/templates/AbstractEntity.java.ftl").build());

                    // --- client ---
                    consumer.customFile(cf -> cf.fileName("DTO.java").filePath(outputDir + CLIENT_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("client_dto_package")).templatePath("/templates/dto.java.ftl").enableFileOverride().build());
                    consumer.customFile(cf -> cf.fileName("VO.java").filePath(outputDir + CLIENT_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("client_vo_package")).templatePath("/templates/vo.java.ftl").enableFileOverride().build());
                    consumer.customFile(cf -> cf.fileName("CreateRequest.java").filePath(outputDir + CLIENT_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("client_request_package")).templatePath("/templates/create_request.java.ftl").enableFileOverride().build());
                    consumer.customFile(cf -> cf.fileName("UpdateRequest.java").filePath(outputDir + CLIENT_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("client_request_package")).templatePath("/templates/update_request.java.ftl").enableFileOverride().build());
                    consumer.customFile(cf -> cf.fileName("Service.java").filePath(outputDir + CLIENT_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("client_api_package")).templatePath("/templates/client-service.java.ftl").enableFileOverride().build());

                    // --- adapter ---
                    // controller
                    consumer.customFile(cf -> cf.fileName("Controller.java").filePath(outputDir + ADAPTER_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("adapter_web_package")).templatePath("/templates/controller.java.ftl").enableFileOverride().build());

                    //--- infra ---
                    // entity
                    consumer.customFile(cf -> cf.fileName("DO.java").filePath(outputDir + INFRA_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("infra_entity_package")).templatePath("/templates/entity.java.ftl").enableFileOverride().build());
                    // mapper
                    consumer.customFile(cf -> cf.fileName("Mapper.java").filePath(outputDir + INFRA_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("infra_mapper_package")).templatePath("/templates/mapper.java.ftl").enableFileOverride().build());
                    // repositoryImpl
                    consumer.customFile(cf -> cf.fileName("RepositoryImpl.java").filePath(outputDir + INFRA_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("infra_repository_package")).templatePath("/templates/repositoryImpl.java.ftl").enableFileOverride().build());
                    // xml
                    consumer.customFile(cf -> cf.fileName("Mapper.xml").filePath(outputDir + INFRA_MODULE_NAME + "/src/main/resources/mapper/" + MODULE_NAME).templatePath("/templates/mapper.xml.ftl").enableFileOverride().build());
                    // manager
                    consumer.customFile(cf -> cf.fileName("Manager.java").filePath(outputDir + INFRA_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("infra_manager_package")).templatePath("/templates/manager.java.ftl").enableFileOverride().build());
                    consumer.customFile(cf -> cf.fileName("ManagerImpl.java").filePath(outputDir + INFRA_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("infra_manager_package") + ".impl").templatePath("/templates/managerImpl.java.ftl").enableFileOverride().build());

                    // --- domain ---
                    // repository
                    consumer.customFile(cf -> cf.fileName("Repository.java").filePath(outputDir + DOMAIN_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("domain_repository_package")).templatePath("/templates/repository.java.ftl").enableFileOverride().build());

                    // --- app ---
                    consumer.customFile(cf -> cf.fileName("ServiceImpl.java").filePath(outputDir + APP_MODULE_NAME + "/src/main/java/").packageName((String) customMap.get("app_api_package")).templatePath("/templates/app-service-Impl.java.ftl").enableFileOverride().build());

                })

                .strategyConfig(builder -> {
                    builder.addInclude(TABLES) // 设置需要生成的表名 可边长参数“user”, “user1”
                            .addTablePrefix("") // 设置过滤表前缀
                            .entityBuilder()
                            .enableLombok()
                            .superClass((String) customMap.get("infra_common_package") + ".AbstractEntity")
                            .addSuperEntityColumns(commonEntityFields())
                            .enableTableFieldAnnotation()
                            .serviceBuilder()//service策略配置
                            .disableServiceImpl()
                            .disableService()
                            .disable()
                            .entityBuilder()// 实体类策略配置
                            .disable()
                            .controllerBuilder() //controller 策略配置
                            .disable()
                            .mapperBuilder()// mapper策略配置
                            .mapperAnnotation(Mapper.class)
                            .disableMapperXml()
                            .disable()
                            .build()
                    ;
                })


                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }


    private static List<String> commonEntityFields() {
        List<String> fs = new ArrayList<>();
        Class<AbstractEntity> abstractEntityClass = AbstractEntity.class;
        for (Field field : abstractEntityClass.getDeclaredFields()) {
            field.setAccessible(true);
            TableField annotation = field.getAnnotation(TableField.class);
            if (annotation != null) {
                fs.add(annotation.value());
            }
        }
        return fs;
    }


    /**
     * 代码生成器支持自定义[DTO\VO等]模版
     */
//    public static class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {
//
//        @Override
//        protected void outputCustomFile(@NotNull List<CustomFile> customFiles, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
//            super.outputCustomFile(customFiles, tableInfo, objectMap);
//            String entityName = tableInfo.getEntityName();
//            String otherPath = this.getPathInfo(OutputFile.other);
//            for (CustomFile customFile : customFiles) {
//                String fileName = String.format(otherPath + File.separator + entityName + "%s", customFile.getFileName());
//                this.outputFile(new File(fileName), objectMap, customFile.getTemplatePath(), true);
//            }
//        }
//
//    }

}
