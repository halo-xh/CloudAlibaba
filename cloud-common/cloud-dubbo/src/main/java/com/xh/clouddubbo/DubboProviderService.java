package com.xh.clouddubbo;

import com.xh.clouddubbo.dto.DubboTest;

public interface DubboProviderService {

    DubboTest getById(Long id);

    void save(DubboTest test);
}
