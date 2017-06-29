package info.idgst.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Cache configuration.
 *
 * @author Aliaksei Bahdanau
 */
@Configuration
@EnableCaching
public class GuavaCacheConfig {

    public static final String DIGESTS_CACHE = "digests";

    @Bean
    GuavaCacheManager cacheManager() {
        GuavaCacheManager cacheManager = new GuavaCacheManager(DIGESTS_CACHE);
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
                .softValues()
                .expireAfterWrite(14, TimeUnit.DAYS);
        cacheManager.setCacheBuilder(cacheBuilder);
        return cacheManager;
    }
}
