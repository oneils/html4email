package info.devbug.upload

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

    @RequestMapping(value = "/upload", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun provideUploadInfo(): String {
        return "You can upload a file by posting (POST method) to this same URL.";
    }

    @RequestMapping(value = "/upload", method = arrayOf(RequestMethod.POST))
    fun handleFileUpload(@RequestParam("file") file: MultipartFile): String {
        val saveTo = getPathForSaving(file)

        file.transferTo(File(saveTo))

        return "redirect:/digest?filePath=${saveTo}"
    }

    private fun getPathForSaving(file: MultipartFile): String {
        val tempDir = File(System.getProperty("java.io.tmpdir"))
        val uploadedFileName = file.originalFilename
        val saveTo = tempDir.absolutePath + File.separator + uploadedFileName
        return saveTo
    }
}