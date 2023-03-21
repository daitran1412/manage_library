package com.nashtech.manage_library.service;

import java.util.List;

import com.nashtech.manage_library.Entity.Publish.BorrowRecord;

public interface BorrowRecordService {

    List <BorrowRecord> getAllBorrowRecords ();

    List <BorrowRecord> getBorrowRecordsByUserId (Long userId);
    
    List <BorrowRecord> getBorrowRecordsByBookId (Long bookId);

    BorrowRecord getBorrowRecordById (Long borrowRecordId);

    BorrowRecord createBorrowRecord (BorrowRecord borrowRecord);    

    BorrowRecord deleteBorrowRecord (Long borrowRecordId);
    
}
