package platform.codingnomads.co.springweb.gettingdatafromclient.handlingmultipartdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springweb.gettingdatafromclient.handlingmultipartdata.models.DatabaseFile;

import java.util.List;

public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, Long> {
    boolean existsByFileName(String fileName);
    List<DatabaseFile> findByFileName(String fileName);
}
