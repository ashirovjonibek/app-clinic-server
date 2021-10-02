package uz.napa.clinic.service.iml;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.napa.clinic.entity.Words;
import uz.napa.clinic.payload.ApiResponse;
import uz.napa.clinic.repository.WordsRepository;
import uz.napa.clinic.service.WordsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordsServiceImpl implements WordsService {
    private final WordsRepository wordsRepository;

    public WordsServiceImpl(WordsRepository wordsRepository) {
        this.wordsRepository = wordsRepository;
    }

    @Override
    public ApiResponse create(Words request) {
        wordsRepository.save(request);
        return new ApiResponse("So'z kiritildi !", true);
    }

    @Override
    public Words getById(Long id) {
        return wordsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Data not found"));
    }

    @Override
    public ApiResponse delete(Long id) {
        if (getById(id) != null) {
            wordsRepository.deleteById(id);
            return new ApiResponse("So'z o'chirildi !", false);
        }
        return new ApiResponse("So'z topilmadi !", false);
    }

    @Override
    public ApiResponse update(Long id, Words request) {
        Words words = getById(id);
        words.setName(request.getName());
        wordsRepository.save(words);
        return new ApiResponse("So'z o'zgartirildi !", true);
    }

    @Override
    public List<Words> list() {
        List<Words> all = wordsRepository.findAll();
        return all.stream().map(words -> new Words(words.getId(), words.getName())).collect(Collectors.toList());
    }
}
