CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE match
(
    id uuid CONSTRAINT match_pk PRIMARY KEY,
    description text,
    match_date timestamp NOT NULL,
    team_a varchar(50) NOT NULL,
    team_b varchar(50) NOT NULL CHECK (match.team_b <> match.team_a),
    sport smallint NOT NULL CHECK (sport >= 0 or sport < 2 )
);

CREATE UNIQUE INDEX idx_match ON match(match_date, team_a, team_b, sport);

CREATE TABLE match_odds
(
    id uuid CONSTRAINT match_odds_pk PRIMARY KEY,
    match_id uuid NOT NULL,
    specifier varchar(1) NOT NULL,
    odds decimal NOT NULL CHECK (odds > 0),
    CONSTRAINT fk_match FOREIGN KEY(match_id) REFERENCES match
);

CREATE UNIQUE INDEX idx_match_odd ON match_odds(match_id, specifier);
