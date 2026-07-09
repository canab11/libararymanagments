package com.libararymanagment.libararymangmentsystem.service;


import com.libararymanagment.libararymangmentsystem.entity.Member;
import com.libararymanagment.libararymangmentsystem.repository.MemberRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    public Member saveMember(Member member) {
        return repository.save(member);
    }

    public void deleteMember(Long id) {
        repository.deleteById(id);
    }
}
