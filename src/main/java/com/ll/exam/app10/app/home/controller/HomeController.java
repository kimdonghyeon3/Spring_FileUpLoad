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
    @GetMapping("/")
    public String showMain(Principal principal, Model model) {
        Member loginedMember = null;
        String loginedMemberProfileImgUrl = null;

        if (principal != null && principal.getName() != null) {
            loginedMember = memberService.getMemberByUsername(principal.getName());
        }

        if (loginedMember != null) {
            loginedMemberProfileImgUrl = loginedMember.getProfileImgUrl();
        }

        model.addAttribute("loginedMember", loginedMember);
        model.addAttribute("loginedMemberProfileImgUrl", loginedMemberProfileImgUrl);

        return "home/main";
    }

    @GetMapping("/about")
    public String showAbout(Principal principal, Model model) {
        Member loginedMember = null;
        String loginedMemberProfileImgUrl = null;

        if (principal != null && principal.getName() != null) {
            loginedMember = memberService.getMemberByUsername(principal.getName());
        }

        if (loginedMember != null) {
            loginedMemberProfileImgUrl = loginedMember.getProfileImgUrl();
        }

        model.addAttribute("loginedMember", loginedMember);
        model.addAttribute("loginedMemberProfileImgUrl", loginedMemberProfileImgUrl);

        return "home/about";
    }

}
