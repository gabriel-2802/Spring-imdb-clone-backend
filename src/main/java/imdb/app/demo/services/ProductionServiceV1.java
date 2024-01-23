package imdb.app.demo.services;

import imdb.app.demo.entities.Production;
import imdb.app.demo.repos.ProductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductionServiceV1 implements ProductionService {
    private final ProductionRepository productionRepository;

    @Override
    public void updateProductionRating(Production production) {
        production.updateRating();
        productionRepository.save(production);
    }
}
