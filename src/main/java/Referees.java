public class Referees extends People {
    private String name;
    private String surname1;
    private String surname2;
    private int sport;
    private int comp;
    private String sex;
    private int age;
    private int nat;
    private String ref_type;
    private int salary;
    private int type;

    public Referees(String DNI, String name, String surname1, String surname2, int sport, int comp, int age, String sex, int nat, String ref_type, int salary, int type) {
        this.DNI = DNI;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.sport = sport;
        this.comp = comp;
        this.age = age;
        this.sex = sex;
        this.nat = nat;
        this.ref_type = ref_type;
        this.salary = salary;
        this.type = type;
    }

    public Referees(String DNI, String name, String surname1, String surname2, int sport, int comp, int age, String sex, int nat, String ref_type, int salary) {
        this.DNI = DNI;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.sport = sport;
        this.comp = comp;
        this.age = age;
        this.sex = sex;
        this.nat = nat;
        this.ref_type = ref_type;
        this.salary = salary;
    }

    public Referees(String DNI, String name, String surname1, String surname2, int age, String sex, int nat, String ref_type, int salary, int type) {
        this.DNI = DNI;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.age = age;
        this.sex = sex;
        this.nat = nat;
        this.ref_type = ref_type;
        this.salary = salary;
        this.type = type;
    }

    @Override
    public String getDNI() {
        return DNI;
    }

    @Override
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

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }

    public int getComp() {
        return comp;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNat() {
        return nat;
    }

    public void setNat(int nat) {
        this.nat = nat;
    }

    public String getRef_type() {
        return ref_type;
    }

    public void setRef_type(String ref_type) {
        this.ref_type = ref_type;
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
}
