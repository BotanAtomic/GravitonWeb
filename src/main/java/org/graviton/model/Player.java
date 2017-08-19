package org.graviton.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "players")
public class Player {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "owner")
    private int owner;

    @Column(name = "honor")
    private int honor;

    @Column(name = "breed")
    private byte breed;

    @Column(name = "experience")
    private long experience;

    @Column(name = "alignment")
    private byte alignment;

    @Column(name = "level")
    private short level;

    @Column(name = "sex")
    private byte sex;

    @Column(name = "server")
    private byte server;

    public String getLook() {
        return String.valueOf(breed * 10 + sex);
    }
}
