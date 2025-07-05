package ${client_vo_package};

import ${client_common_package}.AbstractVO;
<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
<#elseif swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
</#if>

import java.io.Serializable;

/**
 * <p>
 * ${entity} VO
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
</#if>
<#if springdoc>
@Schema(name = "${entity} VO", description = "${table.comment!}")
<#elseif swagger>
@ApiModel(value = "${entity} VO", description = "${table.comment!}")
</#if>
public class ${entity}VO extends AbstractVO implements Serializable {
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
    @Schema(description = "${field.comment}")
        <#elseif swagger>
    @ApiModelProperty("${field.comment}")
        <#else>
        </#if>
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>

}