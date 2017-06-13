package info.idgst.digest;

import info.idgst.config.GuavaCacheConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Default implementation of {@link DigestCache}.
 *
 * @author Aliaksei Bahdanau
 */
@Service
public class DefaultDigestCache implements DigestCache {
    private final Log logger = LogFactory.getLog(getClass());

    private final DigestRepository digestRepository;

    @Autowired
    public DefaultDigestCache(final DigestRepository digestRepository) {
        this.digestRepository = digestRepository;
    }

    @Override
    @Cacheable(value = GuavaCacheConfig.DIGESTS_CACHE,  key="#title")
    public Digest fetchByTitle(String title) {
        return digestRepository.findByTitle(title);
    }

    @Override
    @Cacheable(value = GuavaCacheConfig.DIGESTS_CACHE, key="#digestID")
    public Digest fetch(String digestID) {
        return digestRepository.findOne(digestID);
    }

    @Override
    public Page<Digest> fetch(int page, int size, Sort.Direction sortDirection, String sortBy) {
        PageRequest pageRequest = new PageRequest(page, size, sortDirection, sortBy);
        logger.info("Digests not found in cache. Loading from DB");
        return digestRepository.findAll(pageRequest);
    }

    @Override
    @CachePut(value = GuavaCacheConfig.DIGESTS_CACHE, key = "#digest.title")
    public Digest put(Digest digest) {
        logger.info(String.format("Putting digest (%s) to cache", digest.getTitle()));
        return digestRepository.save(digest);
    }
}
