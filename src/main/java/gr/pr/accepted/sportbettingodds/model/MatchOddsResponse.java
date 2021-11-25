package gr.pr.accepted.sportbettingodds.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MatchOddsResponse {

	private HttpStatus statusCode;
	private LocalDateTime timestamp = LocalDateTime.now();
	private List<MatchDTO> matches = new ArrayList<>();
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

	public List<MatchDTO> getMatches() {
		return matches;
	}

	public void setMatches(List<MatchDTO> matches) {
		this.matches = matches;
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
				", match=" + matches +
				", matchOdds=" + matchOdds +
				'}';
	}
}
