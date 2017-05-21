package servlets;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by toma on 19/05/2017.
 */
public class Materie {
    private String id_materie;
    private Integer nr_credite;

    public Materie(String id_materie, int nr_credite) {
        this.id_materie = id_materie;
        this.nr_credite = nr_credite;
    }

    public Materie(ResultSet resultSet) throws SQLException {
        String result_id_materie = (String)resultSet.getObject("id_materie");
        Integer result_nr_credite = (Integer)resultSet.getObject("nr_credite");
        id_materie = result_id_materie;
        nr_credite = result_nr_credite;
    }

    public String getId_materie() {
        return id_materie;
    }

    public int getNr_credite() {
        return nr_credite;
    }

    @Override
    public String toString() {
        return id_materie + " " + String.valueOf(nr_credite);
    }

    public String toFormattedString() {
        return "servlets.Materie{" +
                "id_materie='" + id_materie + '\'' +
                ", nr_credite=" + nr_credite +
                '}';
    }
}
