package za.co.momentum.investment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.momentum.investment.dto.WithdrawalRequest;
import za.co.momentum.investment.dto.WithdrawalResponse;
import za.co.momentum.investment.service.WithdrawalService;

@RestController
@RequiredArgsConstructor
public class WithdrawalController {

    private final WithdrawalService withdrawalService;

    @PostMapping(path = "/withdraw-investments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WithdrawalResponse> withdrawInvestmenst(@Valid @RequestBody WithdrawalRequest request) {
        return ResponseEntity.ok(withdrawalService.withdrawFromInvestments(request));
    }
}
