package com.example.scheduler.controller;

import com.example.scheduler.DTO.SchedulerRequestDto;
import com.example.scheduler.DTO.SchedulerResponseDto;
import com.example.scheduler.service.SchedulerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //@Controller + @ResponseBody
@RequestMapping("/scheduler") //Prefix
public class SchedulerController {

    //주입된 의존성을 변경할 수 없어 객체의 상태를 안전하게 유지
    private final SchedulerService schedulerService;

    //생성자 주입, 클래스가 필요로 하는 의존성을 생성자를 통해 전달
    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    //스케줄러 저장
    @PostMapping
    public ResponseEntity<SchedulerResponseDto> createScheduler(@RequestBody SchedulerRequestDto dto) {

        // Service Loyer 호출
        return new ResponseEntity<>(schedulerService.saveScheduler(dto), HttpStatus.CREATED);
    }

    //스케줄러 전건 조회
    @GetMapping
    public List<SchedulerResponseDto> findAllScheduler(){
        
        return schedulerService.findAllScheduler();
    }

    //스케줄러 단건 조회(ID)
    @GetMapping("/{id}")
    public ResponseEntity<SchedulerResponseDto> findSchedulerById(@PathVariable Long id){

        return new ResponseEntity<>(schedulerService.findSchedulerById(id), HttpStatus.OK);
    }

    //스케줄러 업데이트(ID기반)
    @PutMapping("/{id}")
    public ResponseEntity<SchedulerResponseDto> updateScheduler(
            @PathVariable Long id,
            @RequestBody SchedulerResponseDto dto
    ){

        return new ResponseEntity<>(schedulerService.updateScheduler(id, dto.getTitle(), dto.getContents(), dto.getPassword(), dto.getTime()), HttpStatus.OK);
    }

    //스케줄러 삭제(ID기반)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduler(@PathVariable Long id, @RequestBody SchedulerRequestDto dto){
        schedulerService.deleteScheduler(id, dto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
