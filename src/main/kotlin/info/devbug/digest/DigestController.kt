package info.devbug.digest

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @author Aliaksei Bahdanau.
 */
@Controller
class DigestController {


    @RequestMapping("/digest")
    fun digest(model: Model): String {
        val digestParser = DigestParser()

        // TODO current file is used for tesing purpuse. Should be replaced.
        val digestFile = "src/test/resources/digest.json"
        val digest = digestParser.getDigest(digestFile)
        val digestMap = hashMapOf("title" to digest.title, "contributeTo" to digest.contributeTo,
                "topics" to digest.topics);

        model.addAllAttributes(digestMap)

        return "digest"
    }
}