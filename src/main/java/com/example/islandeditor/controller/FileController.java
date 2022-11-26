package com.example.islandeditor.controller;

import com.example.islandeditor.entity.Files;
import com.example.islandeditor.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
  @Resource
  FileService fileService;
  // 图片上传接口
  @PostMapping("/upload")
  public String upload(@RequestParam MultipartFile file) throws IOException{
    return fileService.upload(file);
  }
  //todo 没有拦截器拦截 不然前端页面图片不能显示，可以改成单独的接口
  //图片下载地址 和 图片访问地址
  @GetMapping("/{fileUUID}")
  public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException{
    fileService.download(fileUUID,response);
  }
  @GetMapping("/")
  public List<Files> getList(){
    return fileService.findAll();
  }
  @DeleteMapping({"/{id}"})
  public Integer delete(@PathVariable Integer id){
    return fileService.delete(id);
  }
  @PostMapping("/save")
  public Integer save(@RequestBody Files files){
    return fileService.save(files);
  }
}
