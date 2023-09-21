package za.co.momentum.investment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.momentum.investment.enums.ProductType;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private String name;
    @OneToMany(mappedBy = "product")
    Set<InvestorProduct> productInvestors;
}
