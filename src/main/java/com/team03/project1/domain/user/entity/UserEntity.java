package com.team03.project1.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "profile_image")
    private String profileImage;
    @Column(unique = true, nullable = false)
    private String email; //이메일(id)
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String role;
    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;
    //private boolean deleted = false;

    @PrePersist
    public void prePersist(){
        if(this.role == null)
            this.role ="USER";
    }

}
