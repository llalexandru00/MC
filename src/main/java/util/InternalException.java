package util;

/**
 * Application-level exception used to mark internal problems
 */
public class InternalException extends RuntimeException
{
    /**
     * Basic constructor
     * @param message
     * The message to be given to the output
     */
    public InternalException(String message)
    {
        super(message);
    }
}
