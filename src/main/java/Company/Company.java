package Company;

import java.io.IOException;

public class Company {

    public static void main(String[] args)
    {
        
        try {
            Forms.frmLogin f = new Forms.frmLogin();
            readyFunctions.openForm(f);
        } catch (IOException ex) {
          readyFunctions.msgBox(ex.getMessage());
        }
    }
}
