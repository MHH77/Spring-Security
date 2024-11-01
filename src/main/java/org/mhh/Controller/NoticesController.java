package org.mhh.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {
    @GetMapping("/notices")
    public String getNotice(){
        return "Here are the notices detail from the DB";
    }
}
