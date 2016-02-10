package info.devbug.digest

import info.devbug.digest.service.DigestServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * Controller for generating Digest HTML template from the Json file.
 *
 * @author Aliaksei Bahdanau.
 */
@Controller
class DigestController {
    private val digestService: DigestServiceImpl

    @Autowired constructor(digestService: DigestServiceImpl) {
        this.digestService = digestService
    }

    @RequestMapping("/digest")
    fun digest(@RequestParam("filePath") filePath: String, model: Model): String {
        val digest = digestService.getDigest(filePath)
        model.addAttribute("digest", digest)

        val digestNumber = digestService.getDigestNumber(digest.title)
        model.addAttribute("digestNumber", digestNumber)

        return "digest"
    }
}