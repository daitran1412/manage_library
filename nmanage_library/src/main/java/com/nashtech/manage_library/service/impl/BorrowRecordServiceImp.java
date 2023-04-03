package com.nashtech.manage_library.service.impl;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.nashtech.manage_library.Entity.ListBook.Book;
import com.nashtech.manage_library.Entity.Publish.BorrowRecord;
import com.nashtech.manage_library.Entity.Reader.User;
import com.nashtech.manage_library.repository.Publish.BorrowRecordRepository;
import com.nashtech.manage_library.service.BorrowRecordService;


@Service
public class BorrowRecordServiceImp implements BorrowRecordService  {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private UserServiceImp userService;

    @Autowired
    private BookServiceImp bookService;

    @Override
    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }

    @Override
    public List<BorrowRecord> getBorrowRecordsByUserId(Long userId) {
        return borrowRecordRepository.findByUserId(userId);
    }

    @Override
    public List<BorrowRecord> getBorrowRecordsByBookId(Long bookId) {
        return borrowRecordRepository.findByBookId(bookId);
    }

    @Override
    public BorrowRecord getBorrowRecordById(Long borrowRecordId) {
        return borrowRecordRepository.findById(borrowRecordId).orElse(null);
    }

    @Override
    public BorrowRecord createBorrowRecord(BorrowRecord borrowRecord) {
        User user = borrowRecord.getUser();
        Book book = borrowRecord.getBook();
        if (user == null || book == null) {
            return null;
        }
        if (book.getQuantity() == 0) {
            return null;
        }
        if (userService.getUserById(user.getId()) == null || bookService.getBookById(book.getId()) == null) {
            return null;
        }
        return borrowRecordRepository.save(borrowRecord);
    }

    @Override
    public BorrowRecord deleteBorrowRecord(Long borrowRecordId) {
        BorrowRecord existingBorrowRecord = borrowRecordRepository.findById(borrowRecordId).orElse(null);
        borrowRecordRepository.deleteById(borrowRecordId);
        return existingBorrowRecord;
    }

    @Override
    public BorrowRecord updateBorrowRecord(BorrowRecord borrowRecord) {
        BorrowRecord existingBorrowRecord = borrowRecordRepository.findById(borrowRecord.getId()).orElse(null);
        existingBorrowRecord.setBook(borrowRecord.getBook());
        existingBorrowRecord.setUser(borrowRecord.getUser());
        existingBorrowRecord.setBorrowDate(borrowRecord.toString());
        existingBorrowRecord.setReturnDate(borrowRecord.toString());
        existingBorrowRecord.setStatus();
        return borrowRecordRepository.save(existingBorrowRecord);
    }

    @Override
    public BorrowRecord getBorrowRecordByBorrowCode(UUID borrowCode) {
        return borrowRecordRepository.findByBorrowCode(borrowCode).orElse(null);
    }
    
}
