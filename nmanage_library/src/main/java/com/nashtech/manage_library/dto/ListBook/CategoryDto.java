package com.nashtech.manage_library.dto.ListBook;

public class CategoryDto {

    private String id;
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
