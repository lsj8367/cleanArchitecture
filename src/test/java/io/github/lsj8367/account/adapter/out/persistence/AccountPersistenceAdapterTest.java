package io.github.lsj8367.account.adapter.out.persistence;

import static io.github.lsj8367.common.AccountTestData.defaultAccount;
import static org.assertj.core.api.Assertions.assertThat;
import io.github.lsj8367.account.domain.Account;
import io.github.lsj8367.account.domain.Account.AccountId;
import io.github.lsj8367.account.domain.ActivityWindow;
import io.github.lsj8367.account.domain.Money;
import io.github.lsj8367.common.AccountTestData;
import io.github.lsj8367.common.ActivityTestData;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Import({AccountPersistenceAdapter.class, AccountMapper.class})
class AccountPersistenceAdapterTest {

    @Autowired
    private AccountPersistenceAdapter adapterUnderTest;

    @Autowired
    private ActivityRepository activityRepository;

    @Test
    @Sql("AccountPersistenceAdapterTest.sql")
    void loadsAccount() {
        Account account = adapterUnderTest.loadAccount(new AccountId(1L), LocalDateTime.of(2018, 8, 10, 0, 0));

        assertThat(account.getActivityWindow().getActivities()).hasSize(2);
        assertThat(account.calculateBalance()).isEqualTo(Money.of(500));
    }

    @Test
    void updatesActivities() {
        Account account = defaultAccount()
            .withBaselineBalance(Money.of(555L))
            .withActivityWindow(new ActivityWindow(
                ActivityTestData.defaultActivity()
                    .withId(null)
                    .withMoney(Money.of(1L)).build()))
            .build();

        adapterUnderTest.updateActivities(account);

        assertThat(activityRepository.count()).isEqualTo(1);

        ActivityJpaEntity savedActivity = activityRepository.findAll().get(0);
        assertThat(savedActivity.getAmount()).isEqualTo(1L);
    }

}