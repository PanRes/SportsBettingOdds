package gr.pr.accepted.sportbettingodds.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.math.NumberUtils;

public enum SportType {

	FOOTBALL("Football"),
	BASKETBALL("Basketball");

	private String name;

	SportType(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static SportType forValues(@JsonProperty("sportType") String sportType) {
		for (SportType value : SportType.values()) {
			if (value.getName().equalsIgnoreCase(sportType) || NumberUtils.isParsable(sportType) && value.ordinal() + 1 == Integer.parseInt(sportType)) {
				return value;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return name;
	}
}
