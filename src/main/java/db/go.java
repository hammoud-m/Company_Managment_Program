package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Company.readyFunctions.myTabel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class go 
{
    private static String url = "";
    private static Connection con;
    
    public static void getUrl(String dbName)
    {
        //unicode for arabic lang annars blir ?????
        url = "jdbc:mysql://localhost:3306/" + dbName + 
              "?useUnicode=true&characterEncoding=UTF-8";
    }
    // can add throws SQLException
    public static void setConnection() 
    {
        try{
        getUrl("company");
        con = DriverManager.getConnection(url, "root", ""); //(urlpath, user, password)
        }catch(SQLException ex)
        {
            Company.readyFunctions.msgBox(ex.getMessage());
        }
    }
    
    public static boolean checkUserAndPass(String user, String pass)
    {
        try {
            setConnection();
            Statement stmt = con.createStatement();
            String check = "select * from users where username='" + user + "' and pass=" + pass + "'";
            stmt.executeQuery(check);
            while(stmt.getResultSet().next())
                {
                con.close();
                return true;
                } 
                con.close();
        } catch (SQLException ex) 
            {
                Company.readyFunctions.msgBox(ex.getMessage());    
            }   
        return false;
    }
    //method to run add, update, delete only . NonQuery means عدم الاستعلام
    public static boolean runNonQuery(String sqlStatement)
    {
        try{
            
        setConnection();
        Statement stmt = con.createStatement();
        stmt.execute(sqlStatement);
        con.close();
        return true;
        
        }
        catch(SQLException ex){
            Company.readyFunctions.msgBox(ex.getMessage());
            return false;
        }
    }
    // method brings max nr then +1 
    public static String getAutoNumber(String tableName, String coloumnName)
    {   
    try{
        setConnection();
        Statement stmt = con.createStatement();
        String autoMax = "select max(" + coloumnName + ")+1 as autonum from " + tableName;
        stmt.executeQuery(autoMax);
        String num = "";
        while(stmt.getResultSet().next())
        {
            stmt.getResultSet().getString("autonum");
        }
        con.close();
        if(num == null || "".equals(num))
        {
            return "1";
        }else return num;
    }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return "0";
        }
    }
    
    public static myTabel getTableData(String statement)
    {
        Company.readyFunctions t = new Company.readyFunctions();
        try{
            setConnection();
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(statement);
            //metadata hanterar coloumns (för veta nr av coulmns)
            ResultSetMetaData rsmd =rs.getMetaData();
            int c = rsmd.getColumnCount();
            
            myTabel table = t.new myTabel(c);
            while(rs.next())
                    {
                       Object row[] = new Object[c];
                       for(int x = 0; x < c; x++)
                       {
                           row[x] = rs.getString(x+1);
                       }
                       //myTabel.addNewRow(row); 
                    }
            con.close();
            return table;
        }catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return t.new myTabel(0);
        }
    }
    
    public static void fillCombo(String tableName, String coloumnName, JComboBox box)
    {
        try{
           setConnection();
           Statement stmt = con.createStatement();
           String select = "select " + tableName + " from " + coloumnName; 
           ResultSet rs;
           rs = stmt.executeQuery(select);
           //we have to get how many nrs of rows there's
           rs.last(); // stop on last 
           int c = rs.getRow(); //save the row nr which is last row in c 
           rs.beforeFirst(); // go back to first 
           String values[] = new String[c];
           int i = 0; // counter
           while(rs.next())
           {
               values[i] = rs.getString(1); //getString() not like array , starts with 1 not 0
               i++;
           }
           con.close();
           box.setModel(new DefaultComboBoxModel(values));
        }catch(SQLException ex)
        {
            Company.readyFunctions.msgBox(ex.getMessage());
        }
    }
    
    public static void fillToJTable(String tabelNameOrStatement, JTable table)
    {
        try{
        setConnection();
        Statement stmt = con.createStatement(); 
        String select;
        if(tabelNameOrStatement.substring(0, 7).toLowerCase().equals("select "))
        {
            select = tabelNameOrStatement;
        }else{
            select = "select * from " + tabelNameOrStatement; 
        }
        ResultSet rs = stmt.executeQuery(select);
        
        //för att spara coloumns nr
        ResultSetMetaData rsmd = rs.getMetaData();
        int c = rsmd.getColumnCount();
        //hanterar JTable
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        
        Vector row = new Vector();
        while(rs.next())
        {
            row = new Vector(c);
            for(int x =1; x < c; x++)
            {
                row.add(rs.getString(x));
            }
            model.addRow(row);
            
            if(table.getColumnCount()!= c)
            {
                Company.readyFunctions.msgBox("Table Coulomns Count Not Equal to Query Coulomns Count\nJTable Coulomns Count Is: " + table.getColumnCount() + "\nQuery Colulomns Count Is: " + c);
            }
            con.close();
        }
        }catch(SQLException ex)
        {
            Company.readyFunctions.msgBox(ex.getMessage());
        }
        
        
    }
}
