package com.linzongwei.authentication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author linli
 * @since 2021/1/25 11:06
 */
@RestController
public class TestController {

    @RequestMapping("test")
    public String get(){
        return "success";
    }

}
