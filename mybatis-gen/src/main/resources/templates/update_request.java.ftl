package ${client_request_package};

import ${client_dto_package}.${entity}DTO;
<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
<#elseif swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
</#if>

/**
 * <p>
 * ${entity}更新对象
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Data
@EqualsAndHashCode(callSuper = true)
</#if>
<#if springdoc>
@Schema(name = "${entity}更新对象", description = "${table.comment!}")
<#elseif swagger>
@ApiModel(value = "${entity}更新对象", description = "${table.comment!}")
</#if>
public class ${entity}UpdateRequest extends ${entity}DTO implements Serializable {

<#if entitySerialVersionUID>

    private static final long serialVersionUID = 1L;
</#if>

    <#if springdoc>
    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    <#elseif swagger>
    @ApiModelProperty(value = "id", required = true)
    <#else>
    </#if>
    private Long id;

    <#if springdoc>
    @Schema(description = "乐观锁", requiredMode = Schema.RequiredMode.REQUIRED)
    <#elseif swagger>
    @ApiModelProperty(value = "乐观锁", required = true)
    <#else>
    </#if>
    private Integer version;

}
