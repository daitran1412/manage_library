package com.nashtech.manage_library.controller.Publish;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nashtech.manage_library.Entity.Error.Status;
import com.nashtech.manage_library.Entity.Publish.Publisher;
import com.nashtech.manage_library.dto.Publish.PublisherDto;
import com.nashtech.manage_library.service.PublisherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/publisher")
@CrossOrigin
public class PublisherController {
    
    @Autowired
    private PublisherService publisherService;
    private ModelMapper modelMapper;

    public PublisherController(PublisherService publisherService, ModelMapper modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllPublishers() {
        List<Publisher> publishers = publisherService.getAllPublishers();
        List<PublisherDto> publisherDtos = publishers.stream().map(publisher -> modelMapper.map(publisher, PublisherDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(publisherDtos);
    }
    

    @GetMapping("/get/id/{id}")
    public ResponseEntity<PublisherDto> getPublisherById(@PathVariable Long id) {
        Publisher publisher = publisherService.getPublisherById(id);
        PublisherDto publisherDto = modelMapper.map(publisher, PublisherDto.class);
        return ResponseEntity.ok().body(publisherDto);
    }

    // lỗi
    @GetMapping("/get/name/{name}")
    // public PublisherDto getPublisherByName(@PathVariable String name) {
    //     Publisher publisher = publisherService.getPublisherByName(name);
    //     return modelMapper.map(publisher, PublisherDto.class);
    // }
    public ResponseEntity<PublisherDto> getPublisherByName(@PathVariable String name) {
        Publisher publisher = publisherService.getPublisherByName(name);
        PublisherDto publisherDto = modelMapper.map(publisher, PublisherDto.class);
        return ResponseEntity.ok().body(publisherDto);
    }

    @PostMapping("/create")
    public ResponseEntity<PublisherDto> createPublisher(@RequestBody PublisherDto publisherDto) {
        Publisher publisher = modelMapper.map(publisherDto, Publisher.class);
        publisherService.createPublisher(publisher);
        PublisherDto publisherDto1 = modelMapper.map(publisher, PublisherDto.class);
        return ResponseEntity.ok().body(publisherDto1);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<PublisherDto> updatePublisher(@PathVariable Long id, @RequestBody PublisherDto publisherDto) {
        Publisher publisher = modelMapper.map(publisherDto, Publisher.class);
        publisherService.updatePublisher(publisher);
        PublisherDto publisherDto1 = modelMapper.map(publisher, PublisherDto.class);
        return ResponseEntity.ok().body(publisherDto1);
    }

    @PostMapping("/delete/id/{id}")
    public ResponseEntity<?> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        Status status = new Status();
        status.setMessage("Delete success");
        return ResponseEntity.ok().body(status);
    }

    // Lỗi
    @PostMapping("/delete/name/{name}")
    // public void deletePublisher(@PathVariable String name) {
    //     Publisher publisher = publisherService.getPublisherByName(name);
    //     publisherService.deletePublisher(publisher.getId());
    // }
    public ResponseEntity<String> deletePublisher(@PathVariable String name) {
        Publisher publisher = publisherService.getPublisherByName(name);
        publisherService.deletePublisher(publisher.getId());
        return ResponseEntity.ok().body("Delete success");
    }
}
