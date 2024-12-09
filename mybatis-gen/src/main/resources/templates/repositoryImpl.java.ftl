package ${infra_repository_package};

import ${infra_entity_package}.${entity}DO;
import ${infra_mapper_package}.${table.mapperName};
<#if generateService>
import ${package.Service}.${table.serviceName};
</#if>
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * <p>
    * ${table.comment!} RepositoryImpl
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${table.entityName}RepositoryImpl extends ${superServiceImplClass}<${table.mapperName}, ${entity}DO><#if generateService> implements ${table.entityName}Repository</#if> {

}
