package za.co.momentum.investment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import za.co.momentum.investment.dto.InvestorDTO;
import za.co.momentum.investment.dto.ProductDTO;
import za.co.momentum.investment.enums.ProductType;
import za.co.momentum.investment.mappers.InvestorMapper;
import za.co.momentum.investment.repository.InvestorProductRepository;
import za.co.momentum.investment.repository.InvestorRepository;
import za.co.momentum.investment.service.InvestorService;
import za.co.momentum.investment.validation.WithdrawalRequestValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvestorServiceImpl implements InvestorService {

    private final InvestorRepository investorRepository;
    private final InvestorMapper investorMapper;
    private final InvestorProductRepository investorProductRepository;
    private final WithdrawalRequestValidator validator;

    @Override
    public List<InvestorDTO> getAllInvestors() {
        return investorMapper.mapInvestorEntityListToDto(investorRepository.findAll());
    }

    @Override
    public List<InvestorDTO> getAllInvestorsByProductType(ProductType productType) {
        return investorMapper.mapInvestorEntityListToDto(investorRepository.findByInvestorProducts_Product_ProductType(productType));
    }

    @Override
    public InvestorDTO getInvestorById(Long id) {
        return investorMapper.mapInvestorEntityToDto(investorRepository.getReferenceById(id));
    }

    @Override
    public List<ProductDTO> getInvestorProducts(Long id) {
        return investorMapper.mapInvestorProductEntityToDTO(investorProductRepository.findByInvestor_Id(id));
    }
}
