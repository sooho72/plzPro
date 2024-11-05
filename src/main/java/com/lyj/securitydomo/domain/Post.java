package com.lyj.securitydomo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.Collections;
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
    private Long postId; // 게시글 ID 자동 생성

    private String title; // 제목

    private String contentText; // 게시글 본문 내용 저장

    // 게시글 제목과 내용을 변경하는 메서드
    public void change(String title, String contentText) {
        this.title = title;
        this.contentText = contentText;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    @BatchSize(size = 20)
    private Set<pPhoto> imageSet = Collections.synchronizedSet(new HashSet<>());

    // 이미지 추가 메서드
    public void addImage(String uuid, String fileName) {
        pPhoto image = pPhoto.builder()
                .uuid(uuid)
                .fileName(fileName)
                .post(this)
                .pno(imageSet.size())
                .build();
        imageSet.add(image);
    }

    // 모든 이미지를 제거하는 메서드
    public void clearAllImages() {
        imageSet.forEach(pPhoto -> pPhoto.changePost(null));
        this.imageSet.clear();
    }
}
