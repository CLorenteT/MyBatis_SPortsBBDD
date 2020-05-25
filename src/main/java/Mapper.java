import org.apache.ibatis.annotations.*;

public interface Mapper {
	// SELECT
		// PEOPLE
			// PLAYER
			@Select({
					"call selPlayers(#{DNI});"
			})
			Players getPerson(String DNI);

			// REFEREE
			@Select({
					"call selReferees(#{DNI});"
			})
			Referees getReferee(String DNI);

			// COACH
			@Select({
					"call selCoaches(#{DNI});"
			})
			Coaches getCoach(String DNI);

		// TEAMS
		@Select({
				"SELECT * FROM Teams WHERE ID_Team=#{id};"
		})
		Teams getTeam(int id);

		// SPORTS
		@Select({
				"SELECT * FROM Sports WHERE ID_Sport=#{id};"
		})
		Sports getSport(int id);

		// NATIONALITIY
		@Select({
				"SELECT * FROM Nationalities WHERE ID_Nationality=#{id};"
		})
		Nationalities getNationality(int id);

	// INSERT
		// PEOPLE
			// PLAYER
			@Insert({
					"call InsertPlayers(#{DNI},#{dorsal},#{name},#{surname1},#{surname2},#{age},#{sex},#{nationality},#{competitions_wins},#{salary},#{type},#{debut},#{team},#{sport},#{retired});"
			})
			void insertPerson(Players data);

			// COACH
			@Insert({
					"call InsertCoach(#{DNI}, #{name}, #{surname1}, #{surname2}, #{sex}, #{nat}, #{salary}, #{team}, #{type}, #{sport});"
			})
			void insertCoach(Coaches data);

			// REFEREE
			@Insert({
					"call InsertReferee(#{DNI}, #{name}, #{surname1}, #{surname2}, #{sport}, #{comp}, #{age}, #{sex}, #{nat}, #{ref_type}, #{salary}, #{type});"
			})
			void insertReferee(Referees data);


		// TEAMS
		@Insert({
				"call InsertTeam(#{name},#{nationality},#{gender});"
		})
		void insertTeam(Teams data);

		// SPORTS
		@Insert({
				"INSERT INTO Sports(Name_) VALUES (#{name});"
		})
		void insertSport(Sports data);

	// UPDATE
		// PEOPLE
			// PLAYER
			@Update({
					"call upPerson(#{DNI},#{dorsal},#{name},#{surname1},#{surname2},#{age},#{sex},#{nationality},#{competitions_wins},#{salary},#{debut},#{retired},#{team},#{sport});"
			})
			void updatePlayer(Players data);

			// COACH
			@Update({
					"call upCoach(#{DNI}, #{name}, #{surname1}, #{surname2}, #{sex}, #{nat}, #{salary});"
			})
			void updateCoach(Coaches data);

			// REFEREE
			@Update({
					"call upReferee(#{DNI}, #{name}, #{surname1}, #{surname2}, #{sport}, #{comp}, #{age}, #{sex}, #{nat}, #{ref_type}, #{salary});"
			})
			void updateReferee(Referees data);

		// TEAMS
		@Update({
				"call upTeam(#{id},#{name},#{nationality},#{gender});"
		})
		void updateTeam(Teams data);

		// SPORTS
		@Update({
				"call upSport(#{id}, #{name});"
		})
		void updateSport(Sports data);

	// DELETE
		// PEOPLE
		@Delete({
				"call delPerson(#{DNI},#{name});"
		})
		void deletePerson(People data);

		// TEAMS
		@Delete({
				"call delTeam(#{id});"
		})
		void deleteTeam(int id);

		// SPORTS
		@Delete({
				"call delSport(#{id});"
		})
		void deleteSport(int id);

}
