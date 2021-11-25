package gr.pr.accepted.sportbettingodds.model;

import java.util.ArrayList;
import java.util.List;

public class MatchOddsRequest {

	private MatchDTO match;
	private List<MatchOddDTO> matchOdds = new ArrayList<>();

	public MatchDTO getMatch() {
		return match;
	}

	public void setMatch(MatchDTO match) {
		this.match = match;
	}

	public List<MatchOddDTO> getMatchOdds() {
		return matchOdds;
	}

	public void setMatchOdds(List<MatchOddDTO> matchOdds) {
		this.matchOdds = matchOdds;
	}

	@Override
	public String toString() {
		return "MatchOddsRequest{" +
				"match=" + match +
				", matchOdds=" + matchOdds +
				'}';
	}
}
