package com.nashtech.manage_library.Entity.Publish;

import jakarta.persistence.*;

import java.util.UUID;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.nashtech.manage_library.Entity.ListBook.Book;
import com.nashtech.manage_library.Entity.Reader.User;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "borrow_record")
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "status", columnDefinition = "varchar(255) default 'borrowed'")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "borrow_code", nullable = false)
    private UUID borrowCode;

    public void setBorrowDate(String borrowDate) {
        this.borrowDate =LocalDate.parse(borrowDate);
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = LocalDate.parse(returnDate);
    }

    public void setStatus() {
        if (LocalDate.now().isAfter(returnDate)) {
            this.status = "overdue";
        } else {
            this.status = "borrowed";
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setBorrowCode() {
        this.borrowCode = UUID.randomUUID();
    }
    
}

