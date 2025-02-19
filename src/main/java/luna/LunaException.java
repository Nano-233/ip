package luna;

/**
 * Represents an exception specific to the Luna application.
 * This exception is used to handle errors related to invalid user input or command execution.
 */
public class LunaException extends Exception {
    /**
     * Defines various types of errors that can occur within Luna.
     */
    public enum ErrorType {
        /**
         * Error type for incorrect command formats.
         */
        INVALID_FORMAT("Incorrect format :<\n"),

        /**
         * Error type for invalid task numbers provided by the user.
         */
        INVALID_TASK_NUMBER("Check the task number again to make sure it's correct~"),

        /**
         * Error type for unrecognized commands entered by the user.
         */
        UNKNOWN_COMMAND("Sorry :< I don't quite understand");

        private final String message;

        /**
         * Constructs an ErrorType with a predefined message.
         *
         * @param message The error message associated with the error type.
         */
        ErrorType(String message) {
            this.message = message;
        }

        /**
         * Retrieves the formatted error message by appending additional specific details.
         *
         * @param specificMessage Additional information regarding the specific error.
         * @return A detailed error message including the general and specific error descriptions.
         */
        public String getMessage(String specificMessage) {
            return message + specificMessage;
        }
    }

    /**
     * Constructs a LunaException with a specified error type and a detailed message.
     *
     * @param errorType       The category of the error.
     * @param specificMessage Additional details regarding the error.
     */
    public LunaException(ErrorType errorType, String specificMessage) {
        super(errorType.getMessage(specificMessage));
    }
}
