package ${adapter_web_package};


import ${client_api_package}.${table.entityName}Service;
import ${client_request_package}.${entity}CreateRequest;
import ${client_vo_package}.${entity}VO;
<#if springdoc>
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
<#elseif swagger>
import io.swagger.annotations.Api;
</#if>
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if springdoc>
@Tag(name = "${table.comment!}接口", description = "${table.comment!}接口")
<#elseif swagger>
@Api(tags = "${table.comment!}接口")
</#if>
@RestController
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.entityName}Service ${table.serviceName?uncap_first};


    <#if springdoc>
    @Operation(summary = "创建", description = "创建")
    <#elseif swagger>
    @ApiOperation(value = "创建")
    </#if>
    @PostMapping("/create")
    public ${entity}VO create(@RequestBody ${entity}CreateRequest request) {
        return ${table.serviceName?uncap_first}.create(request);
    }


    <#if springdoc>
    @Operation(summary = "详情", description = "详情")
    @Parameters({
        @Parameter(name = "id", description = "id", required = true, in = ParameterIn.PATH)
    })
    <#elseif swagger>
    @ApiOperation(value = "详情")
    </#if>
    @GetMapping("/detail/{id}")
    public ${entity}VO detail(@PathVariable(value = "id") Long id) throws Exception {
        return ${table.serviceName?uncap_first}.detail(id);
    }

}
