package com.korit.korit_gov_spring_boot_study.service;

import com.korit.korit_gov_spring_boot_study.dto.request.AddPostReqDto;
import com.korit.korit_gov_spring_boot_study.dto.request.UpdatePostReqDto;
import com.korit.korit_gov_spring_boot_study.dto.response.AddPostRespDto;
import com.korit.korit_gov_spring_boot_study.dto.response.ApiRespDto;
import com.korit.korit_gov_spring_boot_study.dto.response.GetPostRespDto;
import com.korit.korit_gov_spring_boot_study.entity.Post;
import com.korit.korit_gov_spring_boot_study.repository.PostRepository;

import java.util.List;
import java.util.Optional;

public class PostService {
    private static PostService instance;
    private PostRepository postRepository;

    private PostService() {
        postRepository = PostRepository.getInstance();
    }

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService();
        }
        return instance;
    }

    public AddPostRespDto addPost(AddPostReqDto addPostReqDto) {
        if (addPostReqDto.getTitle() == null || addPostReqDto.getTitle().isBlank() ||
            addPostReqDto.getContent() == null || addPostReqDto.getContent().isBlank()) {
            return new AddPostRespDto("failed", "title과 content를 입력해주세요.");
        }

        if (postRepository.findPostByTitle(addPostReqDto.getTitle()).isPresent()) {
            return new AddPostRespDto("failed", "중복된 title입니다.");
        }

        postRepository.addPost(addPostReqDto.toEntity());
        return new AddPostRespDto("success", "게시글 등록 성공");
    }

    public GetPostRespDto<List<Post>> getPostByKeyword(String keyword) {
        List<Post> postList = postRepository.findPostByKeyword(keyword);

        if (postList.isEmpty()) {
            return new GetPostRespDto<>("failed", "일치하는 keyword 없음", null);
        }

        return new GetPostRespDto<>("success", "keyword 게시글 조회", postList);
    }

    public GetPostRespDto<Post> getPostByPostId(Integer postId) {
        Optional<Post> foundPost = postRepository.findPostByPostId(postId);

        if (foundPost.isEmpty()) {
            return new GetPostRespDto<>("failed", "일치하는 id가 없습니다.", null);
        }

        return new GetPostRespDto<>("success", "단건 조회", foundPost.get());
    }

    public GetPostRespDto<List<Post>> getPostAll() {
        List<Post> postList = postRepository.getPostAll();

        if (postList.isEmpty()) {
            return new GetPostRespDto<>("failed", "등록된 게시글이 없습니다.", null);
        }

        return new GetPostRespDto<>("success", "전체 조회", postList);
    }

    public ApiRespDto updatePost(UpdatePostReqDto correctionPostReqDto) {
        Optional<Post> foundPost = postRepository.findPostByPostId(correctionPostReqDto.getPostId());

        if (foundPost.isEmpty()) {
            return new ApiRespDto("failed", "일치하는 postId 없음");
        }

        foundPost.get().setTitle(correctionPostReqDto.getTitle());
        foundPost.get().setContent(correctionPostReqDto.getContent());

        return new ApiRespDto("success", "수정 완료");
    }

    public ApiRespDto deletePost(Integer postId) {
        Optional<Post> foundPost = postRepository.findPostByPostId(postId);

        if (foundPost.isEmpty()) {
            return new ApiRespDto("failed", "일차하는 postId 없음");
        }

        foundPost.get().setTitle("");
        foundPost.get().setContent("");

        return new ApiRespDto("success", "삭제 완료");
    }
}
