import org.apache.ibatis.annotations.*;

public interface Mapper {
	// PEOPLE
	@Select({ "SELECT a.DNI, a.Dorsals, a.Name_, Surname1, b.Surname2, Age, Sex, ID_Nationality, Competitions_Wins, Salary, ID_Type FROM Players a INNER JOIN Surname2_Player b ON a.DNI = b.DNI WHERE a.DNI=#{DNI}" })
	People getPerson(String DNI);
	
	@Select({ "SELECT * FROM Nationalities WHERE ID_Nationality=#{id}" })
	Nationalities getNationality(int id);

	@Insert({
			"call InsertPlayers(#{DNI},#{dorsals},#{name},#{surname1},#{surname2},#{age},#{sex},#{nationality},#{competitions_wins},#{salary},#{type},#{debut},#{team},#{sport},#{retired});" })
	void insertPerson(People data);

	@Update({ "UPDATE Players SET Name_=#{name} WHERE DNI=#{DNI}" })
	void updatePerson(People data);

	@Delete({ "DELETE FROM Players WHERE DNI=#{DNI}" })
	void deletePerson(People data);

	
	// TEAMS
	@Select({ "SELECT * FROM Teams WHERE ID_Team=#{id}" })
	Teams getTeam(int id);

	@Insert({ "INSERT INTO Teams VALUES (#{id},#{name},#{nationality},#{gender});" })
	void insertTeam(Teams data);

	@Update({ "UPDATE Teams SET Name_=#{name} WHERE ID_Team=#{id}" })
	void updateTeam(Teams data);

	@Delete({ "DELETE FROM Teams WHERE ID_Team=#{id}" })
	void deleteTeam(Teams data);

	
	// SPORTS
	@Select({ "SELECT * FROM Sports WHERE ID_Sport=#{id}" })
	Sports getSport(int id);

	@Insert({ "INSERT INTO Sports VALUES (#{id},#{name});" })
	void insertSport(Sports data);

	@Update({ "UPDATE Sports SET Name_=#{name} WHERE ID_Sport=#{id}" })
	void updateSport(Sports data);

	@Delete({ "DELETE FROM Sports WHERE ID_Sport=#{id}" })
	void deleteSport(Sports data);
}
