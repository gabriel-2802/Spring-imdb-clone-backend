package imdb.app.demo.entities;

import imdb.app.demo.context.ApplicationContextProvider;
import imdb.app.demo.entities.entries.Production;
import imdb.app.demo.services.interfaces.ProductionService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.springframework.context.ApplicationContext;

public class ReviewListener {
    @PostRemove
    @PostUpdate
    @PostPersist
    public void updateProductionRating(Review review) {
        ApplicationContext context = ApplicationContextProvider.getContext();
        ProductionService productionService = context.getBean(ProductionService.class);

        Production production = review.getProduction();
        productionService.updateProductionRating(production);
    }
}
