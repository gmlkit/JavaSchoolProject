package eAsistentMini.Logic;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseWork {
    public void testDatabaseConn(){
        try{

            Class.forName("org.postgresql.Driver");

        }

        catch(ClassNotFoundException e)
        {
            System.out.println("error class not found exception");
            e.printStackTrace();

        }
    }
    public int[] LogIn(String username, String password){

        int[] logCheck=new int[2];
        logCheck[0]=2;
        String str="SELECT *FROM login(?,?)";
        try(Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
            Statement st = conn.createStatement();){

            PreparedStatement psql=conn.prepareStatement(str);
            psql.setString(1,username);
            psql.setString(2,password);

            ResultSet rs = psql.executeQuery();
            while(rs.next()){
                logCheck[0]=rs.getInt(1);

            }
            rs.close();
            st.close();

            if(logCheck[0]==1){//učitelji
                logCheck[1]=getTeacher(username);
                if(logCheck[1]==123123){
                    System.out.println(logCheck[1]);
                }
                return logCheck;
            }else if(logCheck[0]==0){//starši
                logCheck[1]=getParrent(username);
                return logCheck;
            }else{
                logCheck[0]=99;
                return logCheck;
            }
        }catch(Exception es){
            es.printStackTrace();
        }
        return logCheck;
    }
    public int addTeacher(String email,String pass1,String pass2,int classRoom){
        String str="SELECT *FROM add_teacher()?,?,?,?)";
        try(Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
            Statement st = conn.createStatement();){

            PreparedStatement psql=conn.prepareStatement(str);
            psql.setString(1,email);
            psql.setString(2,pass1);
            psql.setString(2,pass2);
            psql.setInt(2,classRoom);

            ResultSet rs = psql.executeQuery();
            int mate=0;
            while(rs.next()){
                mate=rs.getInt(1);

            }
            rs.close();
            st.close();

            return mate;
        }

        catch(Exception es){
            es.printStackTrace();
        }
        return 3;

    }
    public void addParrent(){

    }
    public void addStudent(){

    }
    public void addClass(){

    }
    public void addGrade(){

    }
    public void getGrade(){

    }
    public String[][] getStudents(String email){
        String[][] mate=new String[100][3];

        String str="SELECT DISTINCT(u.*)FROM ucenci u \n" +
                "INNER JOIN ucenci_predmeti u_p ON u_p.ucenec_id=u.id \n" +
                "INNER JOIN predmeti pr ON pr.id=u_p.predmet_id\n" +
                "INNER JOIN ucitelji_predmeti up ON up.predmet_id=pr.id\n" +
                "INNER JOIN ucitelji uc ON uc.id=up.ucitelj_id\n" +
                "WHERE(uc.email=?)";
        try(Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
            Statement st = conn.createStatement();){

            PreparedStatement psql=conn.prepareStatement(str);
            psql.setString(1,email);

            ResultSet rs = psql.executeQuery();
            int i=0;
            while(rs.next()){
                mate[i][0]=rs.getString(1);
                mate[i][1]=rs.getString(2);
                mate[i][2]=rs.getString(3);
                i++;
            }
            rs.close();
            st.close();

            return mate;
        }

        catch(Exception es){
            es.printStackTrace();
        }
        return mate;

    }
    public int getTeacher(String email){
        String str="SELECT id FROM ucitelji WHERE email LIKE ?";
        try(Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
            Statement st = conn.createStatement();){

            PreparedStatement psql=conn.prepareStatement(str);
            psql.setString(1,email);

            ResultSet rs = psql.executeQuery();
            int mate=0;
            while(rs.next()){
                mate=rs.getInt(1);

            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(mate);
            return mate;
        }

        catch(Exception es){
            es.printStackTrace();
        }
        return 123123;
    }
    public int getParrent(String email){
        String str="SELECT id FROM starsi WHERE email LIKE ?";
        try(Connection conn = DriverManager.getConnection(Conn.URL, Conn.USER, Conn.PASS);
            Statement st = conn.createStatement();){

            PreparedStatement psql=conn.prepareStatement(str);
            psql.setString(1,email);

            ResultSet rs = psql.executeQuery();
            int mate=0;
            while(rs.next()){
                mate=rs.getInt(1);

            }
            rs.close();
            st.close();

            return mate;
        }

        catch(Exception es){
            es.printStackTrace();
        }
        return 123123;

    }
}
