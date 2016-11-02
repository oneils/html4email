package info.devbug

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@EnableAutoConfiguration
@ComponentScan (basePackages = arrayOf("info.devbug.*"))
class HomeController {

    @RequestMapping("/")
    fun index(): String {
        return "index.html"
    }
}

//fun main(args: Array<String>) {
//    SpringApplication.run(arrayOf(info.devbug.HomeController::class.java), args)
//}
