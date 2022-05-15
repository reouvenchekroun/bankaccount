package bankaccount.exceptions;

public class UnauthorizedWithrawalException extends Exception {

    public UnauthorizedWithrawalException(String message) {
        super(message);
    }
}
