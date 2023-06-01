package com.Abdessalam.friendMA.controller;

import com.Abdessalam.friendMA.dto.model.DemandDto;
import com.Abdessalam.friendMA.entity.Demand;
import com.Abdessalam.friendMA.service.DemandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demand")
@RequiredArgsConstructor
public class DemandController {

    private final DemandService demandService;

    @GetMapping("/all")
    public Page<DemandDto> getAllDemands(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return demandService.getAllDemands(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Demand createDemand(@RequestBody Demand demandDto) {
        return demandService.createDemand(demandDto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<DemandDto>> getDemandsByUserId(@PathVariable Long id) {
        return demandService.getAllDemandsByUserId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandDto> getDemandById(@PathVariable Long id) {
        return demandService.getDemandById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDemand(@PathVariable Long id) {
        demandService.deleteDemand(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> updateDemand(@PathVariable Long id, @RequestBody DemandDto demandDto) {
        return demandService.updateDemand(id, demandDto);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<Page<DemandDto>> getByCity(@PathVariable String city) {
        return demandService.getAllByCity(city);
    }
}
