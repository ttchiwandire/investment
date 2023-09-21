package za.co.momentum.investment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InvestorProductKey implements Serializable {

    @Column(name = "investor_id")
    Long investorId;

    @Column(name = "product_id")
    Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvestorProductKey that = (InvestorProductKey) o;
        return investorId.equals(that.investorId) && productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(investorId, productId);
    }
}
