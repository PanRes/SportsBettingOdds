package gr.pr.accepted.sportbettingodds.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class MatchOddsRequest {

	@ApiModelProperty(required = true)
	@NotEmpty
	private MatchDTO match;
	@ApiModelProperty(required = true)
	@NotEmpty
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
