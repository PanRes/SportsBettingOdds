package gr.pr.accepted.sportbettingodds.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class MatchOddDTO {

	@ApiModelProperty
	private UUID id;
	@ApiModelProperty(required = true)
	@NotNull
	@NotBlank
	private String specifier;
	@ApiModelProperty(required = true)
	@NotNull
	@Min(1)
	private Double odds;
	@ApiModelProperty
	private UUID matchId;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Double getOdds() {
		return odds;
	}

	public void setOdds(Double odds) {
		this.odds = odds;
	}

	public String getSpecifier() {
		return specifier;
	}

	public void setSpecifier(String specifier) {
		this.specifier = specifier;
	}

	public UUID getMatchId() {
		return matchId;
	}

	public void setMatchId(UUID matchId) {
		this.matchId = matchId;
	}

	@Override
	public String toString() {
		return "MatchOddDTO{" +
				"id=" + id +
				", specifier='" + specifier + '\'' +
				", odds=" + odds +
				", matchId=" + matchId +
				'}';
	}
}
