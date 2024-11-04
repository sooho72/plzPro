package com.lyj.securitydomo.controller;

import com.lyj.securitydomo.dto.PageRequestDTO;
import com.lyj.securitydomo.dto.PageResponseDTO;
import com.lyj.securitydomo.dto.PostDTO;
import com.lyj.securitydomo.service.PostService;
import groovy.util.logging.Log4j2;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Controller
@Log4j2
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/list1")
    public void list1(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<PostDTO> responseDTO = postService.list(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);

    }
    @GetMapping("/register1") //등록
    public void register1GET(){

    }
    @PostMapping("/register1") //등록
    public String register1POST(@Valid PostDTO postDTO, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        log.info("post Post register");

        if (bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/post/register1";
        }
        log.info(postDTO);

        Long postId = postService.register(postDTO);

        redirectAttributes.addFlashAttribute("result", postId);
        return "redirect:/post/list1";
    }
     @GetMapping("/read1") //조회처리
        public void read(Long postId, PageRequestDTO pageRequestDTO, Model model) {

            PostDTO postDTO = postService.readOne(postId);

            log.info(postDTO);

            model.addAttribute("dto", postDTO);
     }

     @GetMapping({"/read1","/modify1"}) //게시물의 수정/삭제 처리
    public void read1(Long postId, PageRequestDTO pageRequestDTO, Model model) {

        PostDTO postDTO = postService.readOne(postId);

        log.info(postDTO);

        model.addAttribute("dto", postDTO);
     }

     @PostMapping("/modify1") //수정처리
    public String modify1 (PageRequestDTO pageRequestDTO,
                           @Valid PostDTO postDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        log.info("post modify1 post....." + postDTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors.......");

            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            redirectAttributes.addFlashAttribute("postId", postDTO.getPostId());

            return "redirect:/post/modify1"+link;
        }
        postService.modify(postDTO);
        redirectAttributes.addFlashAttribute("result","modified");
        redirectAttributes.addFlashAttribute("postId", postDTO.getPostId());
        return "redirect:/post/read1";
     }

     @PostMapping("/remove1") // 삭제처리
    public String remove1(Long postId, RedirectAttributes redirectAttributes) {
        log.info("post remove1 post....." + postId);

        postService.remove(postId);
        redirectAttributes.addFlashAttribute("result","removed");
        return "redirect:/post/list1";
     }

}
