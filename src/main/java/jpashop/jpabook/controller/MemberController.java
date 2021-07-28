package jpashop.jpabook.controller;

import jpashop.jpabook.domain.Address;
import jpashop.jpabook.domain.Member;
import jpashop.jpabook.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String memberForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMembersForm";
    }
    @PostMapping("/members/new")
    public String memberRegister(@Valid @ModelAttribute("memberForm") MemberForm memberForm, BindingResult result){

        if(result.hasErrors()){
            return "members/createMembersForm";
        }

         try{  Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
            Member member = new Member();
            member.setName(memberForm.getName());
            member.setAddress(address);
            memberService.join(member);

            return "redirect:/";}
         catch(Exception e){
             return "redirect:/members/new";

        }

    }
    @RequestMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
