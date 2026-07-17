package com.libararymanagment.libararymangmentsystem.DTO;


import lombok.Data;

@Data
public class FineResponse {

    private Long fineId;

    private Long borrowId;

    private Integer daysLate;

    private Double fineAmount;

    private String paymentStatus;
}

