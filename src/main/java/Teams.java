
public class Teams {
	private int id = 0;
	private String name = "";
	private int nationality = 0;
	private String gender = "";
	
	public Teams(int id, String name, int nationality, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.nationality = nationality;
		this.gender = gender;	
	}
	
	public Teams(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Teams(int id) {
		super();
		this.id = id;
	}

	public int getId() { return id; }
	
	public void setId(int id) { this.id = id; }
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	
	public int getNationality() { return nationality; }
	
	public void setNationality(int nationality) { this.nationality = nationality; }
	
	public String getGender() { return gender; }
	
	public void setGender(String gender) { this.gender = gender; }
	
}
