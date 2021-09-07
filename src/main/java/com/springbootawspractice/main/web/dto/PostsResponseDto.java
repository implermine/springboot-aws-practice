package com.springbootawspractice.main.web.dto;

import com.springbootawspractice.main.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String author;

    @Builder
    public PostsResponseDto(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //enh:
    //책에선 생성자패턴으로 fromEntity를 구현했지만, 정적 팩토리 메소드가 더 나은 선택이라 생각했습니다.
    public static PostsResponseDto fromEntity(Posts entity){
        return PostsResponseDto.builder()
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .build();
    }
}
