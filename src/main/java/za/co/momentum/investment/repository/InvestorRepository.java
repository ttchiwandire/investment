package za.co.momentum.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.momentum.investment.enums.ProductType;
import za.co.momentum.investment.model.Investor;

import java.util.List;

public interface InvestorRepository extends JpaRepository<Investor, Long> {

    List<Investor> findByInvestorProducts_Product_ProductType(ProductType productType);
}
