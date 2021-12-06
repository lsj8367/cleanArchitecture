package io.github.lsj8367.account.application.port.out;

import io.github.lsj8367.account.domain.Account;

public interface UpdateAccountStatePort {

    void updateActivities(Account account);

}
