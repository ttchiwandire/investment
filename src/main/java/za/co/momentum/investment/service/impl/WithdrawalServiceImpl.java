package za.co.momentum.investment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import za.co.momentum.investment.dto.WithdrawalRequest;
import za.co.momentum.investment.dto.WithdrawalResponse;
import za.co.momentum.investment.model.Investor;
import za.co.momentum.investment.model.InvestorProduct;
import za.co.momentum.investment.repository.InvestorProductRepository;
import za.co.momentum.investment.service.WithdrawalService;
import za.co.momentum.investment.validation.WithdrawalRequestValidator;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class WithdrawalServiceImpl implements WithdrawalService {

    private final InvestorProductRepository investorProductRepository;
    private final WithdrawalRequestValidator validator;

    @Override
    public WithdrawalResponse withdrawFromInvestments(WithdrawalRequest request) {
        WithdrawalResponse withdrawalResponse = null;
        if (validator.isWithdrawalRequestValid(request)) {
            InvestorProduct product = investorProductRepository.findByInvestor_IdAndProduct_ProductType(
                    request.getInvestorId(), request.getProductType());
            Investor investor = product.getInvestor();
            BigDecimal currentBalance = product.getCurrentBalance();
            BigDecimal closingBalance = product.getCurrentBalance().subtract(request.getAmount());
            product.setCurrentBalance(closingBalance);
            investorProductRepository.save(product);
            withdrawalResponse = WithdrawalResponse.builder()
                    .investorId(investor.getId())
                    .investorName(investor.getFirstname() + "  " + investor.getLastname())
                    .productType(product.getProduct().getProductType())
                    .amountWithdrawn(request.getAmount())
                    .balanceBeforeTransaction(currentBalance)
                    .closingBalance(closingBalance)

                    .build();
        }
        return withdrawalResponse;
    }
}
