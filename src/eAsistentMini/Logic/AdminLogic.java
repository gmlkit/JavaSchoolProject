package eAsistentMini.Logic;

import java.sql.*;

public class AdminLogic {
    public void testDatabaseConn() {
        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("error class not found exception");
            e.printStackTrace();

        }
    }
    public int addTeacher(String email, String pass1, String pass2, int classRoom) {
        String str = "SELECT *FROM add_teacher()?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
             Statement st = conn.createStatement();) {

            PreparedStatement psql = conn.prepareStatement(str);
            psql.setString(1, email);
            psql.setString(2, pass1);
            psql.setString(2, pass2);
            psql.setInt(2, classRoom);

            ResultSet rs = psql.executeQuery();
            int mate = 0;
            while (rs.next()) {
                mate = rs.getInt(1);

            }
            rs.close();
            st.close();

            return mate;
        } catch (Exception es) {
            es.printStackTrace();
        }
        return 3;

    }
}
