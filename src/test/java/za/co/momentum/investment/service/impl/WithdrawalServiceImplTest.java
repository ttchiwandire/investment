package za.co.momentum.investment.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import za.co.momentum.investment.dto.WithdrawalRequest;
import za.co.momentum.investment.dto.WithdrawalResponse;
import za.co.momentum.investment.enums.ProductType;
import za.co.momentum.investment.model.InvestorProduct;
import za.co.momentum.investment.repository.InvestorProductRepository;
import za.co.momentum.investment.validation.WithdrawalRequestValidator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static za.co.momentum.investment.testdata.InvestorTestData.getInvestorProduct;
import static za.co.momentum.investment.testdata.InvestorTestData.getRetirementWithdrawalRequest;

@ExtendWith(MockitoExtension.class)
@Import(WithdrawalServiceImpl.class)
class WithdrawalServiceImplTest {

    @Mock
    private InvestorProductRepository investorProductRepository;
    @Mock
    private WithdrawalRequestValidator validator;
    @InjectMocks
    private WithdrawalServiceImpl withdrawalService;


    @Test
    void withdrawFromInvestmentsSuccess() {

        //GIVEN - Set up fixtures and expectations
        WithdrawalRequest request = getRetirementWithdrawalRequest();

        when(validator.isWithdrawalRequestValid(any(WithdrawalRequest.class)))
                .thenReturn(true);
        when(investorProductRepository.findByInvestor_IdAndProduct_ProductType(
                anyLong(), any(ProductType.class)))
                .thenReturn(getInvestorProduct());
        when(investorProductRepository.save(any(InvestorProduct.class)))
                .thenReturn(getInvestorProduct());

        //WHEN - Scenario Under Test
        WithdrawalResponse response = withdrawalService.withdrawFromInvestments(request);

        //Then - Verify Behavior
        assertNotNull(response);
        verify(validator, times(1)).isWithdrawalRequestValid(any(WithdrawalRequest.class));
        verify(investorProductRepository, times(1))
                .findByInvestor_IdAndProduct_ProductType(anyLong(), any(ProductType.class));
        verify(investorProductRepository, times(1)).save(any(InvestorProduct.class));
    }
}