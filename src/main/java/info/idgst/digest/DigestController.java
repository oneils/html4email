package info.idgst.digest;

import info.idgst.config.IdgstConfigReader;
import info.idgst.digest.reader.DigestReader;
import info.idgst.exception.IdgstException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * Controller for generating Digest HTML template from the Json file.
 *
 * @author Aliaksei Bahdanau.
 */
@Controller
public class DigestController {

    protected static final String DIGEST_NUMBER_DELIMITER = "#";
    private IdgstConfigReader idgstConfigReader;
    private DigestReader digestReader;
    private DigestService digestService;

    @Autowired
    public DigestController(IdgstConfigReader idgstConfigReader, DigestReader digestReader, DigestService digestService) {
        this.idgstConfigReader = idgstConfigReader;
        this.digestReader = digestReader;
        this.digestService = digestService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam(value = "saveDigest", required = false) boolean saveDigest,
                                   UsernamePasswordAuthenticationToken principal,
                                   Model model) throws IOException {

        byte[] fileContent = file.getBytes();
        if (fileContent == null) {
            throw new IdgstException("Specified file is empty");
        }

        final Digest digest = digestReader.readDigest(fileContent);
        if (saveDigest && (principal != null && principal.isAuthenticated())) {
            digestService.save(digest);
            return "redirect:/";
        }

        model.addAttribute("digest", digest);
        int digestNumber = NumberUtils.toInt(digest.getTitle().split(DIGEST_NUMBER_DELIMITER)[1]);
        model.addAttribute("digestNumber", digestNumber);
        model.addAttribute("currentYear", new Date());
        model.addAttribute("archiveHost", idgstConfigReader.getArchiveHost());
        return "digest";
    }
}
