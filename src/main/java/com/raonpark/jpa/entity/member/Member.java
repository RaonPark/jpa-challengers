package com.raonpark.jpa.entity.member;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @NonNull
    private String email;

    @NonNull
    private String address;

    private String username;

    @NonNull
    private String password;

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @NonNull
    private String phoneNumber;
    
    @NonNull
    private String IP_address;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;
}
