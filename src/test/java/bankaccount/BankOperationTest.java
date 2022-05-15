package bankaccount;

import bankaccount.exceptions.UnauthorizedWithrawalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankOperationTest {

    BankAccount accountTest;

    @BeforeEach
    void start(){
        accountTest = new BankAccount();
    }

    @Test
    void shouldAddMoneyAmountOnBalance_whenDepositAmountIsSuperiorToZero(){
        accountTest.depositAmountOnAccount(50);
        assertEquals(50, accountTest.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10, 0})
    void shouldNotAddMoneyAmountOnBalance_whenDepositAmountIsInferiorOrEqualToZero(double amount){
        assertThrows(IllegalArgumentException.class, () ->
                accountTest.depositAmountOnAccount(amount));
    }

    @ParameterizedTest
    @CsvSource({
            "80,20",
            "100,0",
    })
    void shouldWithdrawMoneyFromBalance_whenAmountIsInferiorOrEqualToBalance(double withdrawalAmount, double expectedBalance) throws UnauthorizedWithrawalException {
        accountTest.depositAmountOnAccount(100);
        accountTest.withdrawAmountOnAccount(withdrawalAmount);
        assertEquals(expectedBalance, accountTest.getBalance());
    }

    @Test
    void shouldNotWithdrawMoneyFromBalance_whenAmountIsSuperiorToBalance() {
        accountTest.depositAmountOnAccount(10);

        assertThrows(UnauthorizedWithrawalException.class, () ->
                accountTest.withdrawAmountOnAccount(50));
    }

    @Test
    void shouldNotDisplayHistory_whenNoOperationRealised() {
        assertEquals("", accountTest.displayOperationHistory());
    }

    @Test
    void shouldDisplayHistory_WhenOperationRealised() throws UnauthorizedWithrawalException {
        accountTest.depositAmountOnAccount(10);
        accountTest.withdrawAmountOnAccount(4);

        String expectedHistory = getTodayDate() + " +10.0 10.0\n" +
                getTodayDate() + " -4.0 6.0\n";

        assertEquals(expectedHistory, accountTest.displayOperationHistory());

    }

    private String getTodayDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(new Date());
    }


}
