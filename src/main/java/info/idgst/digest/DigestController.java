package info.idgst.digest;

import info.idgst.config.IdgstConfigReader;
import info.idgst.digest.reader.DigestReader;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Controller for generating Digest HTML template from the Json file.
 *
 * @author Aliaksei Bahdanau.
 */
@Controller
public class DigestController {

    private IdgstConfigReader idgstConfigReader;
    private DigestReader digestReader;

    @Autowired
    public DigestController(IdgstConfigReader idgstConfigReader, DigestReader digestReader) {
        this.idgstConfigReader = idgstConfigReader;
        this.digestReader = digestReader;
    }

    @RequestMapping(value = "/digest", method = RequestMethod.POST)
    public String digest(@RequestParam("filePath") String filePath, Model model) {
        Digest digest = digestReader.readDigest(filePath);

        model.addAttribute("digest", digest);
        model.addAttribute("digestNumber", getDigestNumber(digest.getTitle()));
        model.addAttribute("currentYear", new Date());
        model.addAttribute("archiveHost", idgstConfigReader.getArchiveHost());

        return "digest";
    }

    private int getDigestNumber(String digestTitle) {
        String digestNumberDelimiter = "#";

        return NumberUtils.toInt(digestTitle.split(digestNumberDelimiter)[1]);
    }
}
