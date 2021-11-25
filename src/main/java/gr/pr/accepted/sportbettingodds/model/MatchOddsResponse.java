package gr.pr.accepted.sportbettingodds.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MatchOddsResponse {

	private HttpStatus statusCode;
	private LocalDateTime timestamp = LocalDateTime.now();
	private MatchDTO match;
	private List<MatchOddDTO> matchOdds = new ArrayList<>();

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

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
		return "MatchOddsResponse{" +
				"statusCode=" + statusCode +
				", timestamp=" + timestamp +
				", match=" + match +
				", matchOdds=" + matchOdds +
				'}';
	}
}
