package com.ll.exam.app10.app.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/join")
    public String join (){
        return "member/join";
    }

    @PostMapping("/member/join")
    public String join(String username, String password, String email, MultipartFile profileImg, HttpSession session) {
        Member oldMember = memberService.getMemberByUsername(username);

        if (oldMember != null) {
            return "redirect:/?errorMsg=Already done.";
        }

        Member member = memberService.join(username, "{noop}" + password, email, profileImg);

        session.setAttribute("loginedMemberId", member.getId());

        return "redirect:/member/profile";
    }

    @GetMapping("/member/profile")
    public String showProfile(HttpSession session, Model model) {
        Long loginedMemberId = (Long) session.getAttribute("loginedMemberId");
        boolean isLogined = loginedMemberId != null;

        if (isLogined == false) {
            return "redirect:/?errorMsg=Need to login!";
        }

        Member loginedMember = memberService.getMemberById(loginedMemberId);

        model.addAttribute("loginedMember", loginedMember);

        return "member/profile";
    }
}
