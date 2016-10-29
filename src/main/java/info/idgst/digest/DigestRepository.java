package info.idgst.digest;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository for retrieving {@link Digest}.
 *
 * @author Aliaksei Bahdanau
 */
public interface DigestRepository extends PagingAndSortingRepository<Digest, String> {

    /**
     * Find {@link Digest} by specified title.
     *
     * @param title {@link Digest}'s title to find by.
     * @return {@link Digest}
     */
    Digest findByTitle(String title);
}
