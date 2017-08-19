package org.graviton.service.impl;

import org.graviton.model.Server;
import org.graviton.repository.ServerRepository;
import org.graviton.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("serverService")
public class ServerServiceImpl implements ServerService {
    @Autowired
    private ServerRepository serverRepository;

    @Override
    @Cacheable("server")
    public List<Server> findAll() {
        return serverRepository.findAll().stream().sorted(Comparator.comparingInt(Server::getId)).collect(Collectors.toList());
    }
}
