package com.ssafy.member.controller;

import com.ssafy.member.dto.MemberDTO;
import com.ssafy.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

@Controller
@RequiredArgsConstructor // 생성자 주입
public class MemberController {

    // 생성자주입: 컨트롤러 내에서 서비스의 메서드를 활용할 수 있도록 설정
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm(){
        return "save";
    }

//    ver1 쿼리스트링에서 일일이 값을 가져오는 경우
//    @PostMapping("/member/save")
//    public String save(@RequestParam("memberEmail") String memberEmail,
//                       @RequestParam("memberPassword") String memberPassword,
//                       @RequestParam("memberName") String memberName
//    ){
//        System.out.println("MemberController.java"); //sout
//        System.out.println("memberEmail = " + memberEmail + ", memberPassword = " + memberPassword + ", memberName = " + memberName);
//        return "index";
//    }

//    ver2 model 객체 활용할 경우
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
         return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
       MemberDTO loginResult =  memberService.login(memberDTO);
       if(loginResult!=null){
           // login 성공
           session.setAttribute("loginEmail", loginResult.getMemberEmail());
           return "main";
       } else{
           // login 실패
           return "login";
       }
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/member/")
    public String findAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        // 특정 html로 가져갈 데이터가 있다면 model을 사용한다.
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        // PathVariable : 경로 상의 변수 값을 이와 같은 형식으로 받아오겠다.
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model){
        // object -> STring으로 형 변환
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
//        return "detail" // 이렇게 하면 안됨
        return "redirect:/member/" + memberDTO.getId();
    }

}
