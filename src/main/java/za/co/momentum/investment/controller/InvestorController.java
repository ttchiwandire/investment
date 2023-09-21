package za.co.momentum.investment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.momentum.investment.dto.InvestorDTO;
import za.co.momentum.investment.dto.ProductDTO;
import za.co.momentum.investment.enums.ProductType;
import za.co.momentum.investment.service.InvestorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InvestorController {

    private final InvestorService investorService;

    @GetMapping(path = "/investor-details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InvestorDTO> findInvestorById(
            @RequestParam(required = true) Long id) {
        return ResponseEntity.ok(investorService.getInvestorById(id));
    }

    @GetMapping(path = "/investors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InvestorDTO>> findAllInvestors() {
        return ResponseEntity.ok(investorService.getAllInvestors());
    }

    @GetMapping(path = "/investor-by-product-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InvestorDTO>> findAllInvestorsByProductType(@RequestParam(required = true) ProductType productType) {
        return ResponseEntity.ok(investorService.getAllInvestorsByProductType(productType));
    }

    @GetMapping(path = "/investor-products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> findInvestorProducts(
            @RequestParam(required = true) Long id) {
        return ResponseEntity.ok(investorService.getInvestorProducts(id));
    }
}
