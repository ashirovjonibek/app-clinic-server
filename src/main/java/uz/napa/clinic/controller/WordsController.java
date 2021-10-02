package uz.napa.clinic.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.clinic.entity.Words;
import uz.napa.clinic.payload.ApiResponse;
import uz.napa.clinic.service.iml.WordsServiceImpl;

@RestController
@RequestMapping("/api/words")
public class WordsController {

    private static final String CREATE = "/create";
    private static final String UPDATE = "/{id}";
    private static final String GET_ALL = "";
    private static final String DELETE = "/{id}";

    final
    WordsServiceImpl wordsService;

    public WordsController(WordsServiceImpl wordsService) {
        this.wordsService = wordsService;
    }

    @PostMapping(CREATE)
    public HttpEntity<?> create(@RequestBody Words words) {
        ApiResponse response = wordsService.create(words);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping(UPDATE)
    public HttpEntity<?> update(@PathVariable Long id, @RequestBody Words words) {
        ApiResponse update = wordsService.update(id, words);
        return ResponseEntity.status(update.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(update);
    }

    @GetMapping(GET_ALL)
    public HttpEntity<?> getWordsList() {
        return ResponseEntity.ok(wordsService.list());
    }

    @DeleteMapping(DELETE)
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse delete = wordsService.delete(id);
        return ResponseEntity.ok(delete);
    }
}
