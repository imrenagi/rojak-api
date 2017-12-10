package id.rojak.election.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by imrenagi on 12/9/17.
 */
@RestController
public class TestController {

    @RequestMapping("/testing1")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/testing2")
    String home2() {
        return "Hello World! 2";
    }

    @RequestMapping("/testing3")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String home3() {
        return "Hello World! 3 ADMIN";
    }


}
