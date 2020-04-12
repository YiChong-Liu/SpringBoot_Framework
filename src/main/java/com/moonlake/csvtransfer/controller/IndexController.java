package com.moonlake.csvtransfer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Controller
public class IndexController {
    private static final Logger logger=LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file")MultipartFile file){
        if(file.isEmpty()){
            return "上传失败";
        }
        String fileName=file.getOriginalFilename();
        String filePath="F:\\Idea-workspace\\csvtransfer\\src\\main\\resources\\csv\\base.csv";
        File dest=new File(filePath+fileName);
        try{
            file.transferTo(dest);
            logger.info("上传成功！");
            return "上传成功！";
        }catch (Exception e){
            logger.info("上传失败！");

            e.printStackTrace();
        }
        return "上传失败！";
    }
}
