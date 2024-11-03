package com.lyj.securitydomo.service;

import com.lyj.securitydomo.controller.PageRequestDTO;
import com.lyj.securitydomo.controller.PageResponseDTO;
import com.lyj.securitydomo.domain.Post;
import com.lyj.securitydomo.domain.User;
import com.lyj.securitydomo.dto.PostDTO;

import java.util.List;

public interface PostService {
    Long register(PostDTO postDTO);

    PostDTO readOne(Long postId);

    void modify(PostDTO postDTO);

    void remove(Long postId);

    PageResponseDTO<PostDTO> list(PageRequestDTO pageRequestDTO);


}
