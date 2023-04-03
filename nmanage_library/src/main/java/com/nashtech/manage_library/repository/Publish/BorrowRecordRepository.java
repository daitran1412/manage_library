package com.nashtech.manage_library.repository.Publish;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nashtech.manage_library.Entity.Publish.BorrowRecord;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    Optional<BorrowRecord> findById(Long borrowRecordId);

    List<BorrowRecord> findByUserId(Long userId);
    
    List<BorrowRecord> findByBookId(Long bookId);

    void deleteById(Long borrowRecordId);

    Optional<BorrowRecord> findByBorrowCode(UUID borrowCode);

}
