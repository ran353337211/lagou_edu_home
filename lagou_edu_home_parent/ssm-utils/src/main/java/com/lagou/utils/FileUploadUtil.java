package com.lagou.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xumiao
 * @creationTime 2023/3/7
 * @description 文件上传工具类
 */
public class FileUploadUtil {

    public static Map<String,String> fileUpload(MultipartFile file, HttpServletRequest request) {

        try {
            // 1.判断文件是否为空
            if (file.isEmpty()) {
                throw new RuntimeException();
            }
            // 2.获取项目部署路径
            // D:\Java\apache-tomcat-8.5.85\webapps\ssm_web\
            String realPath = request.getServletContext().getRealPath("/");
            // D:\apache-tomcat-8.5.85\webapps\
            String webappsPath = realPath.substring(0, realPath.indexOf("ssm-web"));
            // 3.获取原文件名
            String originalFilename = file.getOriginalFilename();
            // 4.新文件名
            assert originalFilename != null;
            String filename = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
            // 5.上传文件
            String uploadPath = webappsPath + "upload\\";
            File filePath = new File(uploadPath, filename);
            // 若目录不存在就创建目录
            if (!filePath.getParentFile().exists()) {
                boolean b = filePath.getParentFile().mkdirs();
                System.out.println("创建目录：" + filePath + b);
            }
            file.transferTo(filePath);
            // 6.将文件名和文件路径返回
            Map<String, String> map = new HashMap<>();
            map.put("fileName",filename);
            map.put("filePath","http://localhost:8080/upload/" + filename);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
