package gr.pr.accepted.sportbettingodds.repository;

import gr.pr.accepted.sportbettingodds.entity.MatchOdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatchOddRepository extends JpaRepository<MatchOdd, UUID> {
}
