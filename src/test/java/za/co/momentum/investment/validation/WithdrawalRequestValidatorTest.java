package za.co.momentum.investment.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import za.co.momentum.investment.dto.WithdrawalRequest;
import za.co.momentum.investment.enums.ProductType;
import za.co.momentum.investment.exception.InsufficientFundsException;
import za.co.momentum.investment.exception.TransactionLimitExceededException;
import za.co.momentum.investment.exception.TransactionNotAllowedException;
import za.co.momentum.investment.model.Investor;
import za.co.momentum.investment.repository.InvestorProductRepository;
import za.co.momentum.investment.repository.InvestorRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static za.co.momentum.investment.testdata.InvestorTestData.*;

@ExtendWith(MockitoExtension.class)
@Import(WithdrawalRequestValidator.class)
class WithdrawalRequestValidatorTest {

    @Mock
    private InvestorRepository investorRepository;
    @Mock
    private InvestorProductRepository investorProductRepository;

    @InjectMocks
    WithdrawalRequestValidator withdrawalRequestValidator;

    @Test
    void isWithdrawalRequestValid_RETIREMENT_SUCCESS() {

        //GIVEN - Set up fixtures and expectations
        when(investorRepository.getReferenceById(anyLong()))
                .thenReturn(getRetiredInvestorData());
        when(investorProductRepository.findByInvestor_IdAndProduct_ProductType(
                anyLong(), any(ProductType.class)))
                .thenReturn(getInvestorProduct());

        //WHEN - Scenario Under Test
        boolean validRequest = withdrawalRequestValidator.isWithdrawalRequestValid(getRetirementWithdrawalRequest());

        //Then - Verify Behavior
        assertTrue(validRequest);

    }

    @Test
    void isWithdrawalRequestValid_RETIREMENT_FAIL_ON_AGE_BELOW_65() {

        //GIVEN - Set up fixtures and expectations
        Investor investor = getRetiredInvestorData();
        investor.setBirthDate(LocalDate.of(2000, 01, 01));
        when(investorRepository.getReferenceById(anyLong()))
                .thenReturn(investor);
       /* when(investorProductRepository.findByInvestor_IdAndProduct_ProductType(
                anyLong(), any(ProductType.class)))
                .thenReturn(getInvestorProduct());*/

        //WHEN - Scenario Under Test
        TransactionNotAllowedException ex = assertThrows(TransactionNotAllowedException.class, () ->
                withdrawalRequestValidator.isWithdrawalRequestValid(getRetirementWithdrawalRequest()));

        //Then - Verify Behavior
        assertEquals("You cannot withdraw from a retirement fund if you are aged 65 years and below", ex.getMessage());

    }

    @Test
    void isWithdrawalRequestValid_FAILED_ON_INSUFFICIENT_FUNDS() {

        //GIVEN - Set up fixtures and expectations
        when(investorRepository.getReferenceById(anyLong()))
                .thenReturn(getRetiredInvestorData());
        when(investorProductRepository.findByInvestor_IdAndProduct_ProductType(
                anyLong(), any(ProductType.class)))
                .thenReturn(getInvestorProduct());
        WithdrawalRequest withdrawalRequest = getRetirementWithdrawalRequest();
        withdrawalRequest.setAmount(BigDecimal.valueOf(1000000));

        //WHEN - Scenario Under Test
        InsufficientFundsException ex = assertThrows(InsufficientFundsException.class, () ->
                withdrawalRequestValidator.isWithdrawalRequestValid(withdrawalRequest));

        //Then - Verify Behavior
        assertEquals("Your withdrawal cannot be processed as you have specified a withdrawal amount which exceeds your current balance", ex.getMessage());

    }

    @Test
    void isWithdrawalRequestValid_FAILED_ON_TRANSACTION_LIMIT_EXCEEDED() {

        //GIVEN - Set up fixtures and expectations
        when(investorRepository.getReferenceById(anyLong()))
                .thenReturn(getRetiredInvestorData());
        when(investorProductRepository.findByInvestor_IdAndProduct_ProductType(
                anyLong(), any(ProductType.class)))
                .thenReturn(getInvestorProduct());
        WithdrawalRequest withdrawalRequest = getRetirementWithdrawalRequest();
        withdrawalRequest.setAmount(BigDecimal.valueOf(9500));

        //WHEN - Scenario Under Test
        TransactionLimitExceededException ex = assertThrows(TransactionLimitExceededException.class, () ->
                withdrawalRequestValidator.isWithdrawalRequestValid(withdrawalRequest));

        //Then - Verify Behavior
        assertEquals("You cannot withdraw more than 90% of your total investment", ex.getMessage());

    }
}