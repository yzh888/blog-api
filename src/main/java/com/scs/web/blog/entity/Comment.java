package com.scs.web.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zh_yan
 * @ClassName Comment
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
private Long id;
private String nickname;
private Long userId;
private Long articleId;
private String content;
private LocalDateTime createTime;



}
