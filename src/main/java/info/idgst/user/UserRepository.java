package info.idgst.user;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for retrieving Users.
 *
 * @author Aliaksei Bahdanau
 */
public interface UserRepository extends CrudRepository<AppUser, String> {

    AppUser findByUserName(String userName);
}
