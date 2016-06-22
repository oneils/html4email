package info.devbug.exceptions

import info.devbug.api.RestErrorMessage
import info.devbug.api.RestException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * @author Aliaksei Bahdanau
 */
@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(Exception::class)
    protected fun handleInvalidRequest(e: RestException, request: ServletWebRequest): ResponseEntity<Any> {
        val error = RestErrorMessage(HttpStatus.valueOf(request.getResponse().getStatus()),
                e.code,
                e.exceptionMessage,
                e.detailedMessage,
                e.toString())
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        return handleExceptionInternal(e, error, headers, HttpStatus.OK, request)
    }
}