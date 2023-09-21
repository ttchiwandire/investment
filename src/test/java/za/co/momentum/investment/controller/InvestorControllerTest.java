package za.co.momentum.investment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import za.co.momentum.investment.InvestmentApplication;
import za.co.momentum.investment.enums.ProductType;
import za.co.momentum.investment.mappers.InvestorMapper;
import za.co.momentum.investment.mappers.InvestorMapperImpl;
import za.co.momentum.investment.service.InvestorService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static za.co.momentum.investment.testdata.InvestorTestData.getInvestorDTOMockData;
import static za.co.momentum.investment.testdata.InvestorTestData.getProductDTOMockData;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {InvestmentApplication.class})
@Import({InvestorMapperImpl.class})
@EnableAutoConfiguration
class InvestorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private InvestorMapper investorMapper;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private InvestorService investorService;


    @Test
    void findInvestorById() throws Exception {

        when(investorService.getInvestorById(anyLong()))
                .thenReturn(getInvestorDTOMockData());
        mockMvc.perform(get("/investor-details")
                        .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(getInvestorDTOMockData())));
    }

    @Test
    void findAllInvestors() throws Exception {

        when(investorService.getAllInvestors())
                .thenReturn(Collections.singletonList(getInvestorDTOMockData()));
        mockMvc.perform(get("/investors"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(Collections.singleton(getInvestorDTOMockData()))));
    }

    @Test
    void findAllInvestorsByProductType() throws Exception {

        when(investorService.getAllInvestorsByProductType(any(ProductType.class)))
                .thenReturn(Collections.singletonList(getInvestorDTOMockData()));
        mockMvc.perform(get("/investor-by-product-type")
                        .param("productType", "SAVINGS"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(Collections.singletonList(getInvestorDTOMockData()))));
    }

    @Test
    void findInvestorProducts() throws Exception {
        when(investorService.getInvestorProducts(anyLong()))
                .thenReturn(Collections.singletonList(getProductDTOMockData()));
        mockMvc.perform(get("/investor-products")
                        .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(Collections.singletonList(getProductDTOMockData()))));
    }
}