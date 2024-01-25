package imdb.app.demo.services;

import imdb.app.demo.entities.Review;
import imdb.app.demo.entities.entries.Production;
import imdb.app.demo.repos.ProductionRepository;
import imdb.app.demo.services.interfaces.ProductionService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductionServiceV1 implements ProductionService {
    private final ProductionRepository productionRepository;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void updateProductionRating(int productionId) {
        Optional<Production> productionEntry = productionRepository.findById(productionId);

        if (productionEntry.isEmpty()) {
            return;
        }

        Production production = productionEntry.get();
        entityManager.refresh(production);
        production.updateRating();
        System.out.println(production.getRating());
        productionRepository.save(production);

        Production production2 = productionRepository.findById(productionId).get();
                System.out.println(production2.getRating());

    }
}

