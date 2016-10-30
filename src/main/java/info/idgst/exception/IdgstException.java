package info.idgst.exception;

/**
 * @author Aliaksei Bahdanau
 */
public class IdgstException extends RuntimeException {

    private int code;
    private String exceptionMessage;
    private String detailedMessage;

    public IdgstException(int code, String exceptionMessage, String detailedMessage) {
        this.code = code;
        this.exceptionMessage = exceptionMessage;
        this.detailedMessage = detailedMessage;
    }

    public IdgstException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }
}
