package platform.codingnomads.co.springdata.example.dml.usingqueryannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import platform.codingnomads.co.springdata.example.dml.usingqueryannotation.models.SoilType;
import platform.codingnomads.co.springdata.example.dml.usingqueryannotation.repositories.SoilTypeRepo;

import java.util.ArrayList;

@Service
public class SoilTypeService {
    @Autowired
    SoilTypeRepo soilTypeRepo;

    @Transactional
    public void getSoil() {
        System.out.println("\n** Print all soils **");
        ArrayList<SoilType> soilTypes = soilTypeRepo.getSoilType();
        soilTypes.forEach(System.out::println);

        System.out.println("\n** Print soil 1 **");
        SoilType soilType1 = soilTypeRepo.findSoilTypeWithIdOne();
        System.out.println(soilType1);

        System.out.println("\n** Total soil types **");
        long soilTotal = soilTypeRepo.findNumberOfSoilTypes();
        System.out.println("Total soil types: " + soilTotal);

        System.out.println("\n** Print soil with id variable **");
        SoilType soilType = soilTypeRepo.getSoilTypeById(2L);
        System.out.println(soilType);

        System.out.println("\n** Print soil with name variable **");
        SoilType soilType2 = soilTypeRepo.getSoilTypeByName("tester2");
        System.out.println(soilType2);

        System.out.println("\n** Print dry soils **");
        ArrayList<SoilType> soilTypesDry = soilTypeRepo.getSoilTypeBasedOnDry(true);
        soilTypesDry.forEach(System.out::println);

        System.out.println("\n** Print and sort dry soil **");
        Sort sortByPh = Sort.by(Sort.Order.asc("ph"));
        ArrayList<SoilType> soilTypesDrySorted = soilTypeRepo.getDrySoil(sortByPh);
        soilTypesDrySorted.forEach(System.out::println);
    }
}
