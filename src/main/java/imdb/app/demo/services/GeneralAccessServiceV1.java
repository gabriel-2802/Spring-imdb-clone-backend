package imdb.app.demo.services;

import imdb.app.demo.entities.Review;
import imdb.app.demo.entities.entries.Production;
import imdb.app.demo.entities.request_response.SearchRequest;
import imdb.app.demo.repos.MovieRepository;
import imdb.app.demo.repos.ProductionRepository;
import imdb.app.demo.services.interfaces.GeneralAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeneralAccessServiceV1 implements GeneralAccessService {
    private final ProductionRepository productionRepository;
    private static final int MAX_RESULTS = 10;

    @Override
    public List<List<Production>> viewHome() {
        List<Production> topProductions = productionRepository.findTopRated().stream()
                .limit(MAX_RESULTS).toList();
        List<Production> randomProductions = productionRepository.findRandom().stream()
                .limit(MAX_RESULTS).toList();
        List<Production> latestProductions = productionRepository.findLatest().stream()
                .limit(MAX_RESULTS).toList();

        return List.of(topProductions, latestProductions, randomProductions);
    }

    @Override
    public ResponseEntity<Production> viewProduction(Integer id) {
        Optional<Production> productionEntry = productionRepository.findById(id);
        if (productionEntry.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Production production = productionEntry.get();
        return ResponseEntity.ok(production);
    }

    @Override
    public ResponseEntity<List<Review>> viewProductionReviews(Integer id) {
        Optional<Production> productionEntry = productionRepository.findById(id);
        if (productionEntry.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Production production = productionEntry.get();
        return ResponseEntity.ok(production.getReviews());
    }

    @Override
    public ResponseEntity<List<Production>> search(SearchRequest query) {
        List<Production> productions = productionRepository.findAll().stream().filter(production -> {
            if (query.getTitle() != null && !production.getTitle().contains(query.getTitle())) {
                return false;
            }

            if (query.getYear() != 0 && production.getReleaseYear() != query.getYear()) {
                return false;
            }

            if (query.getGenres() != null && !query.getGenres().isEmpty() && !production.getGenres().contains(query.getGenres())) {
                return false;
            }

            if (query.getRating() != 0 && production.getRating() < query.getRating()) {
                return false;
            }

            return true;
        }).limit(MAX_RESULTS).toList();

        return ResponseEntity.ok(productions);
    }
}
