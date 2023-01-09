package com.pong.ddd.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity //데이터베이스 연동을 위한 모델 클래스
@Data //롬복
public class Role {
    @Id // PK 이다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AI 이다
    private Long id; // role 고유 번호

    private String name;

    @ManyToMany(mappedBy = "roles") // user 클래스에 있는 컬럼 이름이 된다.
    private List<User> users;
}
