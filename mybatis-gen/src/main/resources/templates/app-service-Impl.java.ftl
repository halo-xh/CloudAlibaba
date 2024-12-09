package ${app_api_package};

import ${client_api_package}.${table.entityName}Service;
import ${client_request_package}.${entity}CreateRequest;
import ${client_vo_package}.${entity}VO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* <p>
    * ${table.comment!} 服务类Impl
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Slf4j
@Service
public class ${table.entityName}ServiceImpl implements ${table.entityName}Service {

@Override
public ${entity}VO create(${entity}CreateRequest request) {
return null;
}

@Override
public ${entity}VO detail(Long id) {
return null;
    }
}
