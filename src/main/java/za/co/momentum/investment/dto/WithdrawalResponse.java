package za.co.momentum.investment.dto;

import lombok.*;
import za.co.momentum.investment.enums.ProductType;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WithdrawalResponse {
    private Long investorId;
    private String investorName;
    private ProductType productType;
    private BigDecimal amountWithdrawn;
    private BigDecimal balanceBeforeTransaction;
    private BigDecimal closingBalance;
}
