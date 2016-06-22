package info.devbug.api

import org.springframework.http.HttpStatus

/**
 * @author Aliaksei Bahdanau
 */
data class RestErrorMessage(val status: HttpStatus,
                            val code: Int,
                            val message: String = "Default message",
                            val detailedMessage: String = "Default detailed message",
                            val exceptionMessage: String = "Default exception message") {
}