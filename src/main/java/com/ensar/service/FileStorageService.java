package com.ensar.service;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ensar.entity.FileDB;
import com.ensar.entity.ForecastDashBoard;
import com.ensar.repository.FileDBRepository;


@Service
public class FileStorageService {

  @Autowired
  private FileDBRepository fileDBRepository;

  @Transactional 
  public FileDB createOrUpdate( Optional<String> fileDBId, MultipartFile file) throws IOException {
    
	  FileDB fileDB;
      if (fileDBId.isPresent()) {
    	  fileDB = getById(fileDBId.get());
      } else {
    	  fileDB = new FileDB();
      }
      String fileName = StringUtils.cleanPath(file.getOriginalFilename());
      fileDB.setName(fileName);
      fileDB.setType(file.getContentType());
      fileDB.setData(file.getBytes());
      return fileDBRepository.save(fileDB);
      
  }

   
  public FileDB getById(String id) {
      Optional<FileDB> fileDB = fileDBRepository.findById(id);
      if (!fileDB.isPresent())
          throw new RuntimeException("fileDB with id " + id + " not found");
      return fileDB.get();
  }
  
  
  public Stream<FileDB> getAllFiles() {
    return fileDBRepository.findAll().stream();
  }
}