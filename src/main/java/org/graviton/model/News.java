package org.graviton.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "website_news")
public class News {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private String date;

}
