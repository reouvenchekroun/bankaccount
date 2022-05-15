package bankaccount;


import bankaccount.exceptions.UnauthorizedWithrawalException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static bankaccount.BankOperationType.DEPOSIT;
import static bankaccount.BankOperationType.WITHDRAWAL;

public class BankAccount {

    @Getter
    @Setter
    private double balance;

    @Getter
    List<BankOperation> operations;

    public BankAccount() {
        this.balance = 0;
        this.operations = new ArrayList<>();
    }

    public void depositAmountOnAccount(double amount){
        checkAmountValue(amount);
        balance += amount;
        addOperation(DEPOSIT, amount, balance);
    }

    public void withdrawAmountOnAccount(double amount) throws UnauthorizedWithrawalException {
        checkAmountValue(amount);
        if(amount <= balance) {
            balance -= amount;
            addOperation(WITHDRAWAL, amount, balance);
        }else{
            throw new UnauthorizedWithrawalException("Withrawal amount " + amount + " is superior to account balance " + balance);
        }
    }

    private void checkAmountValue(double amount) {
        if(amount <= 0){
            throw new IllegalArgumentException("Deposit/withdrawal amount must be superior to zero");
        }
    }

    public String displayOperationHistory(){
        StringBuilder operationsHistory = new StringBuilder();
        for(BankOperation operation : operations) {
            operationsHistory.append(operation.toString()).append("\n");
        }
        return operationsHistory.toString();
    }

    private void addOperation(BankOperationType type, double amount, double balanceAfterOperation) {
        BankOperation operation = new BankOperation(new Date(), type, amount, balanceAfterOperation);
        operations.add(operation);
    }
}
