package com.ll.exam.app10.app.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/member/join")
    public String join (){
        return "member/join";
    }

    @PostMapping("/member/join")
    public String join(String username, String password, String email, MultipartFile profileImg, HttpServletRequest request) {
        Member oldMember = memberService.getMemberByUsername(username);

        String passwordClearText = password;
        password = passwordEncoder.encode(password);

        if (oldMember != null) {
            return "redirect:/?errorMsg=Already done.";
        }

        Member member = memberService.join(username, password, email, profileImg);
        try {
            request.login(username, passwordClearText);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/member/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/member/profile")
    public String showProfile(Principal principal, Model model) {
        Member loginedMember = memberService.getMemberByUsername(principal.getName());

        model.addAttribute("loginedMember", loginedMember);

        return "member/profile";
    }

    @GetMapping("/member/login")
    public String showLogin(){
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(Model model){

        model.addAttribute("loginedMember");

        return "redirect:/member/profile";
    }
}
