package ${client_api_package};


import ${client_request_package}.${entity}CreateRequest;
import ${client_vo_package}.${entity}VO;

/**
* <p>
* ${table.comment!} 服务类
* </p>
*
* @author ${author}
* @since ${date}
*/
public interface ${table.entityName}Service {

    ${entity}VO create(${entity}CreateRequest request);

    ${entity}VO detail(Long id);

}
