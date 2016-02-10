package info.devbug

import info.devbug.api.Topic
import info.devbug.topic.TopicDto
import info.devbug.topic.TopicRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


@Controller
@EnableAutoConfiguration
@ComponentScan (basePackages = arrayOf("info.devbug.*"))
class HomeController {

    private val topicRepository: TopicRepository

    @Autowired constructor(topicRepository: TopicRepository) {
        this.topicRepository = topicRepository

//        val topicList: List<TopicDto> = listOf(TopicDto("News", 1), TopicDto("Interesting", 1), TopicDto("NWhat to watch", 1))
//        topicRepository.save(topicList)
    }

    @RequestMapping("/")
    fun index(): String {
        return "index.html"
    }

    @RequestMapping("/test")
    @ResponseBody
    fun test() = "Hello from test url"
}

fun main(args: Array<String>) {
    SpringApplication.run(arrayOf(info.devbug.HomeController::class.java), args)
}
