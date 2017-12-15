package id.rojak.election.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    String home2(Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            System.out.println(authority.getAuthority());
        }
        System.out.println(authentication.toString());
        return "Hello World! 2";
    }

    @RequestMapping("/testing3")
    public String home3() {
        return "Just need authencation. but all authenticated user can access resources";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/testing4")
    public String home4(HttpServletRequest request) {
        System.out.println(request.isUserInRole("BASIC_READ"));
        System.out.println(request.isUserInRole("ADMIN"));
        return "Hello World! 4 ADMIN";
    }

    @RequestMapping("/testing5")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String home5() {
        return "Hello World! 3 USER";
    }

    //work
    @RequestMapping("/testing6")
    @PreAuthorize("hasAuthority('BASIC_WRITE')")
    public String home6() {
        return "Hello World! 3 USER";
    }

    @RequestMapping("/testing7")
    @PreAuthorize("hasAuthority('BASIC_READ')")
    public String home7() {
        return "Hello World! 3 USER";
    }

    //hasPermission doesnt work


}
