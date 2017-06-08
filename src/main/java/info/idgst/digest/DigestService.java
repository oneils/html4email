package info.idgst.digest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Map;

/**
 * Default implementation of {@link DigestService}.
 *
 * @author Aliaksei Bahdanau
 */
public interface DigestService {

    /**
     * Find {@link Digest} by specified Digest's title.
     *
     * @param digestTitle {@link Digest}'s title
     * @return {@link Digest}
     */
    Digest findByTitle(String digestTitle);

    /**
     * Finds {@link Digest} by specified id.
     *
     * @param digestID {@link Digest}'s id
     * @return {@link Digest}
     */
    Digest findById(String digestID);

    /**
     * Saves specified {@link Digest}. {@link info.idgst.exception.DigestAlreadyExistsException} is thrown in case
     * when such Digest already exists.
     *
     * @param digest {@link Digest} to be saved
     * @return saved {@link Digest}
     */
    Digest save(Digest digest);

    /**
     * Finds sorted {@link Digest}s by specified page, size, sort direction and sorted by field.
     *
     * @param page          the page with digests
     * @param size          the amount of digest to be shown on page
     * @param sortDirection Ascending or Descending sort direction
     * @param sortBy        by which field to sort digests
     * @return {@link Page}
     */
    Page<Digest> findAll(int page, int size, Sort.Direction sortDirection, String sortBy);

    void sendViaEmail(Digest digest, Map<String, Object> stringObjectMap);
}
