package com.example.cloudsimple.jetcache;


import com.example.cloudsimple.entity.TestDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserRest {

    @Autowired
    private TestCacheService cacheService;


    @GetMapping("/create")
    public TestDO2 create(@RequestParam(name = "name")String name, @RequestParam(name = "value") Integer value) {
        return cacheService.create(name, value);
    }


    @GetMapping("/findById/{id}")
    public TestDO2 findById(@PathVariable(value = "id") Long id) {
        return cacheService.findById(id);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable(value = "id")Long id) {
        cacheService.delete(id);
    }

}
