package com.example.board.mapper;

import com.example.board.dto.Page;
import com.example.board.dto.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 게시글 Mapper 인터페이스
 * - 메서드 이름 = PostMapper.xml 의 SQL id
 * - 실제 SQL은 resources/mapper/PostMapper.xml 에 있음
 */
@Mapper
public interface PostMapper {

    // 게시글 등록
    int insertPost(Post post);

    // 게시글 목록
    List<Post> selectPostList();

    // [문항5 추가] 게시글 목록 - 페이징
    List<Post> selectPostListPaging(Page page);

    // [문항5 추가] 전체 게시글 수
    int countPost();

    // 게시글 상세 조회
    Post selectPostById(Long id);

    // 조회수 증가
    int updateViewCount(Long id);

    // 게시글 수정
    int updatePost(Post post);

    // 게시글 삭제
    int deletePost(Long id);
}
