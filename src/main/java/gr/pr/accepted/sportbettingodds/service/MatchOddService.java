package gr.pr.accepted.sportbettingodds.service;

import gr.pr.accepted.sportbettingodds.converter.MatchOddConverter;
import gr.pr.accepted.sportbettingodds.entity.Match;
import gr.pr.accepted.sportbettingodds.entity.MatchOdd;
import gr.pr.accepted.sportbettingodds.model.MatchOddDTO;
import gr.pr.accepted.sportbettingodds.model.MatchOddsResponse;
import gr.pr.accepted.sportbettingodds.repository.MatchOddRepository;
import gr.pr.accepted.sportbettingodds.repository.MatchRepository;
import gr.pr.accepted.sportbettingodds.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import gr.pr.accepted.sportbettingodds.exception.DataNotFoundException;

@Service
public class MatchOddService {

	private final MatchRepository matchRepository;
	private final MatchOddRepository matchOddRepository;
	private final MatchOddConverter matchOddConverter;
	private final ResponseUtil responseUtil;


	public MatchOddService(MatchRepository matchRepository, MatchOddRepository matchOddRepository, MatchOddConverter matchOddConverter, ResponseUtil responseUtil) {
		this.matchRepository = matchRepository;
		this.matchOddRepository = matchOddRepository;
		this.matchOddConverter = matchOddConverter;
		this.responseUtil = responseUtil;
	}

	public MatchOddsResponse insertMatchOdds(UUID matchId, List<MatchOddDTO> matchOddDTOS) {
		Match match;
		try {
			match = matchRepository.getById(matchId);
		} catch (Exception e) {
			throw new DataNotFoundException("Match not found", e);
		}
		List<MatchOdd> matchOdds = matchOddDTOS.stream().map(matchOddDTO -> matchOddConverter.convert(match, matchOddDTO)).collect(Collectors.toList());
		matchOddRepository.saveAllAndFlush(matchOdds);
		return responseUtil.createResponse(matchOdds, matchOddDTOS);
	}

	public MatchOddsResponse updateMatchOdd(UUID matchId, List<MatchOddDTO> matchOddDTOs) {
		Match match;
		try {
			match = matchRepository.getById(matchId);
		} catch (Exception e) {
			throw new DataNotFoundException("Match not found", e);
		}
		List<MatchOdd> matchOdds = new ArrayList<>();
		for (MatchOddDTO matchOddDTO : matchOddDTOs) {
			matchOdds.add(matchOddConverter.convert(match, matchOddDTO));

		}
		matchOddRepository.saveAllAndFlush(matchOdds);

		return responseUtil.createResponse(match, matchOddDTOs);
	}

	public MatchOddsResponse findMatchesOddById(UUID matchOddId) {
		return  responseUtil.createResponse(matchOddRepository.findById(matchOddId).orElseThrow(() -> new DataNotFoundException("Match Odd not found")));
	}

	public MatchOddsResponse findMatchOddsByMatchId(UUID matchId){
		try {
			return responseUtil.createResponseMatchOdds(matchOddRepository.findByMatchId(matchId));
		} catch (Exception e) {
			throw new DataNotFoundException("Match not found", e);
		}
	}

	public MatchOddsResponse deleteAllOddsMatchById(UUID matchId) {
		try {
			matchOddRepository.deleteByMatchId(matchId);
		} catch (Exception e) {
			throw new DataNotFoundException("Match not found", e);
		}
		return new MatchOddsResponse(HttpStatus.NO_CONTENT);
	}

	public MatchOddsResponse deleteMatchOddById(UUID matchOddId) {
		try {
			matchOddRepository.deleteById(matchOddId);
		} catch (Exception e) {
			throw new DataNotFoundException("Match Odd not found", e);
		}
		return new MatchOddsResponse(HttpStatus.NO_CONTENT);
	}
}
