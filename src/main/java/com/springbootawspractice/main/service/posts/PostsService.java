package com.springbootawspractice.main.service.posts;

import com.springbootawspractice.main.domain.Posts;
import com.springbootawspractice.main.domain.PostsRepository;
import com.springbootawspractice.main.web.dto.PostsResponseDto;
import com.springbootawspractice.main.web.dto.PostsSaveRequestDto;
import com.springbootawspractice.main.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );

        return PostsResponseDto.fromEntity(entity);
    }
}