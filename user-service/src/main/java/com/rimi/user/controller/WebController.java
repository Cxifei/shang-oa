package com.rimi.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

/**
 * @author shangzf
 */
@ApiIgnore
@RestController
@RequestMapping("/foo")
public class WebController {

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getFoo() {
        return "I'm foo, " + UUID.randomUUID().toString();
    }

}
