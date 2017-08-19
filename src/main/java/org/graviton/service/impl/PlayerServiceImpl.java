package org.graviton.service.impl;

import org.graviton.model.Player;
import org.graviton.repository.PlayerRepository;
import org.graviton.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("playerService")
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    @Cacheable("players")
    public List<Player> findAll() {
        return playerRepository.findAll();
    }
}
