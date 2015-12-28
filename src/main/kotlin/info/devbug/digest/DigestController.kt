package info.devbug.digest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * @author Aliaksei Bahdanau.
 */
@Controller
class DigestController {
    private val digestService: DigestService

    @Autowired constructor(digestService: DigestService) {
        this.digestService = digestService
    }

    @RequestMapping("/digest")
    fun digest(@RequestParam("filePath") filePath: String, model: Model): String {
        val digestParser = DigestParser()

        val digest = digestParser.getDigest(filePath)
        model.addAttribute("digest", digest)

        return "digest"
    }
}