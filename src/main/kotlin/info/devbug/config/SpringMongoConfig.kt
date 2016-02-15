package info.devbug.config

import com.mongodb.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

/**
 * @author Aliaksei Bahdanau
 */
@Configuration
@EnableMongoRepositories("info.devbug.article", "info.devbug.digest", "info.devbug.topic", "info.devbug.feedback")
open class SpringMongoConfig {

    @Bean
    @Throws(Exception::class)
    open fun mongoDbFactory(): MongoDbFactory {
        return SimpleMongoDbFactory(MongoClient(), "digest")
    }

    @Bean
    @Throws(Exception::class)
    open fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongoDbFactory())
    }
}