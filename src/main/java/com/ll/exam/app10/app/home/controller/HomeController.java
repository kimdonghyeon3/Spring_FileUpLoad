package com.ll.exam.app10.app.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @RequestMapping("/")
    public String main() {
        return "home/main";
    }

    @GetMapping("/test/upload")
    public String upload() {
        return "home/test/upload";
    }

    //파일 업로드 폼으로 업로드한 파일이 c:/temp/app10/1.png로 저장되도록 하기
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("img1") MultipartFile file){

        String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
        long size = file.getSize(); //파일 사이즈

        System.out.println("파일명 : "  + fileRealName);
        System.out.println("용량크기(byte) : " + size);
        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
        String uploadFolder = "C:\\Temp\\app10";

        String uniqueName = "1";
        System.out.println("생성된 고유문자열" + uniqueName);
        System.out.println("확장자명" + fileExtension);
        File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
        try {
            file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "hello";
    }
}
