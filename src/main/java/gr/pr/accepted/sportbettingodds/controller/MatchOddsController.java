package gr.pr.accepted.sportbettingodds.controller;

import gr.pr.accepted.sportbettingodds.model.MatchOddDTO;
import gr.pr.accepted.sportbettingodds.model.MatchOddsResponse;
import gr.pr.accepted.sportbettingodds.service.MatchOddService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@Api
@RestController
@RequestMapping("/match/{matchId}/odds")
public class MatchOddsController {

	private final MatchOddService service;

	public MatchOddsController(MatchOddService service) {
		this.service = service;
	}

	@ApiOperation("Create Match Odds for a Match")
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<MatchOddsResponse> createMatchOdds(@RequestBody @Valid List<MatchOddDTO> matchOddDTOS, @PathVariable UUID matchId) {
		MatchOddsResponse response = service.insertMatchOdds(matchId, matchOddDTOS);
		response.setStatusCode(HttpStatus.CREATED);
		return ResponseEntity.of(Optional.of(response));
	}

	@ApiOperation("Update MatchOdds")
	@PutMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<MatchOddsResponse> updateMatchOdds(@RequestBody @Valid List<MatchOddDTO> matchOddDTOS, @PathVariable UUID matchId) {
		MatchOddsResponse response = service.updateMatchOdd(matchId, matchOddDTOS);
		response.setStatusCode(HttpStatus.CREATED);
		return ResponseEntity.of(Optional.of(response));
	}

	@ApiOperation("Get MatchOdd by its ID")
	@GetMapping("{matchOddId}")
	public ResponseEntity<MatchOddsResponse> findMatchesOddById(@PathVariable UUID matchOddId) {
		MatchOddsResponse response = service.findMatchesOddById(matchOddId);
		response.setStatusCode(HttpStatus.OK);
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Find all MatchOdds of a Match")
	@GetMapping("/")
	public ResponseEntity<MatchOddsResponse> findMatchOddsByMatchId(@PathVariable UUID matchId) {
		MatchOddsResponse response = service.findMatchOddsByMatchId(matchId);
		response.setStatusCode(OK);
		return ResponseEntity.ok(response);
	}

	@ApiOperation("Delete MatchOdd by its ID")
	@DeleteMapping("/")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<MatchOddsResponse> deleteAllOddsMatchById(@PathVariable UUID matchId) {
		return ResponseEntity.of(Optional.of(service.deleteAllOddsMatchById(matchId)));
	}

	@ApiOperation("Delete all MatchOdds of a Match")
	@DeleteMapping("/{matchOddId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<MatchOddsResponse> deleteMatchOddById(@PathVariable UUID matchOddId) {
		return ResponseEntity.of(Optional.of(service.deleteMatchOddById(matchOddId)));
	}
}
