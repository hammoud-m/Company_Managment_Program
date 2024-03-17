package Entity;

import javax.swing.JTable;

public interface mainData 
{
    public void add();
    public void delete();
    public void update();
    public void getAllRows(JTable table);
    public void getOneRow(JTable table);
    public void getCoustmRow(String statement, JTable table);
    public String getAutoNumber();
    public String getValueByName(String name);    
    public String getNameByValue (String value);   
}
