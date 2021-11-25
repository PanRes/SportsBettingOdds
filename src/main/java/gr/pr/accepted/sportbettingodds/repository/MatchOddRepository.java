package gr.pr.accepted.sportbettingodds.repository;

import gr.pr.accepted.sportbettingodds.entity.MatchOdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchOddRepository extends JpaRepository<MatchOdd, UUID> {

	@Query("SELECT mo from MatchOdd mo where mo.match.id = :matchId")
	List<MatchOdd> findByMatchId(UUID matchId);

	@Query("delete from MatchOdd where match.id = :matchId")
	List<MatchOdd> deleteByMatchId(UUID matchId);

}
