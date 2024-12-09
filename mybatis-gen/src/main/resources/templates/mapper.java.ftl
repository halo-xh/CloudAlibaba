package ${infra_mapper_package};

import ${infra_entity_package}.${entity}DO;
import ${superMapperClassPackage};
import ${mapperAnnotationClass.name};

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@${mapperAnnotationClass.simpleName}
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}
<${entity}DO>
<#else>
 public interface ${table.mapperName} extends ${superMapperClass}
 <${entity}DO> {

}
</#if>
