package info.idgst.digest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * Cache for {@link Digest}s.
 *
 * @author Aliaksei Bahdanau
 */
public interface DigestCache {

    /**
     * Fetches {@link Digest} by specified digest title.
     *
     * @param title Digest's title to fetch by
     * @return {@link Digest}
     */
    Digest fetchByTitle(String title);

    /**
     * Fetches {@link Digest} by specified Digest id.
     *
     * @param digestID Digest's ID to fetch by
     * @return {@link Digest}
     */
    Digest fetch(String digestID);

    /**
     * Fetches {@link Digest} by specified page, size, sort direction and sort field.
     *
     * @param page          the number of the page
     * @param size          the amount of {@link Digest}s to retrieve
     * @param sortDirection Ascending or Descending sorting direction of type {@link Sort.Direction}
     * @param sortBy        by which field to sort retrieved {@link Digest}
     * @return {@link Page} of {@link Digest}s
     */
    Page<Digest> fetch(int page, int size, Sort.Direction sortDirection, String sortBy);

    Digest put(Digest digest);
}
