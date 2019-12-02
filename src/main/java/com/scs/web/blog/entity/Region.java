package com.scs.web.blog.entity;

import lombok.Data;

/**
 * @ClassName Region
 * @Description  全国省市区地址Region实体类
 * @Author mq_xu
 * @Date 2019/11/23
 **/
@Data
public class Region {
    private  Integer id;
    private String name;
    private Integer parentId;
    private Integer level;
    private String cityCode;
    private String postCode;
    private String mergeName;
}
