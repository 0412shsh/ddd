package com.pong.ddd.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity //데이터베이스 연동을 위한 모델 클래스
@Data //롬복
public class Board {

    @Id // PK 이다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AI 이다
    private Long bno; // 게시글 번호
    private String title;
    private String user_name;
//    private String user_id;
    private String write_date;
    private String content;
    private int score;
}
