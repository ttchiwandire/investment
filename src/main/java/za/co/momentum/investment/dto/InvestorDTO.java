package za.co.momentum.investment.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class InvestorDTO {

    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private String emailAddress;
    private String address;
    private String mobileNumber;
}
