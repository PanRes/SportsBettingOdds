package gr.pr.accepted.sportbettingodds.service;

import gr.pr.accepted.sportbettingodds.converter.MatchOddConverter;
import gr.pr.accepted.sportbettingodds.entity.Match;
import gr.pr.accepted.sportbettingodds.entity.MatchOdd;
import gr.pr.accepted.sportbettingodds.model.MatchOddDTO;
import gr.pr.accepted.sportbettingodds.model.MatchOddsResponse;
import gr.pr.accepted.sportbettingodds.repository.MatchOddRepository;
import gr.pr.accepted.sportbettingodds.repository.MatchRepository;
import gr.pr.accepted.sportbettingodds.util.ResponseUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
		Match match = matchRepository.getById(matchId);
		List<MatchOdd> matchOdds = matchOddDTOS.stream().map(matchOddDTO -> matchOddConverter.convert(match, matchOddDTO)).collect(Collectors.toList());
		matchOddRepository.saveAllAndFlush(matchOdds);
		return responseUtil.createResponse(matchOdds, matchOddDTOS);
	}

	public MatchOddsResponse updateMatchOdd(UUID matchId, List<MatchOddDTO> matchOddDTOs) {
		Match match = matchRepository.getById(matchId);
		List<MatchOdd> matchOdds = new ArrayList<>();
		for (MatchOddDTO matchOddDTO : matchOddDTOs) {
			matchOdds.add(matchOddConverter.convert(match, matchOddDTO));

		}
		matchOddRepository.saveAllAndFlush(matchOdds);

		return responseUtil.createResponse(match, matchOddDTOs);
	}

	public MatchOddsResponse findMatchesOddById(UUID matchOddId) {
		return  responseUtil.createResponse(matchOddRepository.findById(matchOddId).orElseThrow());
	}

	public MatchOddsResponse findMatchOddsByMatchId(UUID matchId){
		return responseUtil.createResponseMatchOdds(matchOddRepository.findByMatchId(matchId));
	}

}
