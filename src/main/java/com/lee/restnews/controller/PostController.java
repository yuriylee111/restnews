package com.lee.restnews.controller;


import com.lee.restnews.dto.PostDto;
import com.lee.restnews.dto.PostDtoV2;
import com.lee.restnews.dto.PostResponse;
import com.lee.restnews.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.lee.restnews.util.Constant.*;

@RestController
@RequestMapping
@Tag(name = "Post Resource")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Create Post", description = "Create Post is used to save Post into database")
    @ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
    @PostMapping("/api/v1/posts")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Posts", description = "Get All Posts is used to fetch all posts from the database")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @GetMapping("/api/v1/posts")
    public PostResponse getAllPosts(
            @RequestParam(value = PAGE_NO_TEMPLATE, defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = PAGE_SIZE_TEMPLATE, defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = SORT_BY_TEMPLATE, defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = SORT_DIR_TEMPLATE, defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @Operation(summary = "Get Post by Id", description = "Get Post by Id is used to get single Post from the database")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name = ID) Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @Operation(summary = "Get Post by Id", description = "Get Post by Id is used to get single Post from the database")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @GetMapping("/api/v2/posts/{id}")
    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable(name = ID) Long id) {
        PostDto postDto = postService.getPostById(id);
        PostDtoV2 postDtoV2 = new PostDtoV2();
        postDtoV2.setId(postDto.getId());
        postDtoV2.setTitle(postDto.getTitle());
        postDtoV2.setDescription(postDto.getDescription());
        postDtoV2.setContent(postDto.getContent());

        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("Spring Boot");
        tags.add("AWS");
        postDtoV2.setTags(tags);

        return ResponseEntity.ok(postDtoV2);
    }

    @Operation(summary = "Update Post by Id", description = "Update Post by Id is used to update particular Post in the database")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PutMapping("/api/v1/posts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = ID) Long id) {
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @Operation(summary = "Delete Post by Id", description = "Delete Post by Id is used to delete particular Post from the database")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @DeleteMapping("/api/v1/posts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<String> deletePost(@PathVariable(name = ID) Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully!", HttpStatus.OK);
    }

    @Operation(summary = "Get Posts by Category", description = "Get Posts by Category is used to get all Posts by Category from the database")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @GetMapping("/api/v1/posts/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Long categoryId) {
        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }
}
