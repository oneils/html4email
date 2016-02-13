package info.devbug.user

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author Aliaksei Bahdanau
 */

@Document(collection = "users")
class UserDto() {
    @Id
    var id: String? = null

    @NotEmpty
    var userName: String = ""

    @NotEmpty
    @JsonIgnore
    var password: String = ""

    @NotEmpty
    var firstName: String = ""

    @NotEmpty
    var lastName: String = ""

    @NotEmpty
    var admin: Boolean = false


}