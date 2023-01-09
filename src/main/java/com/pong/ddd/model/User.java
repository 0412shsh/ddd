package com.pong.ddd.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //데이터베이스 연동을 위한 모델 클래스
@Data //롬복
public class User {
    @Id // PK 이다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AI 이다
    private Long id; // 유저 고유 번호

    private String username;
    private String password;
    private boolean enabled;
    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_role", // 조인테이블
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))


    // @ManyToMany List를 사용한 Role 테이블과 매핑
    //memberRepository를 통해 조회하면 user에 해당하는 권한이 알아서 조회되어서 roles에 담기게 됨
    private List<Role> roles = new ArrayList<>();



}
