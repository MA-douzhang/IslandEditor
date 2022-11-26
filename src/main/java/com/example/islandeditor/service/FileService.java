package com.example.islandeditor.service;

import cn.hutool.core.io.FileUtil;
import com.example.islandeditor.entity.Files;
import com.example.islandeditor.mapper.MapperFile;
import com.example.islandeditor.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;


@Service
public class FileService implements FileServiceImpl {

  @Resource
  MapperFile mapperFile;
  @Value("${files.upload.path}")
  private String fileUploadPath;

  /**
   * 上传文件
   *
   * @param file
   * @return 返回文件访问地址
   */
  @Override
  public String upload(MultipartFile file) {
    if (file.isEmpty()) {
      return "上传失败，请选择文件";
    }

    String fileName = file.getOriginalFilename();
    String fileSrc = fileUploadPath + fileName;
    File dest = new File(fileSrc);
    try {
      //文件存入磁盘
      file.transferTo(dest);
      //img_src地址
      String downFileName = "http://localhost:9099/file/" + fileName;
      //存入数据库
      Files saveFile = new Files();
      saveFile.setFileSrc(downFileName);
      saveFile.setName(fileName);
      mapperFile.save(saveFile);
      return downFileName;
    } catch (IOException e) {
    }
    return "上传失败！";
  }

  /**
   * 图片下载地址
   *
   * @param fileUUID
   * @param response
   * @throws IOException
   */
  @Override
  public void download(String fileUUID, HttpServletResponse response) throws IOException {

    //根据文件的唯一标识码获取文件
    File uploadFile = new File(fileUploadPath + fileUUID);
//        输出流格式
    ServletOutputStream os = response.getOutputStream();
    response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
    //读取文件字节流
    os.write(FileUtil.readBytes(uploadFile));
    os.flush();
    os.close();
  }

  @Override
  public List<Files> findAll() {
    List<Files> findAll = mapperFile.findAll();
    return findAll;
  }

//  @Override
//  public List<Files> findAllImgs() {
//    List<Files> findAll = mapperFile.findAllImgs();
//    return findAll;
//  }

  @Override
  public Integer delete(Integer id) {
    return mapperFile.delete(id);
  }

  @Override
  public Integer save(Files files) {
    if (files.getId() == null) {
      return mapperFile.save(files);
    } else {
      return mapperFile.update(files);
    }
  }
}
