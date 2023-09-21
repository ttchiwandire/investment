package za.co.momentum.investment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvestorProduct {

    @EmbeddedId
    InvestorProductKey investorProductKey;

    @ManyToOne
    @MapsId("investorId")
    @JoinColumn(name = "investor_id")
    Investor investor;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    BigDecimal currentBalance;
}
