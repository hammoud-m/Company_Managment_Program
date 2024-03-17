
package Entity;

import javax.swing.JTable;


public class employee_phones implements mainData
{
    private int empno;
    private String phone;

    public int getEmpono() {
        return empno;
    }

    public void setEmpono(int empono) {
        this.empno = empono;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void add() {
        String insert = "insert into employee_phones values(" + empno + ",'" + phone + "')";
        boolean isAdded = db.go.runNonQuery(insert);
        if(isAdded) Company.readyFunctions.msgBox("Phone Is Added");
    }

    @Override
    public void delete() {
        String delete = "delete from employee_phones where empno=" + empno + " and phone='" + phone + "'";
        boolean isDeleted = db.go.runNonQuery(delete);
        if(isDeleted) Company.readyFunctions.msgBox("Phone Is Deleted");
    }
     //we dont need to update phones nrs, delete them and add new inst!
    @Override
    public void update() {
        Company.readyFunctions.msgBox("Update Method Not Supported Here!");
    }

    @Override
    public void getAllRows(JTable table) {
        String empNumber = "select from employee_phones where empno=" + empno; 
        db.go.fillToJTable(empNumber, table);
    }

    @Override
    public void getOneRow(JTable table) {
        Company.readyFunctions.msgBox("Doesn't Supported Here");
    }

    @Override
    public void getCoustmRow(String statement, JTable table) {
        Company.readyFunctions.msgBox("Doesn't Supported Here");
    }
     //we dont need this on phones!
    @Override
    public String getAutoNumber() {
        Company.readyFunctions.msgBox("Doesn't Supported Here");
        return "";
    }

    @Override
    public String getValueByName(String name) {
        Company.readyFunctions.msgBox("Doesn't Supported Here");
        return "";
    }

    @Override
    public String getNameByValue(String value) {
        Company.readyFunctions.msgBox("Doesn't Supported Here");
        return "";
    }
        //to delete all phones på en gång
    public void deleteByEmp() {
        String delete = "delete from employee_phones where empno=" + empno;
        boolean isDeleted = db.go.runNonQuery(delete);
        if(isDeleted) Company.readyFunctions.msgBox("Phones are deleted");
    }
    
    public String searchByPhone(String PhoneNr)
    {
        String select = "select empno from employee_phones where phone='" + PhoneNr + "'";
        Object row[][] = db.go.getTableData(select).items;
        String strEmpNo = "";
        if(row.length > 0) strEmpNo = (String)row[0][0];
        else strEmpNo = "0";
        
        return strEmpNo;
    }
}
