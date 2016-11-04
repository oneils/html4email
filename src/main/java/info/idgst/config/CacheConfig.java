package info.idgst.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Cache configuration.
 *
 * @author Aliaksei Bahdanau
 */
//@EnableCaching
//@Configuration
public class CacheConfig {

    @Bean
    GuavaCacheManager cacheManager() {
        GuavaCacheManager cacheManager = new GuavaCacheManager("searches");
        cacheManager.setCacheBuilder(CacheBuilder.newBuilder()
                                                 .softValues()
                                                 .expireAfterWrite(14, TimeUnit.DAYS));
        return cacheManager;
    }
}
