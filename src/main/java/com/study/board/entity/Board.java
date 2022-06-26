package com.study.board.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Board {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY: mysql, mariadb에서 사용
    private Integer id; //mysql 테이블 안에 보드 중 id
    
    private String title;
    
    private String content;

    private String filename;

    private String filepath;
}
