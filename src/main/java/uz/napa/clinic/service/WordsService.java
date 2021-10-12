package uz.napa.clinic.service;

import uz.napa.clinic.entity.Words;
import uz.napa.clinic.payload.ApiResponse;
import uz.napa.clinic.payload.ResPageable;

import java.util.List;

public interface WordsService {
    ApiResponse create(Words request) ;

    Words getById(Long id);

    ApiResponse delete(Long id);

    ApiResponse update(Long id, Words request) ;

    ResPageable list(int page, int size);
}
