package com.example.scheduler.controller;

import com.example.scheduler.DTO.PasswordRequestDto;
import com.example.scheduler.DTO.SchedulerRequestDto;
import com.example.scheduler.DTO.SchedulerResponseDto;
import com.example.scheduler.service.SchedulerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController //@Controller + @ResponseBody
@RequestMapping("/schedules") //Prefix
public class SchedulerController {

    //주입된 의존성을 변경할 수 없어 객체의 상태를 안전하게 유지
    private final SchedulerService schedulerService;

    //생성자 주입, 클래스가 필요로 하는 의존성을 생성자를 통해 전달

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    //스케줄러 저장
    @PostMapping
    @Operation(summary = "스케줄 생성", description = "스케줄 생성합니다.")
    public ResponseEntity<SchedulerResponseDto> createScheduler(@RequestBody @Valid SchedulerRequestDto dto) {
        if(dto.getUserId() == null){
            throw new IllegalArgumentException("User ID는 필수값입니다.");
        }

        // Service Loyer 호출
        return new ResponseEntity<>(schedulerService.saveScheduler(dto), HttpStatus.CREATED);
    }

    //스케줄러 전건 조회
    @GetMapping
    @Operation(summary = "스케줄 전건 조회", description = "모든 스케줄을 조회합니다.")
    public List<SchedulerResponseDto> findAllScheduler(){
        
        return schedulerService.findAllScheduler();
    }

    //스케줄러 조건부 조회
    @GetMapping("/search")
    @Operation(summary = "스케줄 조건부 조회", description = "이름, 개월 수, 유저 아이디를 기반으로 스케줄을 조회합니다.")
    public List<SchedulerResponseDto> searchSchedules(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) Integer months,
            @RequestParam(required = false) Long userId
    ){
        return schedulerService.searchSchedules(name, date, months, userId);
    }

    //스케줄러 단건 조회(ID)
    @GetMapping("/{id}")
    @Operation(summary = "스케줄 단건 조회", description = "스케줄 id를 기반으로 단건 조회를 합니다.")
    public ResponseEntity<SchedulerResponseDto> findSchedulerById(@PathVariable Long id){

        return new ResponseEntity<>(schedulerService.findSchedulerById(id), HttpStatus.OK);
    }


    //스케줄러 업데이트(ID기반)
    @PutMapping("/{id}")
    @Operation(summary = "스케줄 업데이트", description = "스케줄 id를 기반으로 업데이트합니다.")
    public ResponseEntity<SchedulerResponseDto> updateScheduler(
            @PathVariable Long id,
            @RequestBody @Valid SchedulerRequestDto dto
    ){
        return new ResponseEntity<>(schedulerService.updateScheduler(id, dto.getTitle(), dto.getContents(), dto.getName(),dto.getPassword()), HttpStatus.OK);
    }

    //스케줄러 삭제(ID기반)
    @DeleteMapping("/{id}")
    @Operation(summary = "스케줄 삭제", description = "스케줄 id를 기반으로 삭제합니다.")
    public ResponseEntity<Void> deleteScheduler(
            @PathVariable Long id,
            @RequestBody @Valid PasswordRequestDto dto
            ){
        schedulerService.deleteScheduler(id, dto.getPassword());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paging")
    @Operation(summary = "페이지네이션", description = "페이지네이션 부분입니다.")
    public List<SchedulerResponseDto> getPaginationSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return schedulerService.getPaginationSchedules(page, size);
    }
}
