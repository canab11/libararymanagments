package com.libararymanagment.libararymangmentsystem.controller;



import com.libararymanagment.libararymangmentsystem.entity.Member;
import com.libararymanagment.libararymangmentsystem.service.MemberService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin("*")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return service.getAllMembers();
    }

    @PostMapping
    public Member saveMember(
            @RequestBody Member member) {
        return service.saveMember(member);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(
            @PathVariable Long id) {
        service.deleteMember(id);
    }
}
