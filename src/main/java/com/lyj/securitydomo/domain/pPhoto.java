package com.lyj.securitydomo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "post")
public class pPhoto extends BaseEntity implements Comparable<pPhoto> {

    @Id
    private String uuid;
    private String fileName;
    private int pno;

    // 연관관계 설정 - 필요 시 연관된 Post 엔티티와 매핑
    @ManyToOne
    private Post post;

    @Override
    public int compareTo(pPhoto other) {
        return this.pno - other.pno;
    }
    public void changePost(Post post) {
        this.post = post;
    }
}
