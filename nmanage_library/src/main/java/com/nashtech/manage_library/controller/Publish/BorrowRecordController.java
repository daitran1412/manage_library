package com.nashtech.manage_library.controller.Publish;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.manage_library.Entity.Publish.BorrowRecord;
import com.nashtech.manage_library.dto.Publish.BorrowRecordDto;
import com.nashtech.manage_library.service.BorrowRecordService;

@RestController
@RequestMapping("/api/borrow-record")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get")
    public List<BorrowRecordDto> getAllBorrowRecord() {
        List<BorrowRecordDto> borrowRecordDtos = new ArrayList<>();
        List<BorrowRecord> borrowRecords = borrowRecordService.getAllBorrowRecords();
        for (BorrowRecord borrowRecord : borrowRecords) {
            borrowRecordDtos.add(modelMapper.map(borrowRecord, BorrowRecordDto.class));
        }
        return borrowRecordDtos;
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<BorrowRecordDto> getBorrowRecordById(@PathVariable("id") Long id) {
        BorrowRecord borrowRecord = borrowRecordService.getBorrowRecordById(id);
        return ResponseEntity.ok().body(modelMapper.map(borrowRecord, BorrowRecordDto.class));
    }

    @GetMapping("/get-by-user/{id}")
    public List<BorrowRecordDto> getBorrowRecordByUserId(@PathVariable("id") Long id) {
        List<BorrowRecordDto> borrowRecordDtos = new ArrayList<>();
        List<BorrowRecord> borrowRecords = borrowRecordService.getBorrowRecordsByUserId(id);
        for (BorrowRecord borrowRecord : borrowRecords) {
            borrowRecordDtos.add(modelMapper.map(borrowRecord, BorrowRecordDto.class));
        }
        return borrowRecordDtos;
    }

    @GetMapping("/get-by-code/{code}")
    public ResponseEntity<BorrowRecordDto> getBorrowRecordByBorrowCode(@PathVariable("code") UUID code) {
        BorrowRecord borrowRecord = borrowRecordService.getBorrowRecordByBorrowCode(code);
        return ResponseEntity.ok().body(modelMapper.map(borrowRecord, BorrowRecordDto.class));
    }

    @PostMapping("/create")
    public ResponseEntity<BorrowRecordDto> createBorrowRecord(@RequestBody BorrowRecordDto BorrowRecordDto) {
        BorrowRecord borrowRecord = modelMapper.map(BorrowRecordDto, BorrowRecord.class);
        borrowRecordService.createBorrowRecord(borrowRecord);
        return ResponseEntity.ok().body(modelMapper.map(borrowRecord, BorrowRecordDto.class));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<BorrowRecordDto> updateBorrowRecord(@PathVariable("id") Long id, @RequestBody BorrowRecordDto BorrowRecordDto) {
        BorrowRecord borrowRecord = modelMapper.map(BorrowRecordDto, BorrowRecord.class);
        borrowRecordService.updateBorrowRecord(borrowRecord);
        return ResponseEntity.ok().body(modelMapper.map(borrowRecord, BorrowRecordDto.class));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<BorrowRecordDto> deleteBorrowRecord(@PathVariable("id") Long id) {
        borrowRecordService.deleteBorrowRecord(id);
        return ResponseEntity.ok().build();
    }
    
}
