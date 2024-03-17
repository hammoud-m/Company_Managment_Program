
package Entity;

import javax.swing.JTable;

public class Employee implements mainData
{
    private int empno;
    private String empname;
    private String address;
    private double salary;
    private String hiringDate;
    private String birthDate;
    private int deptno;

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    @Override
    public void add() {
        String insert = "insert into employee values(" + empno + ",'" + empname + "','" + address + "'," + salary + ",'" + hiringDate + "'," + birthDate + "'," + deptno + ")";
        boolean isAdded = db.go.runNonQuery(insert);
        if(isAdded) Company.readyFunctions.msgBox("Employee is added");
    }

    @Override
    public void delete() {
        String delete = "delete from employee where empno=" + empno;
        boolean isDeleted = db.go.runNonQuery(delete);
        if(isDeleted) Company.readyFunctions.msgBox("Employee is deleted");
    }

    @Override
    public void update() {
        String update = "update employee set empname='" + empname + "', address='" + address +
                ", salary=" + salary + ",hiringdate='" + hiringDate + "', birthdate='" + birthDate +
                "', deptno=" + deptno + " where empno=" + empno;
        boolean isUpdated = db.go.runNonQuery(update);
        if(isUpdated) Company.readyFunctions.msgBox("Employee is updated");
    }

    @Override
    public void getAllRows(JTable table) {
        db.go.fillToJTable("employee", table);
    }

    @Override
    public void getOneRow(JTable table) {
        String select = "select * from employee_data where number=" + empno;
        db.go.fillToJTable(select, table);
    }

    @Override
    public void getCoustmRow(String statement, JTable table) {
        db.go.fillToJTable(statement, table);
    }

    @Override
    public String getAutoNumber() {
        return db.go.getAutoNumber("employee", "empno");
    }

    @Override
    public String getValueByName(String name) {
        String select = "select empno form employee where empname='" + name + "'";
        String val = (String)db.go.getTableData(select).items[0][0];
        return val;
    }

    @Override
    public String getNameByValue(String value) {
        String select = "select empname from employee where empno=" + value;
        String name = (String)db.go.getTableData(select).items[0][0];
        return name;
    }
    
}
