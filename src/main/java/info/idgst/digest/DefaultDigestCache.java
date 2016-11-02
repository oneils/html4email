package info.idgst.digest;

import org.springframework.beans.factory.annotation.Autowired;
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

    private final DigestRepository digestRepository;

    @Autowired
    public DefaultDigestCache(DigestRepository digestRepository) {
        this.digestRepository = digestRepository;
    }

    @Override
    @Cacheable("searches")
    public Digest fetchByTitle(String title) {
        return digestRepository.findByTitle(title);
    }

    @Override
    @Cacheable("searches")
    public Digest fetch(String digestID) {
        return digestRepository.findOne(digestID);
    }

    @Override
    @Cacheable("searches")
    public Page<Digest> fetch(int page, int size, Sort.Direction sortDirection, String sortBy) {
        PageRequest pageRequest = new PageRequest(page, size, sortDirection, sortBy);
        return digestRepository.findAll(pageRequest);
    }
}
