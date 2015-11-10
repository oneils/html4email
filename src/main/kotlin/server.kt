import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody


@Controller
@EnableAutoConfiguration
class HomeController {

    @RequestMapping("/")
    @ResponseBody
    fun index() = "HTML4Email Kotlin"
}

fun main(args: Array<String>) {
    SpringApplication.run(arrayOf(HomeController::class.java), args)
}
