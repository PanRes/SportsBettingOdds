package gr.pr.accepted.sportbettingodds.converter;

import gr.pr.accepted.sportbettingodds.entity.Match;
import gr.pr.accepted.sportbettingodds.entity.MatchOdd;
import gr.pr.accepted.sportbettingodds.model.MatchOddDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MatchOddConverter {

	public MatchOdd convert(Match match, MatchOddDTO matchOddDTO) {
		MatchOdd matchOdd = new MatchOdd();
		if (matchOddDTO.getId() != null) {
			matchOdd.setId(matchOddDTO.getId());
		}
		matchOdd.setMatch(match);
		matchOdd.setSpecifier(matchOddDTO.getSpecifier());
		matchOdd.setOdds(matchOddDTO.getOdds());
		return matchOdd;
	}

}
