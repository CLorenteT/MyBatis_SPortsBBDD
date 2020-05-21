
public class Sports {
	private int id = 0;
	private String name = "";
	
	public Sports(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Sports(int id) {
		super();
		this.id = id;
	}

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }
	
}