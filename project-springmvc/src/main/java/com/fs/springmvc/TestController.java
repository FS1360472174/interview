
package com.fs.springmvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author cnstonefang@gmail.com
 */
@RestController
public class TestController {

    @GetMapping("/get")
    public String getValue() {
        return "get";
    }
}
