import java.util.Date;

public class Players extends People{
	private int dorsal;
	private String name;
	private String surname1;
	private String surname2;
	private int age;
	private String sex;
	private int nationality;
	private int competitions_wins;
	private int salary;
	private int type;
	private Date debut;
	private Date retired;
	private int team;
	private int sport;

	// PRINCIPAL CONSTRUCTOR
	public Players(String dNI, int dorsal, String name, String surname1, String surname2, int age, String sex,
				   int nationality, int competitions_wins, int salary, int type, Date debut, Date retired, int sport,
				   int team) {
		super();
		DNI = dNI;
		this.dorsal = dorsal;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.age = age;
		this.sex = sex;
		this.nationality = nationality;
		this.competitions_wins = competitions_wins;
		this.salary = salary;
		this.type = type;
		this.debut = debut;
		this.retired = retired;
		this.team = team;
		this.sport = sport;
	}

	public Players(String DNI, int dorsal, String name, String surname1, String surname2, int age, String sex, int nationality, int competitions_wins, int salary, int type, Date debut, Date retired) {
		this.DNI = DNI;
		this.dorsal = dorsal;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.age = age;
		this.sex = sex;
		this.nationality = nationality;
		this.competitions_wins = competitions_wins;
		this.salary = salary;
		this.type = type;
		this.debut = debut;
		this.retired = retired;
	}

	// PLAYERS
	public Players(String dNI, int dorsal, String name, String surname1, String surname2, int age, String sex, int nationality,
				   int competitions_wins, int salary, int type) {
		super();
		DNI = dNI;
		this.dorsal = dorsal;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.age = age;
		this.sex = sex;
		this.nationality = nationality;
		this.competitions_wins = competitions_wins;
		this.salary = salary;
		this.type = type;
	}

	public Players(String DNI, int dorsal, String name, String surname1, String surname2, int age, String sex, int nationality, int competitions_wins, int salary, Date debut, Date retired, int team, int sport) {
		this.DNI = DNI;
		this.dorsal = dorsal;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.age = age;
		this.sex = sex;
		this.nationality = nationality;
		this.competitions_wins = competitions_wins;
		this.salary = salary;
		this.debut = debut;
		this.retired = retired;
		this.team = team;
		this.sport = sport;
	}



	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getNationality() {
		return nationality;
	}

	public void setNationality(int nationality) {
		this.nationality = nationality;
	}

	public int getCompetitions_wins() {
		return competitions_wins;
	}

	public void setCompetitions_wins(int competitions_wins) {
		this.competitions_wins = competitions_wins;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getRetired() {
		return retired;
	}

	public void setRetired(Date retired) {
		this.retired = retired;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public int getSport() {
		return sport;
	}

	public void setSport(int sport) {
		this.sport = sport;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

}