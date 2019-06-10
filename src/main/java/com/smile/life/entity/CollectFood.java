package com.smile.life.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: Smile
 * @date: 2019/6/10
 * 食物收藏表
 */
@Document(collection = "collectFood")
//复合索引
@CompoundIndexes({
        @CompoundIndex(name = "food", unique = true, def = "{username:1,foodId:1}")
})
@Data
public class CollectFood {
    @Id
    private String id;
    private String username; //用户名
    private String foodId;  //食物ID
    private String foodName; //食物名称

    public CollectFood(String username, String foodId, String foodName) {
        this.username = username;
        this.foodId = foodId;
        this.foodName = foodName;
    }

    public CollectFood() {
    }
}
