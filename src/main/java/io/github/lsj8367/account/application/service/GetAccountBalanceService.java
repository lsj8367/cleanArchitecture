package io.github.lsj8367.account.application.service;

import io.github.lsj8367.account.application.port.in.GetAccountBalanceQuery;
import io.github.lsj8367.account.application.port.out.LoadAccountPort;
import io.github.lsj8367.account.domain.Account.AccountId;
import io.github.lsj8367.account.domain.Money;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class GetAccountBalanceService implements GetAccountBalanceQuery {

    private final LoadAccountPort loadAccountPort;

    @Override
    public Money getAccountBalance(AccountId accountId) {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now())
            .calculateBalance();
    }

}
