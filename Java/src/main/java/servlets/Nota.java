package servlets;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by toma on 19/05/2017.
 */
public class Nota {
    private Integer nota;

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
        return String.valueOf(nota);
    }

    public String toFormattedString() {
        return "Nota{" +
                "nota=" + nota +
                '}';
    }
}
