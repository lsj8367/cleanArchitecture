package io.github.lsj8367.account.application.port.out;

import io.github.lsj8367.account.domain.Account;
import io.github.lsj8367.account.domain.Account.AccountId;
import java.time.LocalDateTime;

public interface LoadAccountPort {

    Account loadAccount(AccountId accountId, LocalDateTime baselineDate);

}
