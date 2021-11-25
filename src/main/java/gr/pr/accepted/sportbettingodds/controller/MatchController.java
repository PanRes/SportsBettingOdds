package gr.pr.accepted.sportbettingodds.controller;

import gr.pr.accepted.sportbettingodds.model.MatchDTO;
import gr.pr.accepted.sportbettingodds.model.MatchOddsRequest;
import gr.pr.accepted.sportbettingodds.model.MatchOddsResponse;
import gr.pr.accepted.sportbettingodds.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("match")
public class MatchController {

	private final MatchService service;

	public MatchController(MatchService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public ResponseEntity<MatchOddsResponse> createMatch(@RequestBody @Valid MatchDTO matchDTO) {
		MatchOddsResponse response = service.insertMatch(matchDTO);
		response.setStatusCode(CREATED);
		return ResponseEntity.of(Optional.of(response));
	}

	@PostMapping("/odds")
	@ResponseStatus(CREATED)
	public ResponseEntity<MatchOddsResponse> createMatchAndMatchOdds(@RequestBody @Valid MatchOddsRequest request) {
		MatchOddsResponse response = service.insertMatchAndOdds(request);
		response.setStatusCode(CREATED);
		return ResponseEntity.of(Optional.of(response));
	}

	@PutMapping("/{matchId}")
	@ResponseStatus(ACCEPTED)
	public ResponseEntity<MatchOddsResponse> updateMatch(@RequestBody @Valid MatchDTO matchDTO, @PathVariable UUID matchId) {
		MatchOddsResponse response = service.updateMatch(matchDTO, matchId);
		response.setStatusCode(ACCEPTED);
		return ResponseEntity.of(Optional.of(response));
	}

	@GetMapping("/{matchId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<MatchOddsResponse> findMatchById(@PathVariable UUID matchId) {
		MatchOddsResponse response = service.findMatchById(matchId);
		response.setStatusCode(OK);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<MatchOddsResponse> findAllMatches() {
		MatchOddsResponse response = service.findAllMatches();
		response.setStatusCode(OK);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/odds")
	public ResponseEntity<MatchOddsResponse> findAllMatchesAndOdds() {
		MatchOddsResponse response = service.findAllMatchesAndOdds();
		response.setStatusCode(OK);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{matchId}")
	@ResponseStatus(NO_CONTENT)
	public ResponseEntity<MatchOddsResponse> deleteMatchById(@PathVariable UUID matchId) {
		return ResponseEntity.of(Optional.of(service.deleteMatchById(matchId)));
	}

}
