package com.scs.web.blog.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zh_yan
 * @ClassName CommentDto
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
@Data
public class CommentDto implements Serializable {

    /*private Long userId;
    private Long articleId;*/
    private String nickname;
    private String content;
    private LocalDateTime createTime;
    private String code;
    private int status;
}
