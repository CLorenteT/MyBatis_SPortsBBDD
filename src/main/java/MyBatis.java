import java.io.Reader;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

public class MyBatis {
	public static SqlSessionFactory sqlMapper;

	static {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis.xml");
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			throw new RuntimeException("No se ha podido acceder a la bbdd" + e, e);
		}
	}

	public static void main(String[] args) throws ParseException {
		// OPEN SESSION
		SqlSession session = sqlMapper.openSession();
		Scanner sc = new Scanner(System.in);

		sqlMapper.getConfiguration().addMapper(Mapper.class);
		Mapper mapper = session.getMapper(Mapper.class);

		System.out.print("Enter the action you want to realice [select, insert, modify or delete]: ");
		String action = sc.nextLine();

		if (action.equalsIgnoreCase("select")) {
			System.out.print("Enter the group [people, teams or sports]: ");
			String group1 = sc.nextLine();

			if (group1.equals("people")) {
				System.out.print("Insert DNI of player: ");
				People p = mapper.getPerson(sc.nextLine());
				Nationalities n = mapper.getNationality(p.getNationality());
				System.out.printf(
						"+-------------+----------+-------------------+-------------------+-------------------+----------+-------+------------------------+\n|     DNI     |  DORSAL  |       NAME        |   FIRST SURNAME   |  SECOND SURNAME   |  GENDER  |  AGE  |      NATIONALITY       |\n+-------------+----------+-------------------+-------------------+-------------------+----------+-------+------------------------+\n|  %9s  |    %2d    |  %15s  |  %15s  |  %15s  |  %6s  |   %2s  |  %20s  |\n+-------------+----------+-------------------+-------------------+-------------------+----------+-------+------------------------+",
						p.getDNI(), p.getDorsals(), p.getName(), p.getSurname1(), p.getSurname2(), p.getSex(), p.getAge(), n.getName());
			} else if (group1.equals("teams")) {
				System.out.print("Insert ID of team: ");
				Teams t = mapper.getTeam(sc.nextInt());
				System.out.println("| ID Team: " + t.getId() + " | Name: " + t.getName() + " | ID_Nationality: "
						+ t.getNationality() + " | Gender: " + t.getGender() + " |");
			} else if (group1.equals("sports")) {
				System.out.print("Insert ID of sport: ");
				Sports s = mapper.getSport(sc.nextInt());
				System.out.println("| ID Sport: " + s.getId() + " | Name: " + s.getName() + " |");
			} else
				System.out.println("Incorrect group.");
		} else if (action.equals("insert")) {
			System.out.print("Enter the group [people, teams or sports]: ");
			String group2 = sc.nextLine();

			if (group2.equals("people")) {
				// PLAYER
				System.out.print("DNI: ");
				String dni = sc.nextLine();
				System.out.print("Dorsal: ");
				int dorsal = sc.nextInt();
				System.out.println("--");
				String alert = sc.nextLine();
				System.out.print("Name: ");
				String name = sc.nextLine();
				System.out.print("Surname: ");
				String surname = sc.nextLine();
				System.out.print("Surname2: ");
				String surname2 = sc.nextLine();
				System.out.print("Age: ");
				int age = sc.nextInt();
				System.out.println("--");
				String alert1 = sc.nextLine();
				System.out.print("Gender: ");
				String sex = sc.nextLine();
				System.out.print("ID_Nationality: ");
				int nat = sc.nextInt();
				System.out.print("Competitions Wins: ");
				int comp = sc.nextInt();
				System.out.print("Salary: ");
				int salary = sc.nextInt();
				System.out.print("ID_Type: ");
				int type = sc.nextInt();
				System.out.println("DATES:");
				String dates = sc.nextLine();
				// DEBUT
				System.out.println("Debut date (YYYY/MM/DD): ");
				String entradaDeb = sc.nextLine();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date debut = format.parse(entradaDeb);
				// RETIRED
				System.out.println("Retired date (YYYY/MM/DD): ");
				String entradaRet = sc.nextLine();
				Date retired = format.parse(entradaRet);
				// SPORT
				System.out.println("Sport ID: ");
				int sport = sc.nextInt();
				// TEAM
				System.out.println("Team ID: ");
				int team = sc.nextInt();
				People insP = new People(dni, dorsal, name, surname, surname2, age, sex, nat, comp, salary, type, debut, retired, sport, team);
				mapper.insertPerson(insP);

				System.out.println("Person successfully added!");
			} else if (group2.equals("teams")) {
				System.out.print("ID: ");
				int id = sc.nextInt();
				System.out.println("--");
				String alert = sc.nextLine();
				System.out.print("Name: ");
				String name = sc.nextLine();
				System.out.print("ID_Nationality: ");
				int nat = sc.nextInt();
				System.out.println("--");
				String alert2 = sc.nextLine();
				System.out.print("Gender: ");
				String sex = sc.nextLine();
				Teams insT = new Teams(id, name, nat, sex);
				mapper.insertTeam(insT);
				System.out.println("Team successfully added!");
			} else if (group2.equals("sports")) {
				System.out.print("ID: ");
				int id = sc.nextInt();
				System.out.print("Name: ");
				String name = sc.nextLine();
				Sports insS = new Sports(id, name);
				mapper.insertSport(insS);
				System.out.println("Sport successfully added!");
			} else
				System.out.println("Incorrect group.");
		} else if (action.equals("modify")) {
			System.out.print("Enter the group [people, teams or sports]: ");
			String group3 = sc.nextLine();

			if (group3.equals("people")) {
				System.out.print("Insert name: ");
				String nameP = sc.nextLine();
				People updP = new People("48584159A", nameP);
				mapper.updatePerson(updP);
				System.out.println("Person successfully modified!");
			} else if (group3.equals("teams")) {
				System.out.print("Insert name: ");
				String nameT = sc.nextLine();
				Teams updT = new Teams(2, nameT);
				mapper.updateTeam(updT);
				System.out.println("Team successfully modified!");
			} else if (group3.equals("sports")) {
				System.out.println("Insert name: ");
				String nameS = sc.nextLine();
				Sports updS = new Sports(2, nameS);
				mapper.updateSport(updS);
				System.out.println("Sport successfully modified!");
			} else
				System.out.println("Incorrect group.");
		} else if (action.equals("delete")) {
			System.out.print("Enter the group [people, teams or sports]: ");
			String group4 = sc.nextLine();

			if (group4.equals("people")) {
				People delP = new People("48584159A");
				mapper.deletePerson(delP);
				System.out.println("Person successfully deleted!");
			} else if (group4.equals("teams")) {
				Teams delT = new Teams(1);
				mapper.deleteTeam(delT);
				System.out.println("Team successfully deleted!");
			} else if (group4.equals("sports")) {
				Sports delS = new Sports(1);
				mapper.deleteSport(delS);
				System.out.println("Sport successfully deleted!");
			} else
				System.out.println("Incorrect group.");
		} else
			System.out.println("Incorrect action.");

		// CLOSE SESSION
		session.commit();
		session.close();

	}

}