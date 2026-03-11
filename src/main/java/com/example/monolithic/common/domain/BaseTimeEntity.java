package com.example.monolithic.common.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public class BaseTimeEntity {

    @CreationTimestamp
    private LocalDateTime createAt ;

    @CreationTimestamp
    private LocalDateTime updateAt ;

    @CreationTimestamp
    private LocalDateTime deleteAt ;
    
}

/*
일반 entity가 아니라 (테이블 역할이 아님.) entity들의 부모역할을 함.
*/