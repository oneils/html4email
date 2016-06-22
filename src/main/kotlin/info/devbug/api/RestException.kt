package info.devbug.api

/**
 * @author Aliaksei Bahdanau
 */
class RestException (val code: Int,
                          val exceptionMessage: String,
                          val detailedMessage: String) : RuntimeException() {

}