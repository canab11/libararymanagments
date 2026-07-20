package com.libararymanagment.libararymangmentsystem.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MemberResponse {

    private Long memberId;

    private String fullName;

    private String email;

    private String phone;

    private String address;

    private LocalDate registrationDate;
}
