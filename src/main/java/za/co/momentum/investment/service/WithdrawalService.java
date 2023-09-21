package za.co.momentum.investment.service;

import za.co.momentum.investment.dto.WithdrawalRequest;
import za.co.momentum.investment.dto.WithdrawalResponse;

public interface WithdrawalService {

    WithdrawalResponse withdrawFromInvestments(WithdrawalRequest request);
}

