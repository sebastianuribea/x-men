package co.com.mercadolibre.xmen.repository;

import co.com.mercadolibre.xmen.model.DnaEntity;
import org.json.simple.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DnaRepository extends JpaRepository<DnaEntity,Long> {
    Optional<DnaEntity> findByDna(String dna);
}
