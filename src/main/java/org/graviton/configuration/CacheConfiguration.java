package org.graviton.configuration;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {

    @CacheEvict(value = "news", allEntries = true)
    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void updateNews() {

    }


    @CacheEvict(value = "server", allEntries = true)
    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void updateServers() {

    }

    @CacheEvict(value = "players", allEntries = true)
    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void updatePlayers() {

    }

}
