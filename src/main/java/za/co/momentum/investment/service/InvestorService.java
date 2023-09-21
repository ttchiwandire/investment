package za.co.momentum.investment.service;

import za.co.momentum.investment.dto.InvestorDTO;
import za.co.momentum.investment.dto.ProductDTO;
import za.co.momentum.investment.dto.WithdrawalRequest;
import za.co.momentum.investment.dto.WithdrawalResponse;
import za.co.momentum.investment.enums.ProductType;

import java.util.List;

public interface InvestorService {

    List<InvestorDTO> getAllInvestors();

    List<InvestorDTO> getAllInvestorsByProductType(ProductType productType);

    InvestorDTO getInvestorById(Long id);

    List<ProductDTO> getInvestorProducts(Long id);
}

