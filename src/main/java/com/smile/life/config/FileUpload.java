package com.smile.life.config;


import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传
 *
 * @author: Smile
 * @date: 2019/4/8
 */

public class FileUpload {
    public static List<String> upload(MultipartFile[] file, GridFsTemplate gridFsTemplate) {
        try {
            if (file == null) return null;
            if (file.length == 0) return null;
            String fileName = null;
            List<String> imgUrl = new ArrayList<>();
            for (int i = 0; i < file.length; i++) {
                fileName = file[i].getOriginalFilename();
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                //后缀名
                fileName = UUID.randomUUID().toString() + suffixName;
                gridFsTemplate.store(file[i].getInputStream(), fileName);
                imgUrl.add("http://image.smile233.top/life/" + fileName);
            }
            return imgUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
