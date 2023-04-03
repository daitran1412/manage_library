package com.nashtech.manage_library.controller.Publish;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class PublisherController {
    
    @Autowired
    private PublisherService publisherService;
    private ModelMapper modelMapper;

    public PublisherController(PublisherService publisherService, ModelMapper modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/get")
    public List<PublisherDto> getAllPublisher() {
        List<PublisherDto> publisherDtos = new ArrayList<>();
        List<Publisher> publishers = publisherService.getAllPublishers();
        for (Publisher publisher : publishers) {
            publisherDtos.add(modelMapper.map(publisher, PublisherDto.class));
        }
        return publisherDtos;
    }
    

    @GetMapping("/get/id/{id}")
    public PublisherDto getPublisherById(@PathVariable Long id) {
        Publisher publisher = publisherService.getPublisherById(id);
        return modelMapper.map(publisher, PublisherDto.class);
    }


    // lỗi
    @GetMapping("/get/name/{name}")
    public PublisherDto getPublisherByName(@PathVariable String name) {
        Publisher publisher = publisherService.getPublisherByName(name);
        return modelMapper.map(publisher, PublisherDto.class);
    }

    @PostMapping("/create")
    public PublisherDto createPublisher(@RequestBody PublisherDto publisherDto) {
        Publisher publisher = modelMapper.map(publisherDto, Publisher.class);
        publisherService.createPublisher(publisher);
        return modelMapper.map(publisher, PublisherDto.class);
    }

    @PostMapping("/update/{id}")
    public PublisherDto updatePublisher(@PathVariable Long id, @RequestBody PublisherDto publisherDto) {
        Publisher publisher = modelMapper.map(publisherDto, Publisher.class);
        publisherService.updatePublisher(publisher);
        return modelMapper.map(publisher, PublisherDto.class);
    }

    @PostMapping("/delete/id/{id}")
    public void deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
    }

    // Lỗi
    @PostMapping("/delete/name/{name}")
    public void deletePublisher(@PathVariable String name) {
        Publisher publisher = publisherService.getPublisherByName(name);
        publisherService.deletePublisher(publisher.getId());
    }

}
