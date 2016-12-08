package info.idgst.exception;

/**
 * Exception for Rest API errors.
 *
 * @author Aliaksei Bahdanau
 */
public class RestException extends RuntimeException {

    private int code;
    private String exceptionMessage;
    private String detailedMessage;

    public RestException(int code, String exceptionMessage, String detailedMessage) {
        this.code = code;
        this.exceptionMessage = exceptionMessage;
        this.detailedMessage = detailedMessage;
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
