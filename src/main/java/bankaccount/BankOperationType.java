package bankaccount;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BankOperationType {

    DEPOSIT("+"),
    WITHDRAWAL("-");

    final String symbol;

}
