package gr.pr.accepted.sportbettingodds.converter;

import gr.pr.accepted.sportbettingodds.entity.Match;
import gr.pr.accepted.sportbettingodds.enums.SportType;
import gr.pr.accepted.sportbettingodds.model.MatchDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MatchConverter {

	public Match convert(MatchDTO matchDTO, UUID matchId) {
		Match match = new Match();
		if (matchId != null) {
			match.setId(matchId);
		}
		match.setDescription(matchDTO.getDescription());
		match.setSport(SportType.FOOTBALL);
		match.setMatchDate(matchDTO.getMatchDate());
		match.setTeamA(matchDTO.getTeamA());
		match.setTeamB(matchDTO.getTeamB());
		return match;
	}
}
