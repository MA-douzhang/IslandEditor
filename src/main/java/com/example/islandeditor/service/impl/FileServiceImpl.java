package com.example.islandeditor.service.impl;

import com.example.islandeditor.entity.Files;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface FileServiceImpl {
  String upload(MultipartFile file) throws IOException;
  void download(String fileUUID, HttpServletResponse response) throws IOException;

  List<Files> findAll();
//  List<Files> findAllImgs();

  Integer delete(Integer id);

  Integer save(Files files);
}
