package com.pong.ddd.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity //데이터베이스 연동을 위한 모델 클래스
@Data //롬복
public class Board {

    @Id // PK 이다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AI 이다
    private Long bno; // 게시글 번호
    @NotNull
    @Size(min=2,max=30, message = "제목은 2자이상 30자 이하입니다.")
    private String title;
    private String user_name;
//    private String user_id;
    private String write_date;
    private String content;
    private int score;
}
