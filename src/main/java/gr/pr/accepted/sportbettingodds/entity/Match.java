package gr.pr.accepted.sportbettingodds.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Table(name = "match", indexes = {
		@Index(name = "idx_match", columnList = "match_date, team_a, team_b, sport", unique = true)
})
@Entity
public class Match {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "description")
	private String description;

	@Column(name = "match_date", nullable = false)
	private LocalDateTime matchDate;

	@Column(name = "team_a", nullable = false, length = 50)
	private String teamA;

	@Column(name = "team_b", nullable = false, length = 50)
	private String teamB;

	@Column(name = "sport", nullable = false)
	private Integer sport;

	@OneToMany(mappedBy = "match",cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MatchOdd> matchOdds;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Integer getSport() {
		return sport;
	}

	public void setSport(Integer sport) {
		this.sport = sport;
	}

	public String getTeamB() {
		return teamB;
	}

	public void setTeamB(String teamB) {
		this.teamB = teamB;
	}

	public String getTeamA() {
		return teamA;
	}

	public void setTeamA(String teamA) {
		this.teamA = teamA;
	}

	public LocalDateTime getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(LocalDateTime matchDate) {
		this.matchDate = matchDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<MatchOdd> getMatchOdds() {
		return matchOdds;
	}

	public void setMatchOdds(Set<MatchOdd> matchOdds) {
		this.matchOdds = matchOdds;
	}

	@Override
	public String toString() {
		return "Match{" +
				"id=" + id +
				", description='" + description + "'" +
				", matchDate=" + matchDate +
				", teamA='" + teamA + "'" +
				", teamB='" + teamB + "'" +
				", sport=" + sport +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Match)) return false;
		Match match = (Match) o;
		return Objects.equals(getId(), match.getId()) && Objects.equals(getDescription(), match.getDescription()) && Objects.equals(getMatchDate(), match.getMatchDate()) && Objects.equals(getTeamA(), match.getTeamA()) && Objects.equals(getTeamB(), match.getTeamB()) && Objects.equals(getSport(), match.getSport());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getDescription(), getMatchDate(), getTeamA(), getTeamB(), getSport());
	}
}
