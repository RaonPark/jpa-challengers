package com.raonpark.jpa.entity.board;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
    @Id @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    @Nationalized
    private String content;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;
}
