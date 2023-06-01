package com.Abdessalam.friendMA.service;

import com.Abdessalam.friendMA.dto.mapperInterface.IMapperDto;
import com.Abdessalam.friendMA.dto.model.DemandDto;
import com.Abdessalam.friendMA.entity.Demand;
import com.Abdessalam.friendMA.enumeration.RoomType;
import com.Abdessalam.friendMA.repository.DemandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemandService {


    private final DemandRepository demandRepository;
    private final IMapperDto<Demand, DemandDto> demandMapper;


    public Page<DemandDto> getAllDemands(int page,int size) {
        log.info("DemandService.getAllDemands()");
        Pageable pageable = PageRequest.of(page, size);
        Page<Demand> demands =  demandRepository.findAll(pageable);
        return demandMapper.convertPageToPageDto(demands, DemandDto.class);
    }

    public Demand createDemand(Demand demand) {
        log.info("DemandService.createDemand()" + demand);
        demand.setCity(demand.getCity().toLowerCase());
        return demandRepository.save(demand);
    }

    public ResponseEntity<List<DemandDto>> getAllDemandsByUserId(Long userId) {
        log.info("DemandService.getAllDemandsByUserId()");
        List<Demand> demands = demandRepository.findAllByUserId(userId);
        if (demands.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(demandMapper.convertListToListDto(demands, DemandDto.class));
    }

    public void deleteDemand(Long id) {
        log.info("DemandService.deleteDemand()");
        demandRepository.deleteById(id);
    }

    public ResponseEntity<String> updateDemand(Long id, DemandDto demandDto) {
        log.info("DemandService.updateDemand()");

        Optional<Demand> demand = demandRepository.findById(id);

        if (demand.isEmpty()) {
            return ResponseEntity.status(404).body("Demand not found");
        }

        demand.get().setTitle(demandDto.getTitle());
        demand.get().setDescription(demandDto.getDescription());
        demand.get().setAvailability(demandDto.getAvailability());
        demand.get().setBudget(demandDto.getBudget());
        demand.get().setAvailableFrom(demandDto.getAvailableFrom());
        demand.get().setCity(demandDto.getCity());
        demand.get().setRoomType(RoomType.valueOf(demandDto.getRoomType()));

        demandRepository.save(demand.get());
        return ResponseEntity.ok("Demand updated successfully");
    }

    public ResponseEntity<DemandDto> getDemandById(Long id) {
        log.info("DemandService.getDemandById()");
        Optional<Demand> demand = demandRepository.findById(id);
        if (demand.isEmpty()) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.ok(demandMapper.convertToDto(demand.get(), DemandDto.class));
        }
    }

    public ResponseEntity<Page<DemandDto>> getAllByCity(String city) {
        log.info("DemandService.getAllByCity()");
        Pageable pageable = PageRequest.of(0, 10);
        Page<Demand> demands = demandRepository.findAllByCity(city, pageable);
        return ResponseEntity.ok(demandMapper.convertPageToPageDto(demands, DemandDto.class));
    }
}
