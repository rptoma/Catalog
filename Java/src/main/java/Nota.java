import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by toma on 19/05/2017.
 */
public class Nota {
    private int nota;

    public Nota(int nota) {
        this.nota = nota;
    }

    public Nota(ResultSet resultSet) throws SQLException {
        Integer result_nota = (Integer)resultSet.getObject("nota");
        nota = result_nota;
    }

    public int getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "nota=" + nota +
                '}';
    }
}
