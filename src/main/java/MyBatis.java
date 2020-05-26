import java.io.Reader;
import java.text.SimpleDateFormat;
import java.sql.Date;
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

    public static void main(String[] args) throws Exception {
        // OPEN SESSION
        SqlSession session = sqlMapper.openSession();
        Scanner sc = new Scanner(System.in);

        sqlMapper.getConfiguration().addMapper(Mapper.class);
        Mapper mapper = session.getMapper(Mapper.class);

        boolean keep = true;

        while (keep) {
            System.out.print("Enter the action you want to realice [select, insert, modify or delete]: ");
            String action = sc.next();

            if (action.equalsIgnoreCase("select")) {
                System.out.print("Enter the group [people, teams or sports]: ");
                String group = sc.next();

                if (group.equals("people")) {
                    System.out.print("Type of person [player, coach, referee]: ");
                    String people = sc.next();

                    if (people.equals("player")) {
                        System.out.print("Insert DNI of player: ");
                        Players p = mapper.getPerson(sc.next());
                        if (p != null) {
                            if (p.getSurname2() == null) p.setSurname2("");
                            Nationalities n = mapper.getNationality(p.getNationality());
                            if (p.getRetired() == null) p.setRetired(Date.valueOf("9999-01-01"));
                            System.out.printf(
                                    "+-------------+----------+-------------------+-------------------+-------------------+----------+-------+------------------------+--------------+-------------+--------+---------------------------------+---------------------------------+\n|     DNI     |  DORSAL  |       NAME        |   FIRST SURNAME   |  SECOND SURNAME   |  GENDER  |  AGE  |      NATIONALITY       |  COMP. WINS  |   SALARY    |  TYPE  |              DEBUT              |             RETIRED             |\n+-------------+----------+-------------------+-------------------+-------------------+----------+-------+------------------------+--------------+-------------+--------+---------------------------------+---------------------------------+\n|  %9s  |    %2d    |  %15s  |  %15s  |  %15s  |  %6s  |   %2s  |  %20s  |  %10d  |  %9d  |  %4d  |  %29s  |  %29s  |\n+-------------+----------+-------------------+-------------------+-------------------+----------+-------+------------------------+--------------+-------------+--------+---------------------------------+---------------------------------+\n",
                                    p.getDNI(), p.getDorsal(), p.getName(), p.getSurname1(), p.getSurname2(), p.getSex(), p.getAge(), n.getName(), p.getCompetitions_wins(), p.getSalary(), p.getType(), p.getDebut(), p.getRetired());

                        } else
                            System.out.println("The player's DNI is incorrect or doesn't exists.");

                    } else if (people.equals("coach")) {
                        System.out.print("Insert DNI of coach: ");
                        Coaches c = mapper.getCoach(sc.next());
                        if (c != null) {
                            if (c.getSurname2() == null) c.setSurname2("");
                            Nationalities n = mapper.getNationality(c.getNat());
                            System.out.printf(
                                    "+-------------+-------------------+-------------------+-------------------+----------+------------------------+-------------+--------+\n|     DNI     |       NAME        |   FIRST SURNAME   |  SECOND SURNAME   |  GENDER  |      NATIONALITY       |   SALARY    |  TYPE  |\n+-------------+-------------------+-------------------+-------------------+----------+------------------------+-------------+--------+\n|  %9s  |  %15s  |  %15s  |  %15s  |  %6s  |  %20s  |  %9d  |  %4d  |\n+-------------+-------------------+-------------------+-------------------+----------+------------------------+-------------+--------+\n",
                                    c.getDNI(), c.getName(), c.getSurname1(), c.getSurname2(), c.getSex(), n.getName(), c.getSalary(), c.getType());

                        } else
                            System.out.println("The coach's DNI is incorrect or doesn't exists.");

                    } else if (people.equals("referee")) {
                        System.out.print("Insert DNI of referee: ");
                        Referees r = mapper.getReferee(sc.next());
                        if (r != null) {
                            if (r.getSurname2() == null) r.setSurname2("");
                            Nationalities n = mapper.getNationality(r.getNat());
                            System.out.printf(
                                    "+-------------+-------------------+-------------------+-------------------+----------+-------+------------------------+------------------------+-------------+--------+\n|     DNI     |       NAME        |   FIRST SURNAME   |  SECOND SURNAME   |  GENDER  |  AGE  |      NATIONALITY       |      REFEREE TYPE      |   SALARY    |  TYPE  |\n+-------------+-------------------+-------------------+-------------------+----------+-------+------------------------+------------------------+-------------+--------+\n|  %9s  |  %15s  |  %15s  |  %15s  |  %6s  |   %2s  |  %20s  |  %20s  |  %9d  |  %4d  |\n+-------------+-------------------+-------------------+-------------------+----------+-------+------------------------+------------------------+-------------+--------+\n",
                                    r.getDNI(), r.getName(), r.getSurname1(), r.getSurname2(), r.getSex(), r.getAge(), n.getName(), r.getRef_type(), r.getSalary(), r.getType());

                        } else
                            System.out.println("The referee's DNI is incorrect or doesn't exists.");

                    } else
                        System.out.println("Unknown type of person");

                } else if (group.equals("teams")) {
                    System.out.print("Insert ID of team: ");
                    Teams t = mapper.getTeam(sc.nextInt());
                    if (t != null) {
                        Nationalities n = mapper.getNationality(t.getNationality());
                        System.out.printf(
                                "+--------+-----------------------------+------------------------+----------+\n|   ID   |            NAME             |      NATIONALITY       |  GENDER  |\n+--------+-----------------------------+------------------------+----------+\n|  %4d  |  %25s  |  %20s  |  %6s  |\n+--------+-----------------------------+------------------------+----------+\n",
                                t.getId(), t.getName(), n.getName(), t.getGender());

                    } else
                        System.out.println("The team id is incorrect or doesn't exists.");

                } else if (group.equals("sports")) {
                    int cont = 1;
                    boolean next = true;
                    while (next) {
                        Sports s = mapper.getSport(cont);
                        if (s == null) {
                            next = false;
                        }
                        if (cont == 1 && next) {
                            System.out.printf(
                                    "+--------+-------------------+\n|   ID   |       NAME        |\n+--------+-------------------+\n|  %4d  |  %15s  |\n",
                                    s.getId(), s.getName());
                        } else if (cont != 1 && next)
                            System.out.printf(
                                    "+--------+-------------------+\n|  %4d  |  %15s  |\n",
                                    s.getId(), s.getName());
                        cont++;
                    }
                    System.out.print("+--------+-------------------+\n");

                } else
                    System.out.println("Incorrect group.");

            } else if (action.equals("insert")) {
                System.out.print("Enter the group [people, teams or sports]: ");
                String group = sc.next();

                if (group.equals("people")) {
                    System.out.print("Type of person [player, coach, referee]: ");
                    String people = sc.next();

                    if (people.equals("player")) {
                        System.out.print("DNI: ");
                        String dni = sc.next();
                        System.out.print("Dorsal: ");
                        int dorsal = sc.nextInt();
                        System.out.print("Name: ");
                        String name = sc.next();
                        System.out.print("Surname: ");
                        String surname = sc.next();
                        System.out.print("Surname2: ");
                        String surname2 = sc.next();
                        System.out.print("Age: ");
                        int age = sc.nextInt();
                        System.out.print("Gender: ");
                        String sex = sc.next();
                        System.out.print("ID_Nationality: ");
                        int nat = sc.nextInt();
                        System.out.print("Competitions Wins: ");
                        int comp = sc.nextInt();
                        System.out.print("Salary: ");
                        int salary = sc.nextInt();
                        System.out.print("ID_Type: ");
                        int type = sc.nextInt();
                        System.out.println("Debut date (YYYY-MM-DD): ");
                        String debutDate = sc.next();
                        Date debut = Date.valueOf(debutDate);
                        System.out.println("Retired date (YYYY-MM-DD): ");
                        String retiredDate = sc.next();
                        Date retired = Date.valueOf(retiredDate);
                        System.out.println("Sport ID: ");
                        int sport = sc.nextInt();
                        System.out.println("Team ID: ");
                        int team = sc.nextInt();

                        Players insP = new Players(dni, dorsal, name, surname, surname2, age, sex, nat, comp, salary, type, debut, retired, sport, team);
                        mapper.insertPerson(insP);
                        System.out.println("Player successfully added!");

                    } else if (people.equals("coach")) {
                        System.out.println("DNI: ");
                        String dni = sc.next();
                        System.out.println("Name: ");
                        String name = sc.next();
                        System.out.println("First surname: ");
                        String surname1 = sc.next();
                        System.out.println("Last surname: ");
                        String surname2 = sc.next();
                        System.out.println("Gender: ");
                        String sex = sc.next();
                        System.out.println("ID_Nationality: ");
                        int nat = sc.nextInt();
                        System.out.println("Salary: ");
                        int salary = sc.nextInt();
                        System.out.println("Team ID: ");
                        int team = sc.nextInt();
                        System.out.println("Type: ");
                        int type = sc.nextInt();
                        System.out.println("Sport ID: ");
                        int sport = sc.nextInt();

                        Coaches c = new Coaches(dni, name, surname1, surname2, sex, nat, salary, team, type, sport);
                        mapper.insertCoach(c);
                        System.out.println("Coach successfully added!");

                    } else if (people.equals("referee")) {
                        System.out.println("DNI: ");
                        String dni = sc.next();
                        System.out.println("Name: ");
                        String name = sc.next();
                        System.out.println("First surname: ");
                        String surname1 = sc.next();
                        System.out.println("Last surname: ");
                        String surname2 = sc.next();
                        System.out.println("Sport ID: ");
                        int sport = sc.nextInt();
                        System.out.println("Competition ID: ");
                        int comp = sc.nextInt();
                        System.out.println("Age: ");
                        int age = sc.nextInt();
                        System.out.println("Gender: ");
                        String sex = sc.next();
                        System.out.println("ID_Nationality: ");
                        int nat = sc.nextInt();
                        System.out.println("Referee Type: ");
                        String ref_type = sc.next();
                        System.out.println("Salary: ");
                        int salary = sc.nextInt();
                        System.out.println("Type: ");
                        int type = sc.nextInt();

                        Referees r = new Referees(dni, name, surname1, surname2, sport, comp, age, sex, nat, ref_type, salary, type);
                        mapper.insertReferee(r);
                        System.out.println("Referee successfully added!");

                    } else
                        System.out.println("Unknown type of person");

                } else if (group.equals("teams")) {
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("ID_Nationality: ");
                    int nat = sc.nextInt();
                    System.out.print("Gender: ");
                    String sex = sc.next();

                    Teams insT = new Teams(name, nat, sex);
                    mapper.insertTeam(insT);
                    System.out.println("Team successfully added!");

                } else if (group.equals("sports")) {
                    System.out.print("Name: ");
                    String name = sc.next();

                    Sports insS = new Sports(name);
                    mapper.insertSport(insS);
                    System.out.println("Sport successfully added!");

                } else
                    System.out.println("Incorrect group.");

            } else if (action.equals("modify")) {
                System.out.print("Enter the group [people, teams or sports]: ");
                String group = sc.next();

                if (group.equals("people")) {
                    System.out.print("Type of person [player, coach, referee]: ");
                    String people = sc.next();

                    if (people.equals("player")) {
                        System.out.print("Insert player's DNI: ");
                        Players p = mapper.getPerson(sc.next());
                        if (p != null) {
                            if (p.getSurname2() == null) p.setSurname2("");
                            if (p.getRetired() == null) p.setRetired(Date.valueOf("9999-01-01"));
                            Nationalities n = mapper.getNationality(p.getNationality());
                            System.out.printf(
                                    "+-------------+----------+-------------------+-------------------+-------------------+----------+-------+------------------------+--------------+-------------+--------+---------------------------------+---------------------------------+\n|     DNI     |  DORSAL  |       NAME        |   FIRST SURNAME   |  SECOND SURNAME   |  GENDER  |  AGE  |      NATIONALITY       |  COMP. WINS  |   SALARY    |  TYPE  |              DEBUT              |             RETIRED             |\n+-------------+----------+-------------------+-------------------+-------------------+----------+-------+------------------------+--------------+-------------+--------+---------------------------------+---------------------------------+\n|  %9s  |    %2d    |  %15s  |  %15s  |  %15s  |  %6s  |   %2s  |  %20s  |  %10d  |  %9d  |  %4d  |  %29s  |  %29s  |\n+-------------+----------+-------------------+-------------------+-------------------+----------+-------+------------------------+--------------+-------------+--------+---------------------------------+---------------------------------+\n",
                                    p.getDNI(), p.getDorsal(), p.getName(), p.getSurname1(), p.getSurname2(), p.getSex(), p.getAge(), n.getName(), p.getCompetitions_wins(), p.getSalary(), p.getType(), p.getDebut(), p.getRetired());

                            System.out.print("Dorsal: ");
                            int dorsal = sc.nextInt();
                            System.out.print("Name: ");
                            String name = sc.next();
                            System.out.print("Surname: ");
                            String surname = sc.next();
                            System.out.print("Surname2: ");
                            String surname2 = sc.next();
                            System.out.print("Age: ");
                            int age = sc.nextInt();
                            System.out.print("Gender: ");
                            String sex = sc.next();
                            System.out.print("ID_Nationality: ");
                            int nat = sc.nextInt();
                            System.out.print("Competitions Wins: ");
                            int comp = sc.nextInt();
                            System.out.print("Salary: ");
                            int salary = sc.nextInt();
                            System.out.println("Debut date (YYYY-MM-DD): ");
                            String debutDate = sc.next();
                            Date debut = Date.valueOf(debutDate);
                            System.out.println("Retired date (YYYY-MM-DD): ");
                            String retiredDate = sc.next();
                            Date retired = Date.valueOf(retiredDate);
                            System.out.println("Sport ID: ");
                            int sport = sc.nextInt();
                            System.out.println("Team ID: ");
                            int team = sc.nextInt();

                            Players p2 = new Players(p.getDNI(), dorsal, name, surname, surname2, age, sex, nat, comp, salary, debut, retired, team, sport);
                            mapper.updatePlayer(p2);
                            System.out.println("Player successfully modified!");

                        } else
                            System.out.println("The player's DNI is incorrect or doesn't exists.");

                    } else if (people.equals("coach")) {
                        System.out.print("Insert DNI of coach: ");
                        Coaches c = mapper.getCoach(sc.next());
                        if (c != null) {
                            if (c.getSurname2() == null) c.setSurname2("");
                            Nationalities n = mapper.getNationality(c.getNat());
                            System.out.printf(
                                    "+-------------+-------------------+-------------------+-------------------+----------+------------------------+-------------+--------+\n|     DNI     |       NAME        |   FIRST SURNAME   |  SECOND SURNAME   |  GENDER  |      NATIONALITY       |   SALARY    |  TYPE  |\n+-------------+-------------------+-------------------+-------------------+----------+------------------------+-------------+--------+\n|  %9s  |  %15s  |  %15s  |  %15s  |  %6s  |  %20s  |  %9d  |  %4d  |\n+-------------+-------------------+-------------------+-------------------+----------+------------------------+-------------+--------+\n",
                                    c.getDNI(), c.getName(), c.getSurname1(), c.getSurname2(), c.getSex(), n.getName(), c.getSalary(), c.getType());

                            System.out.println("Name: ");
                            String name = sc.next();
                            System.out.println("First surname: ");
                            String surname1 = sc.next();
                            System.out.println("Last surname: ");
                            String surname2 = sc.next();
                            System.out.println("Gender: ");
                            String sex = sc.next();
                            System.out.println("ID_Nationality: ");
                            int nat = sc.nextInt();
                            System.out.println("Salary: ");
                            int salary = sc.nextInt();

                            Coaches c2 = new Coaches(c.getDNI(), name, surname1, surname2, sex, nat, salary);
                            mapper.updateCoach(c2);
                            System.out.println("Coach successfully modified!");

                        } else
                            System.out.println("The coach's DNI is incorrect or doesn't exists.");

                    } else if (people.equals("referee")) {
                        System.out.print("Insert DNI of referee: ");
                        Referees r = mapper.getReferee(sc.next());
                        if (r != null) {
                            if (r.getSurname2() == null) r.setSurname2("");
                            Nationalities n = mapper.getNationality(r.getNat());
                            System.out.printf(
                                    "+-------------+-------------------+-------------------+-------------------+----------+-------+------------------------+------------------------+-------------+--------+\n|     DNI     |       NAME        |   FIRST SURNAME   |  SECOND SURNAME   |  GENDER  |  AGE  |      NATIONALITY       |      REFEREE TYPE      |   SALARY    |  TYPE  |\n+-------------+-------------------+-------------------+-------------------+----------+-------+------------------------+------------------------+-------------+--------+\n|  %9s  |  %15s  |  %15s  |  %15s  |  %6s  |   %2s  |  %20s  |  %20s  |  %9d  |  %4d  |\n+-------------+-------------------+-------------------+-------------------+----------+-------+------------------------+------------------------+-------------+--------+\n",
                                    r.getDNI(), r.getName(), r.getSurname1(), r.getSurname2(), r.getSex(), r.getAge(), n.getName(), r.getRef_type(), r.getSalary(), r.getType());

                            System.out.println("Name: ");
                            String name = sc.next();
                            System.out.println("First surname: ");
                            String surname1 = sc.next();
                            System.out.println("Last surname: ");
                            String surname2 = sc.next();
                            System.out.println("Sport ID: ");
                            int sport = sc.nextInt();
                            System.out.println("Competition ID: ");
                            int comp = sc.nextInt();
                            System.out.println("Age: ");
                            int age = sc.nextInt();
                            System.out.println("Gender: ");
                            String sex = sc.next();
                            System.out.println("ID_Nationality: ");
                            int nat = sc.nextInt();
                            System.out.println("Referee Type: ");
                            String ref_type = sc.next();
                            System.out.println("Salary: ");
                            int salary = sc.nextInt();
                            System.out.println("Type: ");
                            int type = sc.nextInt();

                            Referees r2 = new Referees(r.getDNI(), name, surname1, surname2, sport, comp, age, sex, nat, ref_type, salary, type);
                            mapper.updateReferee(r2);
                            System.out.println("Referee successfully modified!");

                        } else
                            System.out.println("The referee's DNI is incorrect or doesn't exists.");

                    } else
                        System.out.println("Unknown type of person");

                } else if (group.equals("teams")) {
                    System.out.print("Insert team's ID: ");
                    Teams t = mapper.getTeam(sc.nextInt());
                    if (t != null) {
                        Nationalities n = mapper.getNationality(t.getNationality());
                        System.out.printf(
                                "+--------+-----------------------------+------------------------+----------+\n|   ID   |            NAME             |      NATIONALITY       |  GENDER  |\n+--------+-----------------------------+------------------------+----------+\n|  %4d  |  %25s  |  %20s  |  %6s  |\n+--------+-----------------------------+------------------------+----------+\n",
                                t.getId(), t.getName(), n.getName(), t.getGender());

                        System.out.print("Name: ");
                        String name = sc.next();
                        System.out.print("ID_Nationality: ");
                        int nat = sc.nextInt();
                        System.out.print("Gender: ");
                        String sex = sc.next();

                        Teams t2 = new Teams(t.getId(), name, nat, sex);
                        mapper.updateTeam(t2);
                        System.out.println("Team successfully modified!");

                    } else
                        System.out.println("The team's ID is incorrect or doesn't exists.");

                } else if (group.equals("sports")) {
                    System.out.println("Insert sport's ID: ");
                    int id = sc.nextInt();

                    Sports s = mapper.getSport(id);
                    if (s != null) {
                        System.out.printf(
                                "+--------+-------------------+\n|   ID   |       NAME        |\n+--------+-------------------+\n|  %4d  |  %15s  |\n+--------+-------------------+\n",
                                s.getId(), s.getName());

                        System.out.println("Name: ");
                        String name = sc.next();

                        Sports s2 = new Sports(s.getId(), name);
                        mapper.updateSport(s2);
                        System.out.println("Sport successfully modified!");

                    } else
                        System.out.println("The sport's ID is incorrect or doesn't exists.");

                } else
                    System.out.println("Incorrect group.");

            } else if (action.equals("delete")) {
                System.out.print("Enter the group [people, teams or sports]: ");
                String group = sc.next();

                if (group.equals("people")) {
                    System.out.print("Type of person [player, coach, referee]: ");
                    String people = sc.next();
                    String dni = "";

                    if (people.equals("player")) {
                        System.out.print("Insert person's DNI: ");
                        Players p = mapper.getPerson(sc.next());
                        if (p != null) {
                            if (p.getSurname2() == null) p.setSurname2("");
                            if (p.getRetired() == null) p.setRetired(Date.valueOf("9999-01-01"));
                            Nationalities n = mapper.getNationality(p.getNationality());
                            System.out.printf(
                                    "+-------------+----------+-------------------+-------------------+-------------------+----------+-------+------------------------+--------------+-------------+--------+---------------------------------+--------------------------------+\n|     DNI     |  DORSAL  |       NAME        |   FIRST SURNAME   |  SECOND SURNAME   |  GENDER  |  AGE  |      NATIONALITY       |  COMP. WINS  |   SALARY    |  TYPE  |              DEBUT              |            RETIRED             |\n+-------------+----------+-------------------+-------------------+-------------------+----------+-------+------------------------+--------------+-------------+--------+---------------------------------+--------------------------------+\n|  %9s  |    %2d    |  %15s  |  %15s  |  %15s  |  %6s  |   %2s  |  %20s  |  %10d  |  %9d  |  %4d  |  %28s  |  %28s  |\n+-------------+----------+-------------------+-------------------+-------------------+----------+-------+------------------------+--------------+-------------+--------+---------------------------------+--------------------------------+\n",
                                    p.getDNI(), p.getDorsal(), p.getName(), p.getSurname1(), p.getSurname2(), p.getSex(), p.getAge(), n.getName(), p.getCompetitions_wins(), p.getSalary(), p.getType(), p.getDebut(), p.getRetired());
                            dni = p.getDNI();

                        } else
                            System.out.println("The player's DNI is incorrect or doesn't exists.");

                    } else if (people.equals("coach")) {
                        System.out.print("Insert DNI of coach: ");
                        Coaches c = mapper.getCoach(sc.next());
                        if (c != null) {
                            if (c.getSurname2() == null) c.setSurname2("");
                            Nationalities n = mapper.getNationality(c.getNat());
                            System.out.printf(
                                    "+-------------+-------------------+-------------------+-------------------+----------+------------------------+-------------+--------+\n|     DNI     |       NAME        |   FIRST SURNAME   |  SECOND SURNAME   |  GENDER  |      NATIONALITY       |   SALARY    |  TYPE  |\n+-------------+-------------------+-------------------+-------------------+----------+------------------------+-------------+--------+\n|  %9s  |  %15s  |  %15s  |  %15s  |  %6s  |  %20s  |  %9d  |  %4d  |\n+-------------+-------------------+-------------------+-------------------+----------+------------------------+-------------+--------+\n",
                                    c.getDNI(), c.getName(), c.getSurname1(), c.getSurname2(), c.getSex(), n.getName(), c.getSalary(), c.getType());
                            dni = c.getDNI();

                        } else
                            System.out.println("The coach's DNI is incorrect or doesn't exists.");

                    } else if (people.equals("referee")) {
                        System.out.print("Insert DNI of referee: ");
                        Referees r = mapper.getReferee(sc.next());
                        if (r != null) {
                            if (r.getSurname2() == null) r.setSurname2("");
                            Nationalities n = mapper.getNationality(r.getNat());
                            System.out.printf(
                                    "+-------------+-------------------+-------------------+-------------------+----------+-------+------------------------+------------------------+-------------+--------+\n|     DNI     |       NAME        |   FIRST SURNAME   |  SECOND SURNAME   |  GENDER  |  AGE  |      NATIONALITY       |      REFEREE TYPE      |   SALARY    |  TYPE  |\n+-------------+-------------------+-------------------+-------------------+----------+-------+------------------------+------------------------+-------------+--------+\n|  %9s  |  %15s  |  %15s  |  %15s  |  %6s  |   %2s  |  %20s  |  %20s  |  %9d  |  %4d  |\n+-------------+-------------------+-------------------+-------------------+----------+-------+------------------------+------------------------+-------------+--------+\n",
                                    r.getDNI(), r.getName(), r.getSurname1(), r.getSurname2(), r.getSex(), r.getAge(), n.getName(), r.getRef_type(), r.getSalary(), r.getType());
                            dni = r.getDNI();

                        } else
                            System.out.println("The referee's DNI is incorrect or doesn't exists.");

                    } else
                        System.out.println("Unknown type of person");

                    People p2 = new People(dni, people);
                    System.out.println("Are you sure to delete the player? ALERT: This action has no turning back! (yes / no)");
                    String conf = sc.next();

                    if (conf.equals("yes")) {
                        mapper.deletePerson(p2);
                        System.out.println("Person successfully deleted!");

                    } else if (conf.equals("no"))
                        System.out.println("Person not deleted!");

                    else
                        System.out.println("Confirmation rejected, person not deleted!");

                } else if (group.equals("teams")) {
                    System.out.print("Insert ID of team: ");
                    Teams t = mapper.getTeam(sc.nextInt());
                    if (t != null) {
                        Nationalities n = mapper.getNationality(t.getNationality());
                        System.out.printf(
                                "+--------+-----------------------------+------------------------+----------+\n|   ID   |            NAME             |      NATIONALITY       |  GENDER  |\n+--------+-----------------------------+------------------------+----------+\n|  %4d  |  %25s  |  %20s  |  %6s  |\n+--------+-----------------------------+------------------------+----------+\n",
                                t.getId(), t.getName(), n.getName(), t.getGender());
                    } else
                        System.out.println("The team id is incorrect or doesn't exists.");

                    System.out.println("Are you sure to delete the team? ALERT: This action has no turning back! (yes / no)");
                    String conf = sc.next();

                    if (conf.equals("yes")) {
                        mapper.deleteTeam(t.getId());
                        System.out.println("Team successfully deleted!");
                    } else if (conf.equals("no"))
                        System.out.println("Team not deleted!");
                    else
                        System.out.println("Confirmation rejected, team not deleted!");

                } else if (group.equals("sports")) {
                    System.out.print("ID of sport: ");
                    Sports s = mapper.getSport(sc.nextInt());
                    System.out.printf(
                            "+--------+-------------------+\n|   ID   |       NAME        |\n+--------+-------------------+\n|  %4d  |  %15s  |\n+--------+-------------------+\n",
                            s.getId(), s.getName());

                    System.out.println("Are you sure to delete the sport? ALERT: This action has no turning back! (yes / no)");
                    String conf = sc.next();

                    if (conf.equals("yes")) {
                        mapper.deleteSport(s.getId());
                        System.out.println("Sport successfully deleted!");
                    } else if (conf.equals("no"))
                        System.out.println("Sport not deleted!");
                    else
                        System.out.println("Confirmation rejected, sport not deleted!");

                } else
                    System.out.println("Incorrect group.");

            } else
                System.out.println("Incorrect action.");

            System.out.print("Do you want to continue? (yes, no): ");
            if (sc.next().equals("no")) keep = false;

        }

        // CLOSE SESSION
        session.commit();
        session.close();

    }
}