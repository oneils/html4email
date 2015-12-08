package info.devbug.digest

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * @author Aliaksei Bahdanau.
 */
@Controller
class DigestController {

    @RequestMapping("/digest")
    fun digest(@RequestParam("filePath") filePath: String, model: Model): String {
        val digestParser = DigestParser()

        val digest = digestParser.getDigest(filePath)
        val digestMap = hashMapOf("title" to digest.title, "contributeTo" to digest.contributeTo,
                "companyName" to digest.companyName,
                "topics" to digest.topics);

        model.addAllAttributes(digestMap)

        return "digest"
    }
}