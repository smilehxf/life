package com.smile.life.utils;

import lombok.Data;

/**
 * @author: Smile
 * @date: 2019/6/6
 */
@Data
public class Step{
    private String way; //操作
    private String stepImg; //图片

    public Step() {
    }

    public Step(String way, String stepImg) {
        this.way = way;
        this.stepImg = stepImg;
    }
}
