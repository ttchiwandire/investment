package za.co.momentum.investment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import za.co.momentum.investment.InvestmentApplication;
import za.co.momentum.investment.dto.WithdrawalRequest;
import za.co.momentum.investment.exception.InsufficientFundsException;
import za.co.momentum.investment.exception.TransactionLimitExceededException;
import za.co.momentum.investment.exception.TransactionNotAllowedException;
import za.co.momentum.investment.mappers.InvestorMapper;
import za.co.momentum.investment.mappers.InvestorMapperImpl;
import za.co.momentum.investment.service.WithdrawalService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static za.co.momentum.investment.testdata.InvestorTestData.getRetirementWithdrawalRequest;
import static za.co.momentum.investment.testdata.InvestorTestData.getWithdrawalResponseMockData;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {InvestmentApplication.class})
@Import({InvestorMapperImpl.class})
@EnableAutoConfiguration
class WithdrawalControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private InvestorMapper investorMapper;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private WithdrawalService withdrawalService;

    @Test
    void testWithdrawInvestmenstSuccess() throws Exception {

        when(withdrawalService.withdrawFromInvestments(any(WithdrawalRequest.class)))
                .thenReturn(getWithdrawalResponseMockData());
        mockMvc.perform(post("/withdraw-investments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getRetirementWithdrawalRequest())))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(getWithdrawalResponseMockData())));
    }

    @Test
    void testWithdrawInvestmenstFailOnRetirementAge() throws Exception {

        when(withdrawalService.withdrawFromInvestments(any(WithdrawalRequest.class)))
                .thenThrow(new TransactionNotAllowedException("You cannot withdraw from retirement fund before the age of 65"));
        mockMvc.perform(post("/withdraw-investments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getRetirementWithdrawalRequest())))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void testWithdrawInvestmenstFailOnInsufficientFunds() throws Exception {

        when(withdrawalService.withdrawFromInvestments(any(WithdrawalRequest.class)))
                .thenThrow(new InsufficientFundsException("You do not have sufficient funds to withdraw from"));
        mockMvc.perform(post("/withdraw-investments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getRetirementWithdrawalRequest())))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void testWithdrawInvestmenstFailOnTransactionLimit() throws Exception {

        when(withdrawalService.withdrawFromInvestments(any(WithdrawalRequest.class)))
                .thenThrow(new TransactionLimitExceededException("You cannot withdraw from more than 90% of your savings"));
        mockMvc.perform(post("/withdraw-investments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getRetirementWithdrawalRequest())))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}