package com.nashtech.manage_library.service;

import java.util.List;

import com.nashtech.manage_library.Entity.Publish.Publisher;

public interface PublisherService { 

    Publisher createPublisher(Publisher publisher);

    Publisher updatePublisher(Publisher publisher);

    Publisher getPublisherById(Long id);

    Publisher getPublisherByName(String name);

    List<Publisher> getAllPublishers();

    void deletePublisher(Long id);

}
