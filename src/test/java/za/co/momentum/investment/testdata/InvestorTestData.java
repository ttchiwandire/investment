package za.co.momentum.investment.testdata;

import za.co.momentum.investment.dto.*;
import za.co.momentum.investment.enums.AccountType;
import za.co.momentum.investment.enums.Bank;
import za.co.momentum.investment.enums.ProductType;
import za.co.momentum.investment.model.Investor;
import za.co.momentum.investment.model.InvestorProduct;
import za.co.momentum.investment.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvestorTestData {

    public static Investor getInvestorData() {
        Investor investor = new Investor();
        investor.setId(1l);
        investor.setFirstname("Tatenda");
        return investor;
    }

    public static InvestorDTO getInvestorDTOMockData() {
        InvestorDTO investor = InvestorDTO.builder()
                .birthDate(LocalDate.of(2000, 10, 10))
                .address("7 Jacaranda Street")
                .firstname("Tate")
                .lastname("Gono")
                .build();
        return investor;
    }

    public static WithdrawalResponse getWithdrawalResponseMockData() {
        return WithdrawalResponse.builder()
                .closingBalance(BigDecimal.valueOf(100))
                .investorName("Tatenda")
                .amountWithdrawn(BigDecimal.valueOf(50))
                .build();
    }

    public static ProductDTO getProductDTOMockData() {
        ProductDTO productDTO = ProductDTO.builder()
                .productType(ProductType.SAVINGS)
                .id(1l)
                .currentBalance(BigDecimal.valueOf(100))
                .name("Tate")
                .build();
        return productDTO;
    }

    public static Investor getRetiredInvestorData() {
        Investor investor = new Investor();
        investor.setId(1l);
        investor.setFirstname("Tatenda");
        investor.setBirthDate(LocalDate.of(1940, 01, 01));
        return investor;
    }


    public static WithdrawalRequest getRetirementWithdrawalRequest() {
        return WithdrawalRequest.builder()
                .amount(BigDecimal.valueOf(100.00))
                .investorId(1l)
                .productType(ProductType.RETIREMENT)
                .bankingDetails(BankingDetailsDTO.builder()
                        .accountNumber("123355")
                        .accountOwner("Tatenda")
                        .accountType(AccountType.CHEQUE)
                        .bank(Bank.FNB)
                        .build())
                .build();
    }

    public static WithdrawalRequest getSavingsWithdrawalRequest() {
        return WithdrawalRequest.builder()
                .amount(BigDecimal.valueOf(100.00))
                .investorId(1l)
                .productType(ProductType.SAVINGS)
                .bankingDetails(BankingDetailsDTO.builder()
                        .accountNumber("123355")
                        .accountOwner("Tatenda")
                        .accountType(AccountType.CHEQUE)
                        .bank(Bank.FNB)
                        .build())
                .build();
    }


    public static InvestorProduct getInvestorProduct() {
        InvestorProduct investorProduct = new InvestorProduct();
        investorProduct.setInvestor(getInvestorData());
        investorProduct.setProduct(getProduct());
        investorProduct.setCurrentBalance(BigDecimal.valueOf(10000));
        return investorProduct;
    }

    public static Product getProduct() {
        Product product = new Product();
        product.setProductType(ProductType.RETIREMENT);
        product.setName("Savings");
        return product;
    }
}
