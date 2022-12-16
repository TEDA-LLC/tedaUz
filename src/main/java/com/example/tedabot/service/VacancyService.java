package com.example.tedabot.service;

import com.example.tedabot.dto.ApiResponse;
import com.example.tedabot.dto.VacancyDTO;
import com.example.tedabot.model.Vacancy;
import com.example.tedabot.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Malikov Azizjon    tedaUz    16.12.2022    15:57
 */
@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    public ApiResponse<List<Vacancy>> getAll() {
        List<Vacancy> vacancies = vacancyRepository.findAll();
        if (vacancies.isEmpty()) {
            return ApiResponse.<List<Vacancy>>builder().
                    message("Not Found").
                    status(204).
                    success(false).
                    build();
        } else {
            return ApiResponse.<List<Vacancy>>builder().
                    message("Here").
                    status(200).
                    success(true).
                    data(vacancies).
                    build();
        }
    }

    public ApiResponse<Vacancy> getOne(Long id) {
        Optional<Vacancy> optionalVacancy = vacancyRepository.findById(id);
        if (optionalVacancy.isEmpty()) {
            return ApiResponse.<Vacancy>builder().
                    message("Not Found").
                    status(204).
                    success(false).
                    build();
        } else {
            return ApiResponse.<Vacancy>builder().
                    message("Here").
                    status(200).
                    success(true).
                    data(optionalVacancy.get()).
                    build();
        }
    }

//    @SneakyThrows
    public ApiResponse<?> add(VacancyDTO vacancyDTO) {
//        Optional<Vacancy> vacancyOptional = vacancyRepository.findById(vacancyDTO.getId());
//        if (vacancyOptional.isEmpty()) {
//            return ApiResponse.builder().
//                    message("Category not found").
//                    status(400).
//                    success(false).
//                    build();
//        }

//        MultipartFile photo = productDTO.getPhoto();
//        Attachment attachment = new Attachment();
//        attachment.setBytes(photo.getBytes());
//        attachment.setOriginalName(photo.getOriginalFilename());

        Vacancy vacancy = new Vacancy();
        vacancy.setName(vacancyDTO.getName());
        vacancy.setDescription(vacancyDTO.getDescription());
//        vacancy.setActive(vacancyDTO.isActive());
//        vacancy.setActive(productDTO.getPrice());

        vacancyRepository.save(vacancy);

        return ApiResponse.builder().
                message("Saved").
                status(200).
                success(true).
                build();
    }

    @SneakyThrows
    public ApiResponse<?> edit(VacancyDTO vacancyDTO, Long id) {
        Optional<Vacancy> optionalVacancy = vacancyRepository.findById(id);
//        Optional<Vacancy> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        if (optionalVacancy.isEmpty()) {
            return ApiResponse.builder().
                    message("Vacancy Not Found").
                    status(204).
                    success(false).
                    build();
        }
//        if (optionalVacancy.isEmpty()) {
//            return ApiResponse.builder().
//                    message("Category Not Found").
//                    status(204).
//                    success(false).
//                    build();
//        }

//        MultipartFile photo = productDTO.getPhoto();
//        Attachment attachment = new Attachment();
//        attachment.setBytes(photo.getBytes());
//        attachment.setOriginalName(photo.getOriginalFilename());

        Vacancy vacancy = optionalVacancy.get();
        vacancy.setName(vacancyDTO.getName());
        vacancy.setDescription(vacancyDTO.getDescription());
//        vacancy.setActive(vacancyDTO.isActive());
//        vacancy.setDescriptionUz(productDTO.getNameUz());
//        vacancy.setDescriptionRu(productDTO.getDescriptionRu());
//        vacancy.setDescriptionEn(productDTO.getDescriptionEn());
//        vacancy.setPrice(productDTO.getPrice());
//        vacancy.setCategory(optionalCategory.get());
//        vacancy.setAttachment(attachment);

        vacancyRepository.save(vacancy);

        return ApiResponse.builder().
                message("Edited !").
                status(200).
                success(true).
                build();
    }

    public ApiResponse<?> delete(Long id) {
        Optional<Vacancy> optionalVacancy = vacancyRepository.findById(id);
        if (optionalVacancy.isEmpty()) {
            return ApiResponse.builder().
                    message("Vacancy Not Found").
                    status(204).
                    success(false).
                    build();
        }
        vacancyRepository.deleteById(id);
        return ApiResponse.builder().
                message("Deleted").
                status(201).
                success(true).
                build();
    }

}
