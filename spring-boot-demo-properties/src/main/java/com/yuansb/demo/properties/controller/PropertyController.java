package com.yuansb.demo.properties.controller;

import cn.hutool.core.lang.Dict;
import com.yuansb.demo.properties.property.ApplicationProperty;
import com.yuansb.demo.properties.property.DeveloperProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Controller
 *
 * http:127.0.0.1:8102/demo/property
 *     {
 * 	        "applicationProperty": {
 * 		        "name": "dev环境 spring-boot-demo-properties",
 * 		        "version": "dev环境 0.0.1-SNAPSHOT"
 *          },
 * 	        "developerProperty": {
 * 		        "name": "dev环境 yuansb",
 * 		        "qq": "dev环境 563***580",
 * 		        "phoneNumber": "dev环境 186****6581"
 *          }
 *     }
 */
@RestController
public class PropertyController {

    private final ApplicationProperty applicationProperty;
    private final DeveloperProperty developerProperty;

    @Autowired
    public PropertyController(ApplicationProperty applicationProperty, DeveloperProperty developerProperty) {
        this.applicationProperty = applicationProperty;
        this.developerProperty = developerProperty;
    }

    @GetMapping("/property")
    public Dict index() {
        return Dict.create().set("applicationProperty", applicationProperty).set("developerProperty", developerProperty);
    }

}
