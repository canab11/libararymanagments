package com.libararymanagment.libararymangmentsystem.repository;
import com.libararymanagment.libararymangmentsystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

