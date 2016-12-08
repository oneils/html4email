package info.idgst.digest;

import info.idgst.exception.DigestAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Default implementation of {@link DigestService}.
 *
 * @author Aliaksei Bahdanau
 */
@Service
public class DefaultDigestService implements DigestService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DigestCache digestCache;
    private final DigestRepository digestRepository;

    @Autowired
    public DefaultDigestService(final DigestCache digestCache, final DigestRepository digestRepository) {
        this.digestCache = digestCache;
        this.digestRepository = digestRepository;
    }

    @Override
    public Digest findByTitle(String digestTitle) {
        return digestCache.fetchByTitle(digestTitle);
    }

    @Override
    public Digest findById(String digestID) {
        return digestCache.fetch(digestID);
    }

    @Override
    public Digest save(Digest digest) {
        Digest existingDigest = digestCache.fetchByTitle(digest.getTitle());
        if (existingDigest != null) {
            String msg =
                    "Attempt of saving duplicated digest with title: " + digest.getTitle() + "Existing Digest ID: " +
                    existingDigest.getId();
            logger.warn(msg);
            throw new DigestAlreadyExistsException(msg);
        }
        return digestRepository.save(digest);
    }

    @Override
    public Page<Digest> findAll(int page, int size, Sort.Direction sortDirection, String sortBy) {
        return digestCache.fetch(page, size, sortDirection, sortBy);
    }
}
