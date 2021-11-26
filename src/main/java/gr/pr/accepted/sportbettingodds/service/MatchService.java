package gr.pr.accepted.sportbettingodds.service;

import gr.pr.accepted.sportbettingodds.converter.MatchConverter;
import gr.pr.accepted.sportbettingodds.converter.MatchOddConverter;
import gr.pr.accepted.sportbettingodds.entity.Match;
import gr.pr.accepted.sportbettingodds.entity.MatchOdd;
import gr.pr.accepted.sportbettingodds.exception.DataNotFoundException;
import gr.pr.accepted.sportbettingodds.model.MatchDTO;
import gr.pr.accepted.sportbettingodds.model.MatchOddsRequest;
import gr.pr.accepted.sportbettingodds.model.MatchOddsResponse;
import gr.pr.accepted.sportbettingodds.repository.MatchOddRepository;
import gr.pr.accepted.sportbettingodds.repository.MatchRepository;
import gr.pr.accepted.sportbettingodds.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MatchService {

	private final MatchRepository matchRepository;
	private final MatchOddRepository matchOddRepository;
	private final MatchConverter matchConverter;
	private final MatchOddConverter matchOddConverter;
	private final ResponseUtil responseUtil;

	public MatchService(MatchRepository matchRepository, MatchOddRepository matchOddRepository, MatchConverter matchConverter, MatchOddConverter matchOddConverter, ResponseUtil responseUtil) {
		this.matchRepository = matchRepository;
		this.matchOddRepository = matchOddRepository;
		this.matchConverter = matchConverter;
		this.matchOddConverter = matchOddConverter;
		this.responseUtil = responseUtil;
	}

	public MatchOddsResponse insertMatch(MatchDTO matchDTO) {
		Match match = matchConverter.convert(matchDTO, null);
		matchRepository.saveAndFlush(match);
		return responseUtil.createResponse(matchDTO, match.getId());
	}

	public MatchOddsResponse insertMatchAndOdds(MatchOddsRequest request) {
		Match match = matchConverter.convert(request.getMatch(), null);
		matchRepository.save(match);
		List<MatchOdd> matchOdds = request.getMatchOdds().stream().map(matchOddDTO -> matchOddConverter.convert(match, matchOddDTO)).collect(Collectors.toList());
		matchOddRepository.saveAllAndFlush(matchOdds);
		return responseUtil.createResponse(request.getMatch(), match.getId(), matchOdds, request.getMatchOdds());
	}

	public MatchOddsResponse updateMatch(MatchDTO matchDTO, UUID matchId) {
		Match match = matchConverter.convert(matchDTO, matchId);
		matchRepository.saveAndFlush(match);
		return responseUtil.createResponse(matchDTO, matchId);
	}

	public MatchOddsResponse findMatchById(UUID matchId) {
		return  responseUtil.createResponse(matchRepository.findById(matchId).orElseThrow());
	}

	public MatchOddsResponse findAllMatches() {
		return  responseUtil.createResponse(matchRepository.findAll());
	}

	public MatchOddsResponse findAllMatchesAndOdds() {
		return  responseUtil.createResponseMatchOdds(matchOddRepository.findAll());
	}

	public MatchOddsResponse deleteMatchById(UUID matchId) {
		try {
			matchRepository.deleteById(matchId);
		} catch (Exception e) {
			throw new DataNotFoundException("Match not found", e);
		}
		return new MatchOddsResponse(HttpStatus.NO_CONTENT);
	}

}
