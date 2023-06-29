package exceptions;

public class InvalidGateIdException extends Exception {

    public InvalidGateIdException(String invalidGateId) {
        super(invalidGateId);
    }
}
