package com.libararymanagment.libararymangmentsystem.service;

import com.libararymanagment.libararymangmentsystem.dto.MemberRequest;
import com.libararymanagment.libararymangmentsystem.dto.MemberResponse;
import com.libararymanagment.libararymangmentsystem.entity.Member;
import com.libararymanagment.libararymangmentsystem.repository.MemberRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    // GET ALL
    public List<MemberResponse> getAllMembers() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // CREATE
    public MemberResponse saveMember(MemberRequest request) {

        Member member = new Member();

        member.setFullName(request.getFullName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());
        member.setAddress(request.getAddress());
        member.setRegistrationDate(LocalDate.now());

        Member savedMember = repository.save(member);

        return mapToResponse(savedMember);
    }

    // DELETE
    public void deleteMember(Long id) {
        repository.deleteById(id);
    }

    // Entity -> Response
    private MemberResponse mapToResponse(Member member) {

        MemberResponse response = new MemberResponse();

        response.setMemberId(member.getMemberId());
        response.setFullName(member.getFullName());
        response.setEmail(member.getEmail());
        response.setPhone(member.getPhone());
        response.setAddress(member.getAddress());
        response.setRegistrationDate(member.getRegistrationDate());

        return response;
    }
}

