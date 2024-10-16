package com.example.secondproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    // 클라 요청받기
    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        // model 객체를 이용해 데이터 뷰로 보내기
        model.addAttribute("username", "찬호팍");
        // 클라로 반환
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "칸진리와 흥민쏜");
        return "goodbye";
    }
}
