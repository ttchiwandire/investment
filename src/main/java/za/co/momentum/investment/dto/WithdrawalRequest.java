package za.co.momentum.investment.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import za.co.momentum.investment.enums.ProductType;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WithdrawalRequest {

    @NotNull(message = "Invalid investor id type: NULL")
    private Long investorId;
    @NotNull(message = "Invalid product type: NULL")
    private ProductType productType;
    @NotNull(message = "Invalid amount: NULL")
    private BigDecimal amount;
    @NotNull(message = "Invalid banking details: NULL")
    @Valid
    private BankingDetailsDTO bankingDetails;
}
