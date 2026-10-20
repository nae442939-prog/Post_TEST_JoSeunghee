package com.example.board.controller;

import com.example.board.dto.Page;
import com.example.board.dto.Post;
import com.example.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 게시글 Controller
 * - 사용자의 요청(URL)을 받아 Service 를 호출하고, 결과를 화면(View)으로 전달
 *
 *  [요청 URL 정리]
 *  GET  /Post/list?page=1    : 게시글 목록 (페이징) -> Post/list.html
 *  GET  /Post/insert         : 게시글 등록 화면     -> Post/insert.html
 *  POST /Post/insert         : 게시글 등록 처리
 *  GET  /Post/read/{id}      : 게시글 조회          -> Post/read.html
 *  GET  /Post/update/{id}    : 게시글 수정 화면     -> Post/update.html
 *  POST /Post/update         : 게시글 수정 처리
 *  POST /Post/delete         : 게시글 삭제 처리
 */
@Controller
@RequestMapping("/Post")
public class PostController {

    private static final int PAGE_SIZE = 10;   // [문항5 추가] 한 페이지에 보여줄 게시글 수

    private final PostService postService;

    // 생성자 주입 : 스프링이 PostService 구현 객체(PostServiceImpl)를 넣어 줌
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 목록  [문항5 수정] 페이징 처리
    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNum, Model model) {
        int total = postService.getTotalCount();             // 전체 글 수
        Page page = new Page(pageNum, PAGE_SIZE, total);     // 페이징 정보 계산
        List<Post> posts = postService.getPostList(page);    // 현재 페이지의 글만 조회

        model.addAttribute("posts", posts);
        model.addAttribute("page", page);
        return "Post/list";
    }

    // 게시글 등록 화면
    @GetMapping("/insert")
    public String insertForm() {
        return "Post/insert";
    }

    // 게시글 등록 처리
    @PostMapping("/insert")
    public String insert(@ModelAttribute Post post) {
        postService.registerPost(post);
        return "redirect:/Post/list";            // 등록 후 목록으로 이동
    }

    // 게시글 조회
    @GetMapping("/read/{id}")
    public String read(@PathVariable("id") Long id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "Post/read";
    }

    // 게시글 수정 화면
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "Post/update";
    }

    // 게시글 수정 처리
    @PostMapping("/update")
    public String update(@ModelAttribute Post post) {
        postService.modifyPost(post);
        return "redirect:/Post/read/" + post.getId();   // 수정 후 조회 화면으로 이동
    }

    // 게시글 삭제 처리
    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        postService.removePost(id);
        return "redirect:/Post/list";            // 삭제 후 목록으로 이동
    }
}
