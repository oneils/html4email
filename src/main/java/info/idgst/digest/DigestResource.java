package info.idgst.digest;

import info.idgst.exception.DigestAlreadyExistsException;
import info.idgst.exception.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Endpoint for Digest.
 *
 * @author Aliaksei Bahdanau
 */
@RestController
@RequestMapping(value = "/api/v1/digests")
public class DigestResource {

    private final DigestService digestService;

    @Autowired
    public DigestResource(final DigestService digestService) {
        this.digestService = digestService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Digest>> digests(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "DESC") String sortDirection,
            @RequestParam(value = "sortBy", required = false, defaultValue = "publishedDate") String sortBy) {
        Page<Digest> digests = digestService.findAll(page, size, Sort.Direction.fromString(sortDirection), sortBy);
        if (!digests.hasContent()) {
            return new ResponseEntity<>(digests, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(digests, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Digest> findById(@PathVariable("id") String id) {
        Digest digest = digestService.findById(id);
        if (digest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(digest, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Digest> save(@RequestBody Digest digest) {
        Digest savedDigest;
        try {
            savedDigest = digestService.save(digest);
        } catch (DigestAlreadyExistsException e) {
            throw new RestException(0, "Error while saving Digest", e.getMessage());
        }

        URI pollURI = ServletUriComponentsBuilder.fromCurrentRequest()
                                                 .path("/{id}")
                                                 .buildAndExpand(savedDigest.getId())
                                                 .toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(pollURI);

        return new ResponseEntity<>(savedDigest, responseHeaders, HttpStatus.CREATED);
    }
}
