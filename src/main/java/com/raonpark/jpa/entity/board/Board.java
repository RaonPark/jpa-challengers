package com.raonpark.jpa.entity.board;

import java.util.Date;

import org.hibernate.annotations.Nationalized;

import com.raonpark.jpa.entity.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Board {
    @Id @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;

    @NonNull
    private String title;

    @Nationalized
    private String content;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;

    @Temporal(TemporalType.TIME)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
