package za.co.momentum.investment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import za.co.momentum.investment.enums.AccountType;
import za.co.momentum.investment.enums.Bank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BankingDetailsDTO {

    @NotBlank(message = "Invalid account owner: Empty details")
    @NotNull(message = "Invalid account owner: NULL")
    private String accountOwner;
    @NotBlank(message = "Invalid account number: Empty details")
    @NotNull(message = "Invalid account number: NULL")
    private String accountNumber;
    @NotNull(message = "Invalid bank: NULL")
    private Bank bank;
    @NotNull(message = "Invalid account type: NULL")
    private AccountType accountType;
}
