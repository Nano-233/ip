package luna;

public class LunaException extends Exception {
  public enum ErrorType {
    INVALID_FORMAT("Incorrect format :< "),
    INVALID_TASK_NUMBER("Check the task number again to make sure it's correct~"),
    UNKNOWN_COMMAND("Sorry :< I don't quite understand");

    private final String message;

    ErrorType(String message) {
      this.message = message;
    }

    public String getMessage(String specificMessage) {
      return message + specificMessage;
    }
  }

  public LunaException(ErrorType errorType, String specificMessage) {
    super(errorType.getMessage(specificMessage));
  }
}
