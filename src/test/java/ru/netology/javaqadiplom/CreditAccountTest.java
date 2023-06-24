package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class CreditAccountTest {
    

    @ParameterizedTest
    @CsvFileSource (files = "src/test/resources/balance.csv")
    public void shouldAddToPositiveBalance(int expected, int initialBalance, int creditLimit, int rate, int addToCard){
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        account.add(addToCard);

        Assertions.assertEquals(expected, account.getBalance());

    }

    @Test
    public void shouldThrowsWithRateUnderLimit(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account1 = new CreditAccount(0,500,-1);
        });
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/pay.csv")
    public  void shouldPayCardToCard(int expected, int initialBalance, int creditLimit, int rate, int payToCard) {
        Account account = new CreditAccount(initialBalance, creditLimit, rate);

        account.pay(payToCard);

        Assertions.assertEquals(expected,account.getBalance());
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/percent.csv")
    public void shouldAddPercent(int expected, int initialBalance, int creditLimit, int rate){
        Account account = new CreditAccount(initialBalance,creditLimit,rate);

        account.yearChange();

        Assertions.assertEquals(expected, account.yearChange());
    }
}
