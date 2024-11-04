package com.lyj.securitydomo.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "imageSet")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId; // 게시글 ID자동 생성
    private String title; // 제목
    private String contentText; //게시글본문내용저장

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = true)

    @JoinColumn(name = "userId")
    private User user;


    public void change(String title, String contentText) {
        this.title = title;
        this.contentText = contentText;
    }
//    @Builder.Default
//    @BatchSize(size = 20)
//    private Set<pPhoto> imageSet=new HashSet<>();
//
//    public void addImage(String uuid, String fileName){
//        pPhoto image=pPhoto.builder()
//                .uuid(uuid)
//                .fileName(fileName)
//                .post(this)
//                .pno(imageSet.size())
//                .build();
//        imageSet.add(image);
//    }
//    public void clearImages(){
//        imageSet.forEach(pPhoto -> pPhoto.changePost(null));
//        this.imageSet.clear();
//    }


}