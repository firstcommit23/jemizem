package com.jemizem.blog.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String viewCalendar()  {

        Calendar cal = Calendar.getInstance();
        return "redirect:/calendar/month/" + String.valueOf(cal.get(Calendar.YEAR)) + "/" + String.valueOf(cal.get(Calendar.MONTH)+1) ;

    }

    @GetMapping("/calendar/month/{year}/{month}/prev")
    public String viewCalendarPrev(@PathVariable int year, @PathVariable int month)  {

        cal.set(year, month-1, 1);
        cal.add(Calendar.MONTH, -1);

        return "redirect:/calendar/month/" + String.valueOf(cal.get(Calendar.YEAR)) + "/" + String.valueOf(cal.get(Calendar.MONTH)+1);
    }

    @GetMapping("/calendar/month/{year}/{month}/next")
    public String viewCalendarNext(@PathVariable int year, @PathVariable int month)  {

        cal.set(year, month-1, 1);
        cal.add(Calendar.MONTH, 1);

        return "redirect:/calendar/month/" + String.valueOf(cal.get(Calendar.YEAR)) + "/" + String.valueOf(cal.get(Calendar.MONTH)+1);
    }


    @GetMapping("/calendar/month/{year}/{month}")
    public String viewCalendarMonth(@PathVariable int year, @PathVariable int month, Model model)  {

        ArrayList<Map> ret = new ArrayList<Map>();

        cal.set(year, month-1, 1);


        startDay = cal.get(Calendar.DAY_OF_WEEK); // 월 시작 요일
        lastDay = cal.getActualMaximum(Calendar.DATE); // 월 마지막 날짜
        int day = 1;
        for (int i=0; i<42; i++) {
            Map<String, String> mapCal = new HashMap<>();
            if (i<startDay-1){
                mapCal.put("day"," ");
            } else if (day <= lastDay) {
                mapCal.put("day",String.valueOf(day++));
            } else {
                mapCal.put("day"," ");
            }
            mapCal.put("br",((i>0 && i < 40 && (i+1)%7==0) )?null:"True");

            //공휴일
            //스티커?
            //데이터 { 전체 건수:1, 일정{}, 영화{}, 도서{}, 기록{} }
            ret.add(i, mapCal);
        }
        model.addAttribute("calendar", ret);
        model.addAttribute("year", year);
        model.addAttribute("month", month);

        return "calendar";

    }
}
