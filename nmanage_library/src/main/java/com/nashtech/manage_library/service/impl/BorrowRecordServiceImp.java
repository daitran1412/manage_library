package com.nashtech.manage_library.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nashtech.manage_library.Entity.Publish.BorrowRecord;
import com.nashtech.manage_library.repository.Publish.BorrowRecordRepository;
import com.nashtech.manage_library.service.BorrowRecordService;

@Service
public class BorrowRecordServiceImp implements BorrowRecordService  {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

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
        return borrowRecordRepository.save(borrowRecord);
    }

    @Override
    public BorrowRecord deleteBorrowRecord(Long borrowRecordId) {
        BorrowRecord existingBorrowRecord = borrowRecordRepository.findById(borrowRecordId).orElse(null);
        borrowRecordRepository.deleteById(borrowRecordId);
        return existingBorrowRecord;
    }
    
}
