package davidemancini.U5_W2_D4.repositories;

import davidemancini.U5_W2_D4.entities.Autori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutoriRepository extends JpaRepository<Autori, UUID> {
}
