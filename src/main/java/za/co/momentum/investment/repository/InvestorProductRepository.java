package za.co.momentum.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.momentum.investment.enums.ProductType;
import za.co.momentum.investment.model.InvestorProduct;
import za.co.momentum.investment.model.InvestorProductKey;

import java.util.List;

public interface InvestorProductRepository extends JpaRepository<InvestorProduct, InvestorProductKey> {

    List<InvestorProduct> findByInvestor_Id(Long id);

    InvestorProduct findByInvestor_IdAndProduct_ProductType(Long id, ProductType productType);
}
