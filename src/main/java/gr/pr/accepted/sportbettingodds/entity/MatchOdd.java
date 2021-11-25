package gr.pr.accepted.sportbettingodds.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Table(name = "match_odds", indexes = {
		@Index(name = "idx_match_odd", columnList = "match_id, specifier", unique = true)
})
@Entity
public class MatchOdd {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", nullable = false)
	private UUID id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "match_id", nullable = false)
	private Match match;

	@Column(name = "specifier", nullable = false, length = 1)
	private String specifier;

	@Column(name = "odds", nullable = false)
	private Double odds;

	public MatchOdd() {
	}

	public MatchOdd(Match match, String specifier, Double odds) {
		this.match = match;
		this.specifier = specifier;
		this.odds = odds;
	}

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

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@Override
	public String toString() {
		return "MatchOdd{" +
				"id=" + id +
				", match=" + match +
				", specifier='" + specifier + "'" +
				", odds=" + odds +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MatchOdd)) return false;
		MatchOdd matchOdd = (MatchOdd) o;
		return Objects.equals(getId(), matchOdd.getId()) && Objects.equals(getMatch(), matchOdd.getMatch()) && Objects.equals(getSpecifier(), matchOdd.getSpecifier()) && Objects.equals(getOdds(), matchOdd.getOdds());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getMatch(), getSpecifier(), getOdds());
	}
}
