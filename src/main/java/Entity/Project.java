
package Entity;

import javax.swing.JTable;

public class Project implements mainData
{
    private int projectno ;
    private String projctname ;
    private String location;
    private int deptno ;

    public int getProjectno() {
        return projectno;
    }

    public void setProjectno(int projectno) {
        this.projectno = projectno;
    }

    public String getProjctname() {
        return projctname;
    }

    public void setProjctname(String projctname) {
        this.projctname = projctname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    @Override
    public void add() {
        String insert = "insert into project values(" + projectno + ",'" + 
                projctname + "','" + location + "'," + deptno + ")";
        boolean isAdded = db.go.runNonQuery(insert);
        if(isAdded) Company.readyFunctions.msgBox("Project Is Added");
    }

    @Override
    public void delete() {
        String delete = "delete from project where projectno=" + projectno;
        boolean isDeleted = db.go.runNonQuery(delete);
        if(isDeleted) Company.readyFunctions.msgBox("Project IS Deleted");
    }

    @Override
    public void update() {
        String update = "update project set projctname='" + 
                projctname + "',location='" + location + "',deptno=" +
                deptno + " where projectno=" + projectno;
        boolean isUpdated = db.go.runNonQuery(update);
        if(isUpdated) Company.readyFunctions.msgBox("Project Is Updated");
    }

    @Override
    public void getAllRows(JTable table) {
        db.go.fillToJTable("project", table);
    }

    @Override
    public void getOneRow(JTable table) {
        String select = "select * from project_data where number=" + projectno;
        db.go.fillToJTable(select, table);
    }

    @Override
    public void getCoustmRow(String statement, JTable table) {
        db.go.fillToJTable(statement, table);
    }

    @Override
    public String getAutoNumber() {
        return db.go.getAutoNumber("project", "projectno");
    }

    @Override
    public String getValueByName(String name) {
        String select = "select projectno from project where projectname='" + name + "'";
        String val = (String)db.go.getTableData(select).items[0][0];
        return val;
    }

    @Override
    public String getNameByValue(String value) {
        String select = "select projectname from project where projectno=" + value;
        String val = (String)db.go.getTableData(select).items[0][0];
        return val;
    }
}
