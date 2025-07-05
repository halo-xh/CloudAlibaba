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
 * ${entity}创建对象
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
@Schema(name = "${entity}", description = "${table.comment!}")
<#elseif swagger>
@ApiModel(value = "${entity}创建对象", description = "${table.comment!}")
</#if>
public class ${entity}CreateRequest extends ${entity}DTO implements Serializable {

<#if entitySerialVersionUID>

    private static final long serialVersionUID = 1L;
</#if>

}
