package io.github.lsj8367.account.application.port.in;

import io.github.lsj8367.account.domain.Account.AccountId;
import io.github.lsj8367.account.domain.Money;

public interface GetAccountBalanceQuery {

    Money getAccountBalance(AccountId accountId);

}
