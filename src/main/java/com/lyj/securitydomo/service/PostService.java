package com.lyj.securitydomo.service;

import com.lyj.securitydomo.domain.Post;
import com.lyj.securitydomo.domain.QpPhoto;
import com.lyj.securitydomo.dto.PageRequestDTO;
import com.lyj.securitydomo.dto.PageResponseDTO;
import com.lyj.securitydomo.dto.PostDTO;

import java.util.List;
import java.util.stream.Collectors;

public interface PostService {
    Long register(PostDTO postDTO);

    PostDTO readOne(Long postId);

    void modify(PostDTO postDTO);

    void remove(Long postId);

    PageResponseDTO<PostDTO> list(PageRequestDTO pageRequestDTO);


//    default PostDTO entityToDTO(Post post) {
//        PostDTO boardDTO = PostDTO.builder()
//                .postId(post.getPostId())
//                .title(post.getTitle())
//                .contentText(post.getContentText())
//                .createdAt(post.getCreatedAt())
//                .upDatedAt(post.getUpDatedAt())
//                .build();
//
//        List<String> fileNames =
//                post.getImageSet().stream().sorted().map(pPhoto ->
//                                pPhoto.getUuid()+"_"+pPhoto.getFileName())
//                        .collect(Collectors.toList());
//        boardDTO.setFileNames(fileNames);
//        return boardDTO;
//    }
//

}
