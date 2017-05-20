import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by toma on 19/05/2017.
 */
public class Student {
    private Integer id_student;
    private String prenume;
    private String nume;
    private Integer grupa;

    public Student(Integer id_student, String prenume, String nume, Integer grupa) {
        this.id_student = id_student;
        this.prenume = prenume;
        this.nume = nume;
        this.grupa = grupa;
    }

    public Student(ResultSet resultSet) throws SQLException {
        Integer result_id_student = (Integer)resultSet.getObject("id_student");
        String result_nume = (String)resultSet.getObject("nume");
        String result_prenume = (String)resultSet.getObject("prenume");
        Integer result_grupa = (Integer)resultSet.getObject("grupa");
        id_student = result_id_student;
        nume = result_nume;
        prenume = result_prenume;
        grupa = result_grupa;
    }

    public Integer getId_student() {
        return id_student;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public Integer getGrupa() {
        return grupa;
    }

    @Override
    public String toString() {
        return id_student + " " + nume + " " + prenume + " " + grupa;
    }

    public String toFormattedString() {
        return "Student{" +
                "id_student=" + id_student +
                ", prenume='" + prenume + '\'' +
                ", nume='" + nume + '\'' +
                ", grupa=" + grupa +
                '}';
    }
}
