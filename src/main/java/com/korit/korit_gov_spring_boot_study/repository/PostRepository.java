package com.korit.korit_gov_spring_boot_study.repository;

import com.korit.korit_gov_spring_boot_study.entity.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostRepository {
    private static PostRepository instance;
    private List<Post> postList;

    private PostRepository() {
        postList = new ArrayList<>();
    }

    public static PostRepository getInstance() {
        if (instance == null) {
            instance = new PostRepository();
        }
        return instance;
    }

    public void addPost(Post post) {
        post.setPostId(postList.size() + 1);
        postList.add(post);
    }

    public Optional<Post> findPostByTitle(String title) {
        return postList.stream()
                .filter(post -> post.getTitle().equals(title))
                .findFirst();
    }

    public List<Post> findPostByKeyword(String keyword) {
        return postList.stream()
                .filter(post -> post.getTitle().contains(keyword))
                .toList();
    }

    public Optional<Post> findPostByPostId(Integer postId) {
        return postList.stream()
                .filter(post -> !post.getTitle().isEmpty() && post.getPostId().equals(postId))
                .findFirst();
    }

    public List<Post> getPostAll() {
        return postList;
    }
}
