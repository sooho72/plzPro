package com.lyj.securitydomo.dto.upload1;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UploadResultDTO {
    private String uuid;
    private String fileName;
    private boolean image;
    private int pno; // 연관된 게시글 ID


    public String getLink(){
        if(image){
            return "s_"+uuid+"_"+fileName;
        }else {
            return uuid+"_"+fileName;
        }
    }
}



