package info.idgst.digest;

import info.idgst.config.IdgstConfigReader;
import info.idgst.digest.reader.DigestReader;
import info.idgst.exception.IdgstException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

/**
 * Controller for generating Digest HTML template from the Json file.
 *
 * @author Aliaksei Bahdanau.
 */
@Controller
public class DigestController {

    private static final String DIGEST_NUMBER_DELIMITER = "#";
    private final IdgstConfigReader idgstConfigReader;
    private final DigestReader digestReader;
    private final DigestService digestService;
    private final DigestEmailService digestEmailService;

    @Autowired
    public DigestController(final IdgstConfigReader idgstConfigReader, final DigestReader digestReader,
                            final DigestService digestService, DigestEmailService digestEmailService) {
        this.idgstConfigReader = idgstConfigReader;
        this.digestReader = digestReader;
        this.digestService = digestService;
        this.digestEmailService = digestEmailService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam(value = "saveDigest", required = false) boolean saveDigest,
                                   @RequestParam(value = "sendEmail", required = false) boolean sendEmail,
                                   @RequestParam(value = "sendTo", required = false) String sendTo,
                                   UsernamePasswordAuthenticationToken principal, Model model) throws IOException {

        final Optional<byte[]> fileContent = Optional.ofNullable(file.getBytes());

        final Digest digest = digestReader.readDigest(fileContent.orElseThrow(
                () -> new IdgstException("Specified file is empty")));

        if (saveDigest && hasAccessRights(principal)) {
            digestService.save(digest);
        }

        prepareModel(model, digest);
        model.addAttribute("sendEmail", sendEmail);
        model.addAttribute("digestTitle", digest.getTitle());

        if (sendEmail && hasAccessRights(principal)) {
            model.addAttribute("sendTo", sendTo);
            digestEmailService.sendViaEmail(model.asMap());
        }

        return "digest";
    }

    private void prepareModel(Model model, Digest digest) {
        model.addAttribute("digest", digest);

        int digestNumber = NumberUtils.toInt(digest.getTitle()
                .split(DIGEST_NUMBER_DELIMITER)[1]);
        model.addAttribute("digestNumber", digestNumber);

        model.addAttribute("currentYear", new Date());
        model.addAttribute("archiveHost", idgstConfigReader.getArchiveHost());
        model.addAttribute("contributeTo", idgstConfigReader.getContributeTo());
        model.addAttribute("companyName", idgstConfigReader.getCompanyName());
    }

    private boolean hasAccessRights(UsernamePasswordAuthenticationToken principal) {
        return principal != null && principal.isAuthenticated() && principal.getAuthorities()
                .contains(new SimpleGrantedAuthority(
                        "ROLE_ADMIN"));
    }
}
