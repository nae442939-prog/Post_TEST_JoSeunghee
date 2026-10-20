package com.example.board.service;

import com.example.board.dto.Page;
import com.example.board.dto.Post;
import com.example.board.mapper.PostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 게시글 Service 구현 클래스
 * - PostService 인터페이스의 기능을 PostMapper 를 이용해 실제로 처리
 */
@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    // 생성자 주입 : 스프링이 PostMapper 객체를 넣어 줌
    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    // 게시글 등록
    @Override
    public void registerPost(Post post) {
        postMapper.insertPost(post);
    }

    // 게시글 목록
    @Override
    public List<Post> getPostList() {
        return postMapper.selectPostList();
    }

    // [문항5 추가] 게시글 목록 - 페이징
    @Override
    public List<Post> getPostList(Page page) {
        return postMapper.selectPostListPaging(page);
    }

    // [문항5 추가] 전체 게시글 수
    @Override
    public int getTotalCount() {
        return postMapper.countPost();
    }

    // 게시글 조회 : 조회수 증가 + 상세 조회를 하나의 작업으로 처리
    @Override
    @Transactional
    public Post getPost(Long id) {
        postMapper.updateViewCount(id);
        return postMapper.selectPostById(id);
    }

    // 게시글 수정
    @Override
    public void modifyPost(Post post) {
        postMapper.updatePost(post);
    }

    // 게시글 삭제
    @Override
    public void removePost(Long id) {
        postMapper.deletePost(id);
    }
}
