package com.nashtech.manage_library.dto.Reader;

public class BorrowRecordDto {
    
    private String id;
    private String user;
    private String book;
    private String borrowDate;
    private String returnDate;
    private String status;
    
    public BorrowRecordDto() {
    }

    public BorrowRecordDto(String id, String user, String book, String borrowDate, String returnDate, String status) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getBook() {
        return book;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
