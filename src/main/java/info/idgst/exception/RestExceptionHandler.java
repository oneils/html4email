package info.idgst.exception;

import info.devbug.api.RestErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler for {@link RestException}s.
 *
 * @author Aliaksei Bahdanau
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleInvalidRequest(RestException re, ServletWebRequest request) {

        RestErrorMessage error =
                new RestErrorMessage(HttpStatus.valueOf(request.getResponse().getStatus()), re.getCode(),
                        re.getExceptionMessage(), re.getDetailedMessage(), re.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(re, error, headers,
                HttpStatus.valueOf(request.getResponse().getStatus()), request);
    }
}
