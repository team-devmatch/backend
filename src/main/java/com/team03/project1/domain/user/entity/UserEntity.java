package com.team03.project1.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String profile_image;
    @Column(unique = true, nullable = false)
    private String email; //이메일(id)
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String role;

    @PrePersist
    public void prePersist(){
        if(this.role == null)
            this.role ="USER";
    }

}
