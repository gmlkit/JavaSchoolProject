package eAsistentMini.Logic;

import eAsistentMini.Logic.Objects.ObjectSet;
import eAsistentMini.Logic.Objects.ParentOb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseWork {
    public int[] LogIn(String username, String password) {

        int[] logCheck = new int[2];
        logCheck[0] = 2;
        String str = "SELECT *FROM login(?,?)";
        try (Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
             Statement st = conn.createStatement();) {

            PreparedStatement psql = conn.prepareStatement(str);
            psql.setString(1, username);
            psql.setString(2, password);

            ResultSet rs = psql.executeQuery();
            while (rs.next()) {
                logCheck[0] = rs.getInt(1);

            }
            rs.close();
            st.close();

            if (logCheck[0] == 1) {//učitelji
                logCheck[1] = getTeacher(username);
                System.out.println("Log Check Value" + logCheck[1]);
                if (logCheck[1] == 123123) {
                    System.out.println(logCheck[1]);
                }
                return logCheck;
            } else if (logCheck[0] == 0) {//starši
                logCheck[1] = getParrent(username);
                return logCheck;
            } else {
                logCheck[0] = 99;
                return logCheck;
            }
        } catch (Exception es) {
            es.printStackTrace();
        }
        return logCheck;
    }
    public int addGrade(int ocena, String opOcena, int predmet, int ucenec) {
        int success = 3;
        String str = "SELECT add_grade(?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
             Statement st = conn.createStatement();) {

            PreparedStatement psql = conn.prepareStatement(str);
            psql.setInt(3, ocena);
            psql.setInt(2, predmet);
            psql.setInt(1, ucenec);
            psql.setString(4, opOcena);


            ResultSet rs = psql.executeQuery();
            int i = 0;
            while (rs.next()) {
                success = rs.getInt(1);
            }
            rs.close();
            st.close();
            return success;
        } catch (Exception e) {
            System.out.println(e);
            return success;
        }
    }
    public ObservableList<String> getClassesArray(int id, String str) {
        ArrayList<String> mate = new ArrayList<>();
        ObservableList<String> SSS;
        try (Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
             Statement st = conn.createStatement();) {

            PreparedStatement psql = conn.prepareStatement(str);
            //psql.setString(1,email);
            psql.setInt(1, id);

            ResultSet rs = psql.executeQuery();
            int i = 0;
            while (rs.next()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(rs.getString("first"));
                stringBuilder.append(" ");
                stringBuilder.append(rs.getString("seccond"));
                mate.add(stringBuilder.toString());
            }
            rs.close();
            st.close();
            SSS = FXCollections.observableArrayList(mate);
            return SSS;

        } catch (Exception es) {
            es.printStackTrace();
        }
        ArrayList<String> s = new ArrayList<>();
        return SSS = FXCollections.observableArrayList(s);

    }
    public ObservableList<String> getStudentArray(int id, int prId) {
        ArrayList<String> mate = new ArrayList<>();
        ObservableList<String> SSS;

        String str = "SELECT DISTINCT u.* FROM ucenci u \n" +
                "            INNER JOIN ucenci_predmeti u_p ON u_p.ucenec_id=u.id\n" +
                "            INNER JOIN predmeti pr ON pr.id=u_p.predmet_id\n" +
                "            INNER JOIN ucitelji_predmeti up ON up.predmet_id=pr.id\n" +
                "            INNER JOIN ucitelji uc ON uc.id=up.ucitelj_id\n" +
                "            WHERE(uc.id=? AND pr.id=?)";
        try (Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
             Statement st = conn.createStatement();) {
            PreparedStatement psql = conn.prepareStatement(str);
            psql.setInt(1, id);
            psql.setInt(2, prId);

            ResultSet rs = psql.executeQuery();
            int i = 0;
            while (rs.next()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(rs.getString(3));
                stringBuilder.append(" ");
                stringBuilder.append(rs.getString(4));
                mate.add(stringBuilder.toString());
            }
            rs.close();
            st.close();
            SSS = FXCollections.observableArrayList(mate);
            return SSS;
        } catch (Exception es) {
            es.printStackTrace();
        }
        ArrayList<String> s = new ArrayList<>();
        return SSS = FXCollections.observableArrayList(s);
    }
    public int getTeacher(String email) {
        String str = "SELECT id AS \"id\" FROM ucitelji WHERE email LIKE ?";
        try (Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
             Statement st = conn.createStatement();) {

            PreparedStatement psql = conn.prepareStatement(str);
            psql.setString(1, email);

            ResultSet rs = psql.executeQuery();
            int mate = 0;
            while (rs.next()) {
                mate = rs.getInt("id");

            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(mate);
            return mate;
        } catch (Exception es) {
            es.printStackTrace();
        }
        return 123123;
    }
    public int getParrent(String email) {
        String str = "SELECT id FROM starsi WHERE email LIKE ?";
        try (Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
             Statement st = conn.createStatement();) {

            PreparedStatement psql = conn.prepareStatement(str);
            psql.setString(1, email);

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
        return 123123;

    }
    public int getStudent(String ime,String priimek) {
        String str = "SELECT id FROM ucenci WHERE ime LIKE ? AND priimek LIKE ?";
        try (Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
             Statement st = conn.createStatement();) {

            PreparedStatement psql = conn.prepareStatement(str);
            psql.setString(1, ime);
            psql.setString(2, priimek);

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
        return 123123;

    }
    public ObservableList<String> getParents() {
        ArrayList<ParentOb> mate = new ArrayList<>();
        ObservableList<String> SSS;

        String str = "SELECT id,email FROM starsi ";
        try (Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
             Statement st = conn.createStatement();) {

            PreparedStatement psql = conn.prepareStatement(str);
//            psql.setInt(1,id);
////            psql.setInt(2,prId);

            ResultSet rs = psql.executeQuery();
            int i = 0;
            while (rs.next()) {
                mate.add(new ParentOb(rs.getString(2), rs.getInt(1)));
            }
            rs.close();
            st.close();
            String stdnt;
            ArrayList<String> sList = new ArrayList<>();
            for (ParentOb s : mate
                    ) {
                stdnt = s.toString(s);
                sList.add(stdnt);
            }
            SSS = FXCollections.observableArrayList(sList);

            return SSS;
        } catch (Exception es) {
            es.printStackTrace();
        }
        ArrayList<String> s = new ArrayList<>();
        return SSS = FXCollections.observableArrayList(s);

    }
    public ObservableList<String> getStudents(int classId) {
        ArrayList<ObjectSet> mate = new ArrayList<>();
        ObservableList<String> SSS;

        String str = "SELECT * FROM ucenci u " +
                "INNER JOIN ucenci_predmeti up ON up.ucenec_id=u.id" +
                "WHERE up.predmet_id=?";
        try (Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
             Statement st = conn.createStatement();) {

            PreparedStatement psql = conn.prepareStatement(str);
            psql.setInt(1,classId);
//            psql.setInt(2,prId);

            ResultSet rs = psql.executeQuery();
            int i = 0;
            while (rs.next()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(rs.getString(3));
                stringBuilder.append(" ");
                stringBuilder.append(rs.getString(4));
                mate.add(new ObjectSet(stringBuilder.toString(), rs.getInt(1)));
            }
            rs.close();
            st.close();
            String stdnt;
            ArrayList<String> sList = new ArrayList<>();
            for (ObjectSet s : mate
                    ) {
                stdnt = s.toString(s);
                sList.add(stdnt);
            }
            SSS = FXCollections.observableArrayList(sList);

            return SSS;
        } catch (Exception es) {
            es.printStackTrace();
        }
        ArrayList<String> s = new ArrayList<>();
        return SSS = FXCollections.observableArrayList(s);

    }

    public ObservableList<String> getClasses() {
        ArrayList<String> mate = new ArrayList<>();
        ObservableList<String> SSS;
        String str="SELECT ime FROM predmeti";
        try (Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
             Statement st = conn.createStatement();) {

            PreparedStatement psql = conn.prepareStatement(str);
            //psql.setString(1,email);
            //psql.setInt(1, id);

            ResultSet rs = psql.executeQuery();
            int i = 0;
            while (rs.next()) {
                mate.add(rs.getString(1));
            }
            rs.close();
            st.close();
            SSS = FXCollections.observableArrayList(mate);
            return SSS;

        } catch (Exception es) {
            es.printStackTrace();
        }
        ArrayList<String> s = new ArrayList<>();
        return SSS = FXCollections.observableArrayList(s);

    }
}