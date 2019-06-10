package com.smile.life.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: Smile
 * @date: 2019/6/9
 * 预约表
 */
@Data
@Document(collection = "reservation")
@CompoundIndexes({
        @CompoundIndex(unique = true, name = "activity", def = "{username:1,acitvityId:1}")
})
public class Reservation {
    @Id
    private String id;
    private String username; //用户名
    private String acitvityId; //活动ID
    private String activityName; //活动名称

    public Reservation(String username, String acitvityId, String activityName) {
        this.username = username;
        this.acitvityId = acitvityId;
        this.activityName = activityName;
    }

    public Reservation() {
    }
}
