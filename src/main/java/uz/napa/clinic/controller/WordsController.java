package uz.napa.clinic.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.clinic.payload.WordRequest;
import uz.napa.clinic.payload.ApiResponse;
import uz.napa.clinic.service.iml.WordsServiceImpl;
import uz.napa.clinic.utils.AppConstants;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/words")
public class WordsController {

    private static final String GET_ALL = "";
    private static final String DELETE = "/{id}";

    final
    WordsServiceImpl wordsService;

    public WordsController(WordsServiceImpl wordsService) {
        this.wordsService = wordsService;
    }

    @PostMapping
    public HttpEntity<?> create(@RequestBody WordRequest words) {
        ApiResponse response = wordsService.create(words);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping
    public HttpEntity<?> update(@RequestBody WordRequest words) {
        ApiResponse update = wordsService.update(words);
        return ResponseEntity.status(update.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(update);
    }

    @GetMapping(GET_ALL)
    public HttpEntity<?> getWordsList(@RequestParam(name = "page",defaultValue = AppConstants.DEFAULT_PAGE) int page,
                                      @RequestParam(name = "size",defaultValue = AppConstants.DEFAULT_SIZE) int size) {
        return ResponseEntity.ok(wordsService.list(page,size));
    }

    @DeleteMapping(DELETE)
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse delete = wordsService.delete(id);
        return ResponseEntity.ok(delete);
    }
}
