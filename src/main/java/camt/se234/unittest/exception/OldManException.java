package camt.se234.unittest.exception;

/**
 * Created by Dto on 3/20/2017.
 */
public class OldManException extends RuntimeException {
    public OldManException(){
        this("You are too old");
    }
    public OldManException(String message) {
        super(message);
    }
}
