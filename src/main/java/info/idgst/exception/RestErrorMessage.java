package info.idgst.exception;

import org.springframework.http.HttpStatus;
/**
 * @author Aliaksei Bahdanau.
 */
public class RestErrorMessage {

    private HttpStatus status;
    private String message;
    private String detailedMessage;
    private String exceptionMessage;

    public RestErrorMessage(HttpStatus status, String message, String detailedMessage, String exceptionMessage) {
        this.status = status;
        this.message = message;
        this.detailedMessage = detailedMessage;
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
