package org.graviton.repository;

import org.graviton.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("serverRepository")
public interface ServerRepository extends JpaRepository<Server, Byte> {
    List<Server> findAll();
}
