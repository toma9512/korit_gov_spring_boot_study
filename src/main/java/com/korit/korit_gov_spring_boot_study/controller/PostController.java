package com.korit.korit_gov_spring_boot_study.controller;

import com.korit.korit_gov_spring_boot_study.dto.request.AddPostReqDto;
import com.korit.korit_gov_spring_boot_study.dto.request.UpdatePostReqDto;
import com.korit.korit_gov_spring_boot_study.dto.response.AddPostRespDto;
import com.korit.korit_gov_spring_boot_study.dto.response.ApiRespDto;
import com.korit.korit_gov_spring_boot_study.dto.response.GetPostRespDto;
import com.korit.korit_gov_spring_boot_study.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private PostService postService;

    public PostController() {
        postService = PostService.getInstance();
    }

    @PostMapping("/post")
    public ResponseEntity<AddPostRespDto> addPost(@RequestBody AddPostReqDto addPostReqDto) {
        return ResponseEntity.ok(postService.addPost(addPostReqDto));
    }

    @GetMapping("/postKeyword")
    public ResponseEntity<GetPostRespDto<?>> getPostByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(postService.getPostByKeyword(keyword));
    }

    @GetMapping("/postId")
    public ResponseEntity<GetPostRespDto<?>> getPostByPostId(@RequestParam Integer postId) {
        return ResponseEntity.ok(postService.getPostByPostId(postId));
    }

    @GetMapping("/postList")
    public ResponseEntity<GetPostRespDto<?>> getPostAll() {
        return ResponseEntity.ok(postService.getPostAll());
    }

    @PostMapping("/postUpdate")
    public ResponseEntity<ApiRespDto> updatePost(@RequestBody UpdatePostReqDto updatePostReqDto) {
        return ResponseEntity.ok(postService.updatePost(updatePostReqDto));
    }

    @PostMapping("/postDelete")
    public ResponseEntity<ApiRespDto> deletePost(@RequestParam Integer postId) {
        return ResponseEntity.ok(postService.deletePost(postId));
    }
}
