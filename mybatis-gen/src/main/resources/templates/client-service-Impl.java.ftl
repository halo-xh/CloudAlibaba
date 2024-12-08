package ${package.Service};

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
<#if kotlin>
    interface ${table.serviceName}Impl :
<#else>
    public interface ${table.serviceName}Impl implements ${table.serviceName} {

    }
</#if>
