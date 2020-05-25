public class Coaches extends People {
    private String name;
    private String surname1;
    private String surname2;
    private String sex;
    private int nat;
    private int salary;
    private int team;
    private int type;
    private int sport;

    public Coaches(String DNI, String name, String surname1, String surname2, String sex, int nat, int salary, int team, int type, int sport) {
        this.DNI = DNI;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.sex = sex;
        this.nat = nat;
        this.salary = salary;
        this.team = team;
        this.type = type;
        this.sport = sport;
    }

    public Coaches(String DNI, String name, String surname1, String surname2, String sex, int nat, int salary) {
        this.DNI = DNI;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.sex = sex;
        this.nat = nat;
        this.salary = salary;
    }

    public Coaches(String DNI, String name, String surname1, String surname2, String sex, int nat, int salary, int type) {
        this.DNI = DNI;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.sex = sex;
        this.nat = nat;
        this.salary = salary;
        this.type = type;
    }

    @Override
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getNat() {
        return nat;
    }

    public void setNat(int nat) {
        this.nat = nat;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }
}
