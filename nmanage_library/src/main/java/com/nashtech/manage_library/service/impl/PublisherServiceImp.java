package com.nashtech.manage_library.service.impl;

import com.nashtech.manage_library.service.PublisherService;
import com.nashtech.manage_library.repository.Publish.PublisherRepository;
import com.nashtech.manage_library.Entity.Publish.Publisher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImp implements PublisherService {
    
    @Autowired
    private PublisherRepository publisherRepository;
    
    @Override
    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }
    
    @Override
    public Publisher updatePublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }
    
    @Override
    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }
    
    @Override
    public Publisher getPublisherByName(String name) {
        return publisherRepository.findByName(name).orElse(null);
    }
    
    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }
    
    @Override
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

}
