package com.volleyservice;




import com.volleyservice.entity.BVBTeam;
import com.volleyservice.entity.Match;
import com.volleyservice.entity.MatchSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.util.TestPropertyValues;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class VolleyServiceApplicationTests {

	@Test
	void shouldReturnTeamThatWonASet() {
//		given
		BVBTeam teamA = new BVBTeam("Winning Team");
		BVBTeam teamB = new BVBTeam("Loosing Team");
		MatchSet set = new MatchSet(21, teamA, 21, teamB, 19);

//		when
		Optional<BVBTeam> result = set.getWinner();

//		then
		assertEquals(Optional.of(teamA), result);
	}

	@Test
	void shouldReturnOptionalEmptySinceScoreIsUnderFinishPoint() {
//		given
		BVBTeam teamA = new BVBTeam("TeamA");
		BVBTeam teamB = new BVBTeam("TeamB");
		MatchSet set = new MatchSet(21, teamA, 15, teamB, 10);

//		when
		Optional<BVBTeam> result = set.getWinner();

//		then
		assertEquals(Optional.empty(), result);
	}

	@Test
	void whyItsWorkLikeThis() {
//		given
		BVBTeam teamA = new BVBTeam("TeamA");
		BVBTeam teamB = new BVBTeam("TeamB");
		MatchSet set1 = new MatchSet(21, teamA, 21, teamB, 19);
		MatchSet set2 = new MatchSet(21, teamA, 19, teamB, 21);
		MatchSet set3 = new MatchSet(15, teamA, 15, teamB, 6);

		Match match1 = new Match(List.of(teamA, teamB), List.of(set1, set2, set3));
		Match match2 = new Match(List.of(teamA, teamB), List.of(set1, set2));

//		assertEquals(match1, match2);// dlaczego jak tworze obiekt match2 to match1 staje sie meczem2?
//		dzialo sie tak dlatego, ze atrybut sets klasy Match byl "static"
	}

	@Test
	void shouldReturnTeamThatWonAMatch() {
//		given
		BVBTeam teamA = new BVBTeam("TeamA");
		BVBTeam teamB = new BVBTeam("TeamB");
		MatchSet set1 = new MatchSet(21, teamA, 21, teamB, 19);
		MatchSet set2 = new MatchSet(21, teamA, 19, teamB, 21);
		MatchSet set3 = new MatchSet(15, teamA, 15, teamB, 6);

		Match match1 = new Match(List.of(teamA, teamB), List.of(set1, set2, set3));

//		when
		Optional<BVBTeam> result1 = match1.getWinner();

//		then
		assertEquals(Optional.of(teamA), result1);

	}

	@Test
	void shouldReturnOptionalEmptySinceNotEnoughSetsToWin() {
//		given
		BVBTeam teamA = new BVBTeam("TeamA");
		BVBTeam teamB = new BVBTeam("TeamB");
		MatchSet set1 = new MatchSet(21, teamA, 21, teamB, 19);
		MatchSet set2 = new MatchSet(21, teamA, 19, teamB, 21);
		MatchSet set3 = new MatchSet(15, teamA, 10, teamB, 6);

		Match match = new Match(List.of(teamA, teamB), List.of(set1, set2, set3));

//		when
		Optional<BVBTeam> result = match.getWinner();

//		then
		assertEquals(Optional.empty(), result);

	}

}
