package gr.pr.accepted.sportbettingodds.controller;

import gr.pr.accepted.sportbettingodds.model.MatchOddDTO;
import gr.pr.accepted.sportbettingodds.model.MatchOddsResponse;
import gr.pr.accepted.sportbettingodds.service.MatchOddService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("match/{matchId}/odds")
public class MatchOddsController {

	private final MatchOddService service;

	public MatchOddsController(MatchOddService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<MatchOddsResponse> createMatchOdds(@RequestBody @Valid List<MatchOddDTO> matchOddDTOS, @PathVariable UUID matchId) {
		MatchOddsResponse response = service.insertMatchOdds(matchId, matchOddDTOS);
		response.setStatusCode(HttpStatus.CREATED);
		return ResponseEntity.of(Optional.of(response));
	}

	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<MatchOddsResponse> updateMatchOdds(@RequestBody @Valid List<MatchOddDTO> matchOddDTOS, @PathVariable UUID matchId) {
		MatchOddsResponse response = service.updateMatchOdd(matchId, matchOddDTOS);
		response.setStatusCode(HttpStatus.CREATED);
		return ResponseEntity.of(Optional.of(response));
	}

	@GetMapping("{matchOddId}")
	public ResponseEntity<MatchOddsResponse> findMatchesOddById(@PathVariable UUID matchOddId) {
		MatchOddsResponse response = service.findMatchesOddById(matchOddId);
		response.setStatusCode(HttpStatus.OK);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<MatchOddsResponse> findMatchOddsByMatchId(@PathVariable UUID matchId) {
		MatchOddsResponse response = service.findMatchOddsByMatchId(matchId);
		response.setStatusCode(OK);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<MatchOddsResponse> deleteAllOddsMatchById(@PathVariable UUID matchId) {
		return ResponseEntity.of(Optional.of(service.deleteAllOddsMatchById(matchId)));
	}

	@DeleteMapping("/{matchOddId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<MatchOddsResponse> deleteMatchOddById(@PathVariable UUID matchOddId) {
		return ResponseEntity.of(Optional.of(service.deleteMatchOddById(matchOddId)));
	}
}
