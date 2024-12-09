package ${client_dto_package};


<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
<#elseif swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
</#if>

/**
 * <p>
 * ${entity}对象
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Data
</#if>
<#if springdoc>
@Schema(name = "${entity}", description = "${table.comment!}")
<#elseif swagger>
@ApiModel(value = "${entity}对象", description = "${table.comment!}")
</#if>
public class ${entity}DTO {

<#if entitySerialVersionUID>

    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
        <#if springdoc>
    @Schema(description = "${field.comment}", maxLength = ${field.metaInfo.length?replace(',', '')}, nullable = ${field.metaInfo.isNullable()?string("true", "false")}, defaultValue = "${field.metaInfo.defaultValue!'-'}" )
        <#elseif swagger>
    @ApiModelProperty(value = "${field.comment}", required = ${field.metaInfo.isNullable()?string("true", "false")})
        <#else>
        </#if>
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

}
