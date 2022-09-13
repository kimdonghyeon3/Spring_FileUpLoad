package com.ll.exam.app10.app.home.controller;

import com.ll.exam.app10.app.member.Member;
import com.ll.exam.app10.app.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    @RequestMapping("/")
    public String main(Principal principal, Model model) {

        System.out.println("principal = " + principal.getName());

        Member loginedMember = memberService.getMemberByUsername(principal.getName());
        String loginedMemberProfileImgUrl = null;

        if(loginedMember != null){
            loginedMemberProfileImgUrl = loginedMember.getProfileImgUrl();
        }

        model.addAttribute("loginedMember",loginedMember);
        model.addAttribute("loginedMemberProfileImgUrl",loginedMemberProfileImgUrl);


        return "home/main";
    }

    @RequestMapping("/test/upload")
    public String upload() {
        return "home/test/upload";
    }

}
