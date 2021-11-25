package gr.pr.accepted.sportbettingodds.util;

import gr.pr.accepted.sportbettingodds.entity.Match;
import gr.pr.accepted.sportbettingodds.entity.MatchOdd;
import gr.pr.accepted.sportbettingodds.model.MatchDTO;
import gr.pr.accepted.sportbettingodds.model.MatchOddDTO;
import gr.pr.accepted.sportbettingodds.model.MatchOddsResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Component
public class ResponseUtil {

	private ResponseUtil() {}

	public MatchOddsResponse createResponse(MatchDTO responseMatch, UUID matchId) {
		MatchOddsResponse response = new MatchOddsResponse();
		response.setMatch(responseMatch);
		response.getMatch().setId(matchId);
		return response;
	}

	public MatchOddsResponse createResponse(MatchDTO responseMatch, UUID matchId, List<MatchOdd> matchOdds, List<MatchOddDTO> matchOddDTOs) {
		MatchOddsResponse response = new MatchOddsResponse();
		response.setMatch(responseMatch);
		response.getMatch().setId(matchId);
		for (MatchOddDTO matchOddDTO : matchOddDTOs) {
			MatchOdd respectiveMatchOdd = matchOdds.stream().filter(matchOdd -> matchOdd.getSpecifier().equals(matchOddDTO.getSpecifier())).findFirst().orElseThrow();
			matchOddDTO.setId(respectiveMatchOdd.getId());
			response.getMatchOdds().add(matchOddDTO);
		}
		return response;
	}

	public MatchOddsResponse createResponse(List<MatchOdd> matchOdds, List<MatchOddDTO> matchOddDTOs) {
		MatchOddsResponse response = new MatchOddsResponse();
		for (MatchOddDTO matchOddDTO : matchOddDTOs) {
			MatchOdd respectiveMatchOdd = matchOdds.stream().filter(matchOdd -> matchOdd.getSpecifier().equals(matchOddDTO.getSpecifier())).findFirst().orElseThrow();
			matchOddDTO.setId(respectiveMatchOdd.getId());
			response.getMatchOdds().add(matchOddDTO);
		}
		return response;
	}

	public MatchOddsResponse createResponse(Match match, List<MatchOddDTO> matchOddDTOs) {
		MatchDTO matchDTO = new MatchDTO();
		matchDTO.setId(match.getId());
		matchDTO.setDescription(match.getDescription());
		matchDTO.setTeamA(match.getTeamA());
		matchDTO.setTeamB(match.getTeamB());
		matchDTO.setSport(match.getSport());

		MatchOddsResponse response = new MatchOddsResponse();
		response.setMatch(matchDTO);
		response.setMatchOdds(matchOddDTOs);

		return response;
	}
}
