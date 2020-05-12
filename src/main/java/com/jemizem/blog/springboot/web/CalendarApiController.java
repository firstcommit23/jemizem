package com.jemizem.blog.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarApiController {

    @GetMapping("/api/v1/calendar/")
    public String getCalendar() {

        return "calendar";

    }
}
