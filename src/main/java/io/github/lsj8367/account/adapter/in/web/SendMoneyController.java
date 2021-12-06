package io.github.lsj8367.account.adapter.in.web;

import io.github.lsj8367.account.application.port.in.SendMoneyCommand;
import io.github.lsj8367.account.application.port.in.SendMoneyUseCase;
import io.github.lsj8367.account.domain.Account;
import io.github.lsj8367.account.domain.Account.AccountId;
import io.github.lsj8367.account.domain.Money;
import io.github.lsj8367.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
class SendMoneyController {

    private final SendMoneyUseCase sendMoneyUseCase;

    @PostMapping(path = "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    void sendMoney(
        @PathVariable("sourceAccountId") Long sourceAccountId,
        @PathVariable("targetAccountId") Long targetAccountId,
        @PathVariable("amount") Long amount) {

        SendMoneyCommand command = new SendMoneyCommand(
            new AccountId(sourceAccountId),
            new AccountId(targetAccountId),
            Money.of(amount));

        sendMoneyUseCase.sendMoney(command);
    }

}
