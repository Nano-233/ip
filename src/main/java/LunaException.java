public class LunaException extends Exception {
  public LunaException(Luna.ErrorType errorType, String specificMessage) {
    super(errorType.getMessage(specificMessage));
  }
}
