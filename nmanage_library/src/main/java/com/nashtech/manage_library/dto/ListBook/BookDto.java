package com.nashtech.manage_library.dto.ListBook;

public class BookDto {

    private String id;
    private String name;
    private String author;
    private String publisher;
    private String category;
    private String status;
    private String quantity;
    private String description;
    private String image;

    public BookDto() {
    }

    public BookDto(String id, String name, String author, String publisher, String category, String status, String quantity,String description, String image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.status = status;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
