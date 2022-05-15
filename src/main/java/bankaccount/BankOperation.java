package bankaccount;

import lombok.AllArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
public class BankOperation {

    private Date date;
    private BankOperationType type;
    private double amount;
    private double balanceAfterOperation;

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date) + " " + type.symbol + amount + " " + balanceAfterOperation;
    }
}
