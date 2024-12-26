package ${infra_manager_package}.impl;

import ${infra_entity_package}.${entity}DO;
import ${infra_mapper_package}.${table.mapperName};
<#if generateService>
    import ${package.Service}.${table.serviceName};
</#if>
import ${superServiceImplClassPackage};
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* <p>
    * ${table.comment!} ManagerImpl
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Slf4j
@Service
public class ${table.entityName}ManagerImpl extends ${superServiceImplClass}<${table.mapperName}, ${entity}DO><#if generateService> implements ${table.entityName}Manager</#if> {

}
