package za.co.momentum.investment.dto;

import lombok.*;
import za.co.momentum.investment.enums.ProductType;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {
    private Long id;
    private ProductType productType;
    private String name;
    private BigDecimal currentBalance;
}
