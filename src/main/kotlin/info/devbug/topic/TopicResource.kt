package info.devbug.topic

import info.devbug.topic.service.TopicService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @author Aliaksei Bahdanau
 */
@RestController
@RequestMapping(value = "/api/v1/topics")
class TopicResource {

    private val topicService: TopicService

    @Autowired constructor(topicService: TopicService) {
        this.topicService = topicService
    }

    @RequestMapping(method = arrayOf(RequestMethod.GET), produces =  arrayOf("application/json"))
    fun topics(): ResponseEntity<List<TopicDto>> {
        val topics = topicService.findAll()
        return ResponseEntity(topics, HttpStatus.OK)
    }

    @RequestMapping(value = "/{topicId}", method = arrayOf(RequestMethod.GET))
    fun findTopicById(@PathVariable("topicId") topicId: Int): ResponseEntity<TopicDto> {
        return ResponseEntity(topicService.findById(topicId), HttpStatus.OK)
    }
}