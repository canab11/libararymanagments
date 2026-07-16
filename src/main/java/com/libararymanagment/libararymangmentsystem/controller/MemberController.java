package com.libararymanagment.libararymangmentsystem.controller;



import com.libararymanagment.libararymangmentsystem.DTO.MemberRequest;
import com.libararymanagment.libararymangmentsystem.DTO.MemberResponse;
import com.libararymanagment.libararymangmentsystem.service.MemberService;
import jakarta.validation.Valid;
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
    public List<MemberResponse> getAllMembers() {
        return service.getAllMembers();
    }


    @PostMapping
    public MemberResponse saveMember(
            @Valid @RequestBody MemberRequest request) {

        return service.saveMember(request);
    }


    @DeleteMapping("/{id}")
    public String deleteMember(
            @PathVariable Long id) {

        service.deleteMember(id);

        return "Member deleted successfully";
    }
}


