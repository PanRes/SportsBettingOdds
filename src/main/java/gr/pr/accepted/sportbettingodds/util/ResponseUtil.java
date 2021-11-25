package gr.pr.accepted.sportbettingodds.util;

import gr.pr.accepted.sportbettingodds.entity.Match;
import gr.pr.accepted.sportbettingodds.entity.MatchOdd;
import gr.pr.accepted.sportbettingodds.model.MatchDTO;
import gr.pr.accepted.sportbettingodds.model.MatchOddDTO;
import gr.pr.accepted.sportbettingodds.model.MatchOddsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@Component
public class ResponseUtil {

	private ResponseUtil() {}

	public MatchOddsResponse createResponse(MatchDTO responseMatch, UUID matchId) {
		MatchOddsResponse response = new MatchOddsResponse();
		responseMatch.setId(matchId);
		response.setMatches(singletonList(responseMatch));
		return response;
	}

	public MatchOddsResponse createResponse(MatchDTO responseMatch, UUID matchId, List<MatchOdd> matchOdds, List<MatchOddDTO> matchOddDTOs) {
		MatchOddsResponse response = new MatchOddsResponse();
		responseMatch.setId(matchId);
		response.setMatches(singletonList(responseMatch));
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
		response.setMatches(singletonList(matchDTO));
		response.setMatchOdds(matchOddDTOs);

		return response;
	}

	public MatchOddsResponse createResponse(List<Match> matches) {
		MatchOddsResponse response = new MatchOddsResponse();
		for (Match match : matches) {
			MatchDTO matchDTO = new MatchDTO();
			matchDTO.setId(match.getId());
			matchDTO.setDescription(match.getDescription());
			matchDTO.setTeamA(match.getTeamA());
			matchDTO.setTeamB(match.getTeamB());
			matchDTO.setSport(match.getSport());

			response.getMatches().add(matchDTO);
		}
		return response;
	}

	public MatchOddsResponse createResponseMatchOdds(List<MatchOdd> matchOdds) {
		MatchOddsResponse response = createResponse(matchOdds.stream().map(MatchOdd::getMatch).collect(Collectors.toList()));
		for (MatchOdd matchOdd : matchOdds) {
			MatchOddDTO matchOddDTO = new MatchOddDTO();
			matchOddDTO.setId(matchOdd.getId());
			matchOddDTO.setSpecifier(matchOdd.getSpecifier());
			matchOddDTO.setOdds(matchOdd.getOdds());
			matchOddDTO.setMatchId(matchOdd.getMatch().getId());
			response.getMatchOdds().add(matchOddDTO);
		}
		return response;
	}

	public MatchOddsResponse createResponse(Match match) {
		return createResponse(singletonList(match));
	}

	public MatchOddsResponse createResponse(MatchOdd matchOdd) {
		return createResponseMatchOdds(singletonList(matchOdd));
	}
}
