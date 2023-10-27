package com.codewithdurgesh.blog.controllers;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.CommentDto;
import com.codewithdurgesh.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {
        CommentDto dto = this.commentService.createComment(commentDto, postId);
        return new ResponseEntity<CommentDto>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted Successfully", true, HttpStatus.OK), HttpStatus.OK);
    }
}
