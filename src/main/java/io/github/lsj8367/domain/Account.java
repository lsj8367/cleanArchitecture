package io.github.lsj8367.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Account {

    private AccountId id;
    private Money baselineBalance;
    private ActivityWindow activityWindow;

    public Account(final AccountId id, final Money baselineBalance,
        final ActivityWindow activityWindow) {
        this.id = id;
        this.baselineBalance = baselineBalance;
        this.activityWindow = activityWindow;
    }

    public Money calulateBalance() {
        return Money.add(this.baselineBalance, this.activityWindow.calculateBalance(this.id));
    }

    public boolean withDraw(Money money, AccountId targetAccountId) {
        if (!mayWithdraw(money)) {
            return false;
        }

        Activity withdrawal = new Activity(this.id, this.id, targetAccountId, LocalDateTime.now(),
            money);
        this.activityWindow.addActivity(withdrawal);
        return true;
    }

    private boolean mayWithdraw(Money money) {
        return Money.add(this.calulateBalance(), money.negate()).isPositive();
    }

    public boolean deposit(Money money, AccountId sourceAccountId) {
        Activity deposit = new Activity(this.id, sourceAccountId, this.id, LocalDateTime.now(), money);
        this.activityWindow.addActivity(deposit);
        return true;
    }

}
