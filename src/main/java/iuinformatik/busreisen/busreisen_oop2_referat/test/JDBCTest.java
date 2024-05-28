package iuinformatik.busreisen.busreisen_oop2_referat.test;

import java.sql.*;
import java.util.Arrays;

public class JDBCTest {
    public static void main(String[] args) throws SQLException {

        boolean[] sitzplaetze = new boolean[9];
        Arrays.fill(sitzplaetze,false);

        String sqlArray = "{" + Arrays.toString(sitzplaetze).substring(1, Arrays.toString(sitzplaetze).length()-1) + "}";
        String sql = String.format("UPDATE 1 SET 2 = '%s' WHERE id = 3", sqlArray);
        System.out.println(Date.valueOf("0000-00-00"));

    }


}

