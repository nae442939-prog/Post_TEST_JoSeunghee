package com.example.board.service;

import com.example.board.dto.Page;
import com.example.board.dto.Post;

import java.util.List;

/**
 * 게시글 Service 인터페이스
 * - 게시판 기능(등록, 목록, 조회, 수정, 삭제)을 정의
 */
public interface PostService {

    // 게시글 등록
    void registerPost(Post post);

    // 게시글 목록
    List<Post> getPostList();

    // [문항5 추가] 게시글 목록 - 페이징
    List<Post> getPostList(Page page);

    // [문항5 추가] 전체 게시글 수
    int getTotalCount();

    // 게시글 조회 (조회수 1 증가 후 반환)
    Post getPost(Long id);

    // 게시글 수정
    void modifyPost(Post post);

    // 게시글 삭제
    void removePost(Long id);
}
