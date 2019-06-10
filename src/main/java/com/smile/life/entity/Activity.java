package com.smile.life.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author: Smile
 * @date: 2019/6/9
 */
@Document(collection = "activity")
@Data
public class Activity {
    @Id
    private String id;
    @Indexed
    private String name;  //名称
    private String username; //上传者
    @Indexed
    private String city;  //城市
    @Indexed
    private String dateTime;  //起始时间
    private String address; //地址
    private String content; //活动内容
    @Transient
    private boolean isReservation = false; //是否被收藏
    @Transient
    private String reserveId; //预约ID
    private List<String> imgUrl; //图片
}
