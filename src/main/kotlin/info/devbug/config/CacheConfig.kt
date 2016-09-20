package info.devbug.config

import com.google.common.cache.CacheBuilder
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.guava.GuavaCacheManager
import org.springframework.context.annotation.Bean
import java.util.concurrent.TimeUnit

/**
 * Cache configuration.
 *
 * @author Aliaksei Bahdanau
 */
@EnableCaching
open class CacheConfig {

    @Bean
    open fun cacheManager(): GuavaCacheManager {
        val cacheManager = GuavaCacheManager("searches")
        cacheManager.setCacheBuilder(CacheBuilder
                .newBuilder()
                .softValues()
                .expireAfterWrite(14, TimeUnit.DAYS))
        return cacheManager
    }
}