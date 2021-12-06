package io.github.lsj8367.account.application.port.out;

import io.github.lsj8367.account.domain.Account;

public interface AccountLock {

    void lockAccount(Account.AccountId accountId);

    void releaseAccount(Account.AccountId accountId);

}
