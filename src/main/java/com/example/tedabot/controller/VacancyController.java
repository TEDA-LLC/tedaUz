package com.example.tedabot.controller;

import com.example.tedabot.dto.ApiResponse;
import com.example.tedabot.dto.ProductDTO;
import com.example.tedabot.dto.VacancyDTO;
import com.example.tedabot.model.Vacancy;
import com.example.tedabot.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Malikov Azizjon    tedaUz    16.12.2022    16:10
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vacancy")
public class VacancyController {

    private final VacancyService vacancyService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse<List<Vacancy>> response = vacancyService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody VacancyDTO vacancyDTO) {
        ApiResponse<?> response = vacancyService.add(vacancyDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        ApiResponse<Vacancy> response = vacancyService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody VacancyDTO vacancyDTO, @PathVariable Long id){
        ApiResponse<?> response = vacancyService.edit(vacancyDTO,id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse<?> response = vacancyService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
