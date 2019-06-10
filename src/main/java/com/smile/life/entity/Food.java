package com.smile.life.entity;

import com.smile.life.utils.Step;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

/**
 * @author: Smile
 * @date: 2019/6/6
 */
@Data
@Document(collection = "food")
public class Food {
    @Id
    private String foodId;
    private String username;
    private String foodName;  //食物名称
    private LocalDate uploadDate;//上传时间
    private String mainImg;  //主图
    private String material;   //材料
    private Integer stepNum; //步骤数
    private int goodNum; //点赞人数
    private List<Step> steps;

}
