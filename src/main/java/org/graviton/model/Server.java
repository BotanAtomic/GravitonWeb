package org.graviton.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "servers")
public class Server {
    @Id
    @Column(name = "id")
    private byte id;

    @Column(name = "key")
    private String key;

    @Column(name = "database")
    private String database;

}
