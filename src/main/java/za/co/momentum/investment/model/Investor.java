package za.co.momentum.investment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Investor extends BaseEntity{

    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private String emailAddress;
    private String address;
    private String mobileNumber;

    @OneToMany(mappedBy = "investor")
    private Set<InvestorProduct> investorProducts;
}
