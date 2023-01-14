package platform.codingnomads.co.springdata.example.dml.usingqueryannotation.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import platform.codingnomads.co.springdata.example.dml.usingqueryannotation.models.SoilType;

import java.util.ArrayList;

@Repository
public interface SoilTypeRepo extends JpaRepository<SoilType, Long> {
    // Use JPQL to query the soil type
    @Query("SELECT st FROM SoilType st")
    ArrayList<SoilType> getSoilType();

    // Use native SQL to query the Soil Type with id 1
    @Query(value = "SELECT * FROM soil_types WHERE id = 1", nativeQuery = true)
    SoilType findSoilTypeWithIdOne();

    // Use native SQL to query the total count of Soil Types
    @Query(value = "SELECT count(id) FROM soil_types", nativeQuery = true)
    long findNumberOfSoilTypes();

    // Use JPQL to query the Soil Type with id variable
    @Query("SELECT st FROM SoilType st WHERE id = ?1")
    SoilType getSoilTypeById(Long id);

    // Use JPQL to query the Soil Type with name variable
    @Query("SELECT st FROM SoilType st WHERE name = ?1")
    SoilType getSoilTypeByName(String plantName);

    // Use JPQL to query the Soil Type with dry variable
    @Query("SELECT st FROM SoilType st WHERE dry = :dry")
    ArrayList<SoilType> getSoilTypeBasedOnDry(@Param("dry") boolean dry);

    // Get all dry Soil with the option to sort them
    @Query("SELECT st FROM SoilType st WHERE dry = true")
    ArrayList<SoilType> getDrySoil(Sort sort);
}
