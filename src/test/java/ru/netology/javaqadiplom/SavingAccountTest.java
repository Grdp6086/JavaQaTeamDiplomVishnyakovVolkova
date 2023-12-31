package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void rateShouldMoreOrEqualsThenZero() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2000, 5, 10000, -5);
        });
    }

    @Test
    public void minBalanceShouldMoreOrEqualsThenZero() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2000,
                    -5, 10000, 5);
        });
    }

    @Test
    public void initialBalanceShouldMoreThenMinBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(1000,
                    2000, 10000, 5);
        });
    }

    @Test
    public void minBalanceShouldLessThenMaxBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2000,
                    3000, 2000, 5);
        });
    }

    @Test
    public void payAmountLessThenZero() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, account.pay(-5));
    }

    @Test
    public void payAmountMoreThenZero() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(true, account.pay(5));
        Assertions.assertEquals(1995, account.getBalance());
    }

    @Test
    public void balanceLessThenMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, account.pay(1001));

    }

    @Test
    public void balanceEqualsMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(true, account.pay(1000));

    }

    @Test
    public void balanceEqualsMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(true, account.add(8000));

    }

    @Test
    public void addAmountMoreThenZero() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(true, account.add(2000));
        Assertions.assertEquals(4000, account.getBalance());
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/YearChange.csv")
    public void shouldCalculatePercent(int expected, int initialBalance, int minBalance, int maxBalance, int rate) {
        Account account = new SavingAccount(
                initialBalance,
                minBalance,
                maxBalance,
                rate);

        Assertions.assertEquals(expected, account.yearChange());
    }

    @Test
    public void shouldReturnFalseWithAmountUnderZero() {
        Account account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);

        Assertions.assertEquals(false, account.add(-1));
        Assertions.assertEquals(false, account.add(0));
        Assertions.assertEquals(true, account.add(8_000));
        Assertions.assertEquals(false, account.add(8_001));
    }
}
