package com.nashtech.manage_library.controller.Publish;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.manage_library.Entity.Error.Status;
import com.nashtech.manage_library.Entity.Publish.BorrowRecord;
import com.nashtech.manage_library.Entity.Reader.User;
import com.nashtech.manage_library.dto.Publish.BorrowRecordDto;
import com.nashtech.manage_library.service.BorrowRecordService;
import com.nashtech.manage_library.service.UserService;

@RestController
@RequestMapping("/api/borrow-record")
@CrossOrigin
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get")
    public ResponseEntity<?> getAllBorrowRecord() {
        List<BorrowRecordDto> borrowRecordDtos = new ArrayList<>();
        List<BorrowRecord> borrowRecords = borrowRecordService.getAllBorrowRecords();
        for (BorrowRecord borrowRecord : borrowRecords) {
            borrowRecordDtos.add(modelMapper.map(borrowRecord, BorrowRecordDto.class));
        }
        return ResponseEntity.ok().body(borrowRecordDtos);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<?> getBorrowRecordById(@PathVariable("id") Long id) {
        // BorrowRecord borrowRecord = borrowRecordService.getBorrowRecordById(id);
        // return ResponseEntity.ok().body(modelMapper.map(borrowRecord, BorrowRecordDto.class));
        BorrowRecord borrowRecord = borrowRecordService.getBorrowRecordById(id);
        if (borrowRecord == null) {
            Status status = new Status();
            status.setMessage("Borrow Record not found");
            return ResponseEntity.ok(status);
        }
        BorrowRecordDto borrowRecordDto = modelMapper.map(borrowRecord, BorrowRecordDto.class);
        return ResponseEntity.ok(borrowRecordDto);
    }

    @GetMapping("/get-by-user/{id}")
    public ResponseEntity<?> getBorrowRecordByUserId(@PathVariable("id") Long id) {
        List<BorrowRecordDto> borrowRecordDtos = new ArrayList<>();
        User user = userService.getUserById(id);
        if (user == null) {
            Status status = new Status();
            status.setMessage("User not found");
            return ResponseEntity.ok(status);
        }
        List<BorrowRecord> borrowRecords = borrowRecordService.getBorrowRecordsByUserId(id);
        for (BorrowRecord borrowRecord : borrowRecords) {
            borrowRecordDtos.add(modelMapper.map(borrowRecord, BorrowRecordDto.class));
        }
        return ResponseEntity.ok(borrowRecordDtos);
        // List<BorrowRecord> borrowRecords = borrowRecordService.getBorrowRecordsByUserId(id);
        // for (BorrowRecord borrowRecord : borrowRecords) {
        //     borrowRecordDtos.add(modelMapper.map(borrowRecord, BorrowRecordDto.class));
        // }
        // return new ResponseEntity<List<BorrowRecordDto>>(borrowRecordDtos, HttpStatus.OK);
        
    }

    @GetMapping("/get-by-code/{code}")
    public ResponseEntity<?> getBorrowRecordByBorrowCode(@PathVariable("code") UUID code) {
        // BorrowRecord borrowRecord = borrowRecordService.getBorrowRecordByBorrowCode(code);
        // return ResponseEntity.ok().body(modelMapper.map(borrowRecord, BorrowRecordDto.class));
        BorrowRecord borrowRecord = borrowRecordService.getBorrowRecordByBorrowCode(code);
        if (borrowRecord == null) {
            Status status = new Status();
            status.setMessage("Borrow Record not found");
            return ResponseEntity.ok(status);
        }
        BorrowRecordDto borrowRecordDto = modelMapper.map(borrowRecord, BorrowRecordDto.class);
        return ResponseEntity.ok(borrowRecordDto);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBorrowRecord(@RequestBody BorrowRecordDto BorrowRecordDto) {
        BorrowRecord borrowRecord = modelMapper.map(BorrowRecordDto, BorrowRecord.class);
        borrowRecordService.createBorrowRecord(borrowRecord);
        return ResponseEntity.ok().body(modelMapper.map(borrowRecord, BorrowRecordDto.class));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateBorrowRecord(@PathVariable("id") Long id, @RequestBody BorrowRecordDto BorrowRecordDto) {
        // BorrowRecord borrowRecord = modelMapper.map(BorrowRecordDto, BorrowRecord.class);
        // borrowRecordService.updateBorrowRecord(borrowRecord);
        // return ResponseEntity.ok().body(modelMapper.map(borrowRecord, BorrowRecordDto.class));
        BorrowRecord borrowRecordCheck = borrowRecordService.getBorrowRecordById(id);
        if (borrowRecordCheck == null) {
            Status status = new Status();
            status.setMessage("Borrow Record not found");
            return ResponseEntity.ok(status);
        }
        BorrowRecord borrowRecord = modelMapper.map(BorrowRecordDto, BorrowRecord.class);
        borrowRecordService.updateBorrowRecord(borrowRecord);
        BorrowRecordDto borrowRecordDto = modelMapper.map(borrowRecord, BorrowRecordDto.class);
        return ResponseEntity.ok(borrowRecordDto);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteBorrowRecord(@PathVariable("id") Long id) {
        BorrowRecord borrowRecordCheck = borrowRecordService.getBorrowRecordById(id);
        if (borrowRecordCheck == null) {
            Status status = new Status();
            status.setMessage("Borrow Record not found");
            return ResponseEntity.ok(status);
        }
        borrowRecordService.deleteBorrowRecord(id);
        Status status = new Status();
        status.setMessage("Delete success");
        return ResponseEntity.ok(status);
    }
    
}
