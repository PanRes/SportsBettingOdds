package gr.pr.accepted.sportbettingodds.model;

import gr.pr.accepted.sportbettingodds.enums.SportType;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public class MatchDTO {

	@ApiModelProperty
	private UUID id;
	@ApiModelProperty
	private String description;
	@ApiModelProperty(required = true)
	@NotNull
	private LocalDateTime matchDate;
	@ApiModelProperty(required = true)
	@NotNull
	@NotBlank
	private String teamA;
	@ApiModelProperty(required = true)
	@NotNull
	@NotBlank
	private String teamB;
	@ApiModelProperty(required = true)
	@NotNull
	private SportType sport;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(LocalDateTime matchDate) {
		this.matchDate = matchDate;
	}

	public String getTeamA() {
		return teamA;
	}

	public void setTeamA(String teamA) {
		this.teamA = teamA;
	}

	public String getTeamB() {
		return teamB;
	}

	public void setTeamB(String teamB) {
		this.teamB = teamB;
	}

	public SportType getSport() {
		return sport;
	}

	public void setSport(SportType sport) {
		this.sport = sport;
	}

	@Override
	public String toString() {
		return "MatchDTO{" +
				"id=" + id +
				", description='" + description + '\'' +
				", matchDate=" + matchDate +
				", teamA='" + teamA + '\'' +
				", teamB='" + teamB + '\'' +
				", sport=" + sport +
				'}';
	}
}
