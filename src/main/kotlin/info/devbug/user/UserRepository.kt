package info.devbug.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Aliaksei Bahdanau
 */
@Repository
interface UserRepository : CrudRepository<UserDto, String> {

    fun findByUserName(userName: String): UserDto
}