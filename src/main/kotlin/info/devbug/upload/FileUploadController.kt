package info.devbug.upload

import info.devbug.digest.service.DigestService
import info.devbug.digest.util.DigestReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import java.io.File

/**
 * @author Aliaksei Bahdanau.
 */

@Controller
class FileUploadController {

    @Autowired
    lateinit var digestService: DigestService

    @Autowired
    lateinit var digestReader: DigestReader

    @RequestMapping(value = "/upload", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun provideUploadInfo(): String {
        return "You can upload a file by posting (POST method) to this same URL."
    }

    @RequestMapping(value = "/upload", method = arrayOf(RequestMethod.POST))
    fun handleFileUpload(@RequestParam("file") file: MultipartFile,
                         @RequestParam("saveDigest", required = false) saveDigest: Boolean,
                         principal: UsernamePasswordAuthenticationToken?): String {
        val saveTo: String = getPathForSaving(file)
        file.transferTo(File(saveTo))

        if (saveDigest && (principal != null && principal.isAuthenticated)) {
            digestService.save(digestReader.readDigest(saveTo))

            return "redirect:/"
        }

        return "redirect:/digest?filePath=${saveTo}"
    }

    private fun getPathForSaving(file: MultipartFile): String {
        val tempDir = File(System.getProperty("java.io.tmpdir"))
        val uploadedFileName = file.originalFilename
        val saveTo = tempDir.absolutePath + File.separator + uploadedFileName
        return saveTo
    }
}