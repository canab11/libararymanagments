package com.libararymanagment.libararymangmentsystem.DTO;



import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FineRequest {

    @NotNull(message = "Borrow Record ID is required")
    private Long borrowId;

    @NotNull(message = "Days late is required")
    @Min(value = 0, message = "Days late cannot be negative")
    private Integer daysLate;

    @NotNull(message = "Fine amount is required")
    @DecimalMin(value = "0.0", message = "Fine amount cannot be negative")
    private Double fineAmount;

    @NotNull(message = "Payment status is required")
    private String paymentStatus;
}
