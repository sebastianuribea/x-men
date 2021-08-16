package co.com.mercadolibre.xmen.repository;

import co.com.mercadolibre.xmen.model.DnaEntity;
import org.json.simple.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DnaRepository extends JpaRepository<DnaEntity,Long> {
    Optional<DnaEntity> findByDna(String dna);

    @Query(value = "select count(mutant) as count_human from xmendb.dna where mutant = :value", nativeQuery = true)
    JSONObject getCount(@Param("value") boolean value);

}
