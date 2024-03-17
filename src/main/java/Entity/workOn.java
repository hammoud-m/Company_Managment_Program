
package Entity;

import Company.readyFunctions;
import javax.swing.JTable;

public class workOn implements mainData{
    private int empno;
    private int projectno;

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public int getProjectno() {
        return projectno;
    }

    public void setProjectno(int projectno) {
        this.projectno = projectno;
    }

    @Override
    public void add() {
        String insert = "insert into workon values("
                + empno + "," + projectno + ")";
        boolean isAdded = db.go.runNonQuery(insert);
        if(isAdded) readyFunctions.msgBox("Work On Is Added!");
    }

    @Override
    public void delete() {
        String strDelete = "delete from workon where projectno= " + projectno 
                + "and empno= " + empno;
        boolean isDeleted = db.go.runNonQuery(strDelete);
        if(isDeleted) readyFunctions.msgBox("Work On Is Deleted!");
    }

    @Override
    public void update() {
        //can not update the primary key in logical way !
        readyFunctions.msgBox("Update Method Not Working");
    }

    @Override
    public void getAllRows(JTable table) {
        db.go.fillToJTable("workon_data", table);
    }

    @Override
    public void getOneRow(JTable table) {
        String strSelect = "select * from workn_data where Employee_Number= "
                + empno + "and Project_Number= " + projectno;
        db.go.fillToJTable(strSelect, table);
    }

    @Override
    public void getCoustmRow(String statement, JTable table) {
        db.go.fillToJTable(statement, table);
    }

    @Override
    public String getAutoNumber() {
        readyFunctions.msgBox("getAutoNumber method is not working");
        return "";
    }

    @Override
    public String getValueByName(String name) {
        readyFunctions.msgBox("getValueByName method is not working");
        return "";
    }

    @Override
    public String getNameByValue(String value) {
        readyFunctions.msgBox("getNameByValue method is not working");
        return "";
    }
}
