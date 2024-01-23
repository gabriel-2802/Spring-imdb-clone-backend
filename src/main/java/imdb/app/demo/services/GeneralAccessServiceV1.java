package imdb.app.demo.services;

import imdb.app.demo.repos.MovieRepository;
import imdb.app.demo.repos.ProductionRepository;
import imdb.app.demo.services.interfaces.GeneralAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralAccessServiceV1 implements GeneralAccessService {
    private final ProductionRepository productionRepository;
    private final MovieRepository movieRepository;
}
