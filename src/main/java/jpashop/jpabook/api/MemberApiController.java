package jpashop.jpabook.api;

import jpashop.jpabook.domain.Member;
import jpashop.jpabook.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v2/members")
    public CreateMemberResponse createMember(@RequestBody @Valid CreateMemberRequest createMemberRequest){

        Member member = new Member();
        member.setName(createMemberRequest.getName());
        memberService.join(member);

        return new CreateMemberResponse(member.getId());
    }


    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMember(@PathVariable("id") Long id,
            @RequestBody UpdateMemberRequest updateMemberRequest){

        log.info("updateMemberRequest: " + updateMemberRequest);
        memberService.update(id, updateMemberRequest.getName());
        Member findMember = memberService.findOne(id);
       // return new UpdateMemberResponse(id, updateMemberRequest.getName());
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    @AllArgsConstructor
    static class CreateMemberResponse {
        private Long id;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class CreateMemberRequest {
        @NotEmpty
        private String name;

    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class UpdateMemberRequest {

        private String name;
    }
}
