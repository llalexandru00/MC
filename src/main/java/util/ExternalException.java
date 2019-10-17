package util;

/**
 * Application-level exception used to mark output worthy errors
 */
public class ExternalException extends RuntimeException
{
    /**
     * Basic constructor
     * @param message
     * The message to be written
     */
    public ExternalException(String message)
    {
        super(message);
    }
}
