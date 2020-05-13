package com.jemizem.blog.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    Calendar cal = Calendar.getInstance();

    private int startDay;   // 월 시작 요일
    private int lastDay;    // 월 마지막 날짜
    private int inputDate=1;  // 입력 날짜

    @GetMapping("/")
    public String index() {

        return "index";

    }

    @GetMapping("/calendar")
    public String viewCalendar(Model model)  {
        ArrayList<Map> ret = new ArrayList<Map>();


        startDay = cal.get(Calendar.DAY_OF_WEEK); // 월 시작 요일

        lastDay = cal.getActualMaximum(Calendar.DATE); // 월 마지막 날짜

        
        for (int i=0; i<35; i++) {
            Map<String, String> mapCal = new HashMap<>();
            mapCal.put("day",String.valueOf(i+1));
            mapCal.put("br",((i>0 && (i+1)%7==0) )?null:"True");

            ret.add(i, mapCal);
        }
        model.addAttribute("calendar", ret);
        
        return "calendar";

    }
}
