
package Entity;

import javax.swing.JTable;

public class Department implements mainData
{
    private int deptno;
    private String deptname;
    private String location;

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void add() {
       String insert = "insert into department values("
               + deptno + ", '" + deptname + "' ,'" + location + "');" ;
       boolean isAdded = db.go.runNonQuery(insert);
       if(isAdded)
       {
           Company.readyFunctions.msgBox("Department is added");
       }
    }

    @Override
    public void delete() {
        String delete = "delete from department where deptno=" + deptno;
        boolean isDeleted = db.go.runNonQuery(delete);
        if(isDeleted)
        {
            Company.readyFunctions.msgBox("Department is deleted");
        }
    }

    @Override
    public void update() {
        String update = "update department set deptname= '" 
                     + deptname + "', location= '" + location + 
                     "' where deptno= " + deptno;
        boolean isUpdated = db.go.runNonQuery(update);
        if(isUpdated)
        {
            Company.readyFunctions.msgBox("Department is updated");
        }
    }

    @Override
    public void getAllRows(JTable table) {
        db.go.fillToJTable("department", table);
        
    }

    @Override
    public void getOneRow(JTable table) {
        String select = "select * from department_data where Number= " + deptno;
        db.go.fillToJTable(select, table);
    }

    @Override //this method to use in search btn
    public void getCoustmRow(String statement, JTable table) {
        db.go.fillToJTable(statement, table);
    }

    @Override
    public String getAutoNumber() {
      String auto = db.go.getAutoNumber("department", "deptno");
      return auto;
    }

    @Override
    public String getValueByName(String name) {
        String select = "select deptno from department where deptname='" + name + "'";
        String val = (String)db.go.getTableData(select).items[0][0];
        return val;
    }

    @Override
    public String getNameByValue(String value) {
        String select = "select deptname from department where deptno='" + value;
        String name = (String)db.go.getTableData(select).items[0][0];
        return name;
        
    }
    
}
