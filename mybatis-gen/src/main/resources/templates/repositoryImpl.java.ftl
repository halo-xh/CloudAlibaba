package ${infra_repository_package};

import ${domain_repository_package}.${table.entityName}Repository;
import ${infra_manager_package}.${table.entityName}Manager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
    * ${table.comment!} RepositoryImpl
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Service
public class ${table.entityName}RepositoryImpl implements ${table.entityName}Repository {

@Autowired
private ${table.entityName}Manager manager;

}
