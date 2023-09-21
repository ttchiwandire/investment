package za.co.momentum.investment.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.momentum.investment.dto.WithdrawalRequest;
import za.co.momentum.investment.enums.ProductType;
import za.co.momentum.investment.exception.InsufficientFundsException;
import za.co.momentum.investment.exception.TransactionLimitExceededException;
import za.co.momentum.investment.exception.TransactionNotAllowedException;
import za.co.momentum.investment.model.Investor;
import za.co.momentum.investment.model.InvestorProduct;
import za.co.momentum.investment.repository.InvestorProductRepository;
import za.co.momentum.investment.repository.InvestorRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Component
public class WithdrawalRequestValidator {

    private static final String TRANACTION_NOT_ALLOWED_MSG = "You cannot withdraw from a retirement fund if you are aged 65 years and below";
    private static final String LIMIT_EXCEEDED_MSG = "You cannot withdraw more than 90% of your total investment";
    private static final String INSUFFICIENT_FUNDS_MSG = "Your withdrawal cannot be processed as you have specified a withdrawal" +
            " amount which exceeds your current balance";
    @Autowired
    private InvestorRepository investorRepository;
    @Autowired
    private InvestorProductRepository investorProductRepository;

    public boolean isWithdrawalRequestValid(WithdrawalRequest withdrawalRequest) {
        Investor investor = investorRepository.getReferenceById(withdrawalRequest.getInvestorId());

        if (ProductType.RETIREMENT.equals(withdrawalRequest.getProductType())) {
            int investorAge = calculateAge(investor);
            validateProduct(investorAge);
        }
        validateWithdrawalAmount(withdrawalRequest);
        return true;
    }

    private void validateProduct(int investorAge) {
        if (investorAge <= 65) {
            throw new TransactionNotAllowedException(TRANACTION_NOT_ALLOWED_MSG);
        }
    }

    private void validateWithdrawalAmount(WithdrawalRequest withdrawalRequest) {
        InvestorProduct product = investorProductRepository.findByInvestor_IdAndProduct_ProductType(withdrawalRequest
                .getInvestorId(), withdrawalRequest.getProductType());
        if (withdrawalRequest.getAmount().compareTo(product.getCurrentBalance()) > 0) {
            throw new InsufficientFundsException(INSUFFICIENT_FUNDS_MSG);
        }
        if (withdrawalRequest.getAmount().compareTo(product.getCurrentBalance().multiply(BigDecimal.valueOf(0.9))) > 0) {
            throw new TransactionLimitExceededException(LIMIT_EXCEEDED_MSG);
        }
    }

    private int calculateAge(Investor investor) {
        if (null != investor.getBirthDate()) {
            return Period.between(investor.getBirthDate(), LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }
}
