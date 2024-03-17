package Company;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle ; 
import java.awt.print.PrinterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;




public class readyFunctions
{
    public static void openForm(JFrame form) throws IOException
    {
        //usualy cerate object but here nope!
        BufferedImage img = ImageIO.read(Class.class.getResource("NAME.png"));
        form.setIconImage(img);
        form.setLocationRelativeTo(null);
        form.setDefaultCloseOperation(2);
        form.setVisible(true);
    }

    public static void msgBox(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void createFolder(String FolderName, String path)
    {
        File f = new File (path + "/" + FolderName);
        f.mkdir();
    }

    public static void createFolder(String FolderName)
    {
        File f = new File (FolderName);
        f.mkdir();
    }

    public static void createEmptyFile(String FileName) throws IOException
    {
        File f = new File (FileName + ".txt");
        f.createNewFile();
    }

    public static void createMultiEmptyFiles(String Filenames[]) throws IOException
    {   
        for(String str: Filenames)
        {
            //File f = new File (str + ".txt");
            //f.createNewFile();
            createEmptyFile(str); 
        }
    }

    public static void createFile(String FileName, Object Data[]) throws FileNotFoundException
    {
        PrintWriter p = new PrintWriter(FileName + ".txt");
        for (Object x: Data)
        {
            p.println(x);
        }
        p.close();  
    }

    public static void createFiles(String Filenames[], Object allData[][]) throws FileNotFoundException
    {
        for(int x = 0; x < Filenames.length; x++)
        {
            createFile(Filenames[x], allData[x]);
        }
    }
    //set "" in every JTextField
    public static void clearText(java.awt.Container form)
    {
        for (java.awt.Component c: form.getComponents())
        {
            if (c instanceof JTextField)
            {
                JTextField txt = (JTextField)c;
                txt.setText("");
            }
            else if (c instanceof java.awt.Container)
            {
                clearText((java.awt.Container)c);
            }
        }   
    }
    
    public static void reportPrint(JTable table, String title)
    {
        try {
            MessageFormat Header = new MessageFormat(title + " Report");
            MessageFormat Footer = new MessageFormat("Page - {0}");
            table.print(JTable.PrintMode.FIT_WIDTH,Header,Footer);
        } catch (PrinterException ex) {
            readyFunctions.msgBox(ex.getMessage());
        }   
    }
    
    public static void setReportColor(JTable table)
    {
        table.getTableHeader().setBackground(new Color(51,0,0)); // dark brown color
        table.setForeground(new Color(255,255,255)); // white color
    }
    
    public static Object InputMsg(String title)
    {
        Object obj = JOptionPane.showInputDialog(title);
        return obj;
    }

    public static String getNumberzInStr(String text)
    {
        String value = "";
        for(char c: text.toCharArray())
        {
            if (c == '0' || c == '1'|| c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9')
            {
                value += c;
            }
        }
    return value;
    }
    public static String getNumberzInInt(String text)
    {
        String value = "";
        for(char c: text.toCharArray())
        {
            if (c == '0' || c == '1'|| c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9')
            {
                value += c;
            }
        }
    return value;
    }


    public static int removeNumberz(String text)
    {
        String value = "";
        for(char c: text.toCharArray())
        {
            if (!(c == '0' || c == '1'|| c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9'))
            {
                value += c;
            }
        }
    return Integer.parseInt(value);
    }

    public static void screenShot(String imageName) throws AWTException, IOException
    {
        Robot r = new Robot();
        Rectangle rec = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage img = r.createScreenCapture(rec);
        ImageIO.write(img, "jpg", new File(imageName + ".jpg"));
    }
    // this same but if there's JFrame to hid it before taking screenshot n put it back after
    public static void screenShot(String imageName, JFrame form) throws AWTException, IOException
    {
        form.setState(1);
        Robot r = new Robot();
        Rectangle rec = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage img = r.createScreenCapture(rec);
        ImageIO.write(img, "jpg", new File(imageName + ".jpg"));
        form.setState(0);
    }
    
    public static boolean confirmMsg(String message)
    {
        //showConfirmDialog as showMessageDialog but this with yes, no btn
        //showConfirmDialog return an int value so we save the value in int x
        int x = JOptionPane.showConfirmDialog(null, message);
        if(x == JOptionPane.YES_OPTION) // if user press yes btn
        {
            return true;
        }else return false;
    }
    public static class StringTools
    {
        private static String inverseText;
        
        public static void printCharByChar(String text)
        {
            for(char c: text.toCharArray())
            {
                System.out.println(c);
            }
        }
        
        public static void printCharByCharInverse(String text)
        {
            StringBuilder sb = new StringBuilder(text);
            inverseText = sb.reverse().toString();
            for(char c: inverseText.toCharArray())
            {
                System.out.println(c);
            }
        }  
    }
    
    public class myTabel
{
    public int columns;
    public Object items[][];

    public myTabel(int columns)
    {
        this.columns = columns;
        items = new Object [0][columns];
    }

    public void addNewRow(Object row[])
    {   
        //we have to save all info temproray before we add new row cuz adding a new row will save null on all prevs rows!
        Object tempItems [][] = items; 
        //adding 1 to array length
        items  = new Object [items.length + 1][columns];
        // adding back all info to prevs index's
        for (int x = 0; x < tempItems.length; x++)
        {
            items[x] = tempItems[x];
        }
        //adding a row
        items[items.length - 1] = row ;
    }

    public void printItems()
    {
        for (Object str1[]: items)
        {
            for(Object str2: str1)
            {
                System.out.print(str2 + " ; ");
            }
            System.out.println();
        }
    }

    public void editRow(int rowIndex, int columnIndex, Object newData)
    {
        items[rowIndex][columnIndex] = newData;
    }

    public void deleteRow(int rowIndex)
    {
        Object itemstemp[][] = items;
        items = new Object[items.length - 1][columns];
        //our problem now itemstemp.length != items.length cuz of (-1)
        //so we have to add var to plus it 
        int y = 0;
        for (int x = 0; x < itemstemp.length; x++)
        {
            if (x != rowIndex)
            {
                items[y] = itemstemp[x];    
                y++;
            }
        }
    }

    public Object getValue(int rowIndex, int columnIndex)
    {
        Object value [][] = items;
        System.out.println(value[rowIndex][columnIndex]+ "\n");    
        //System.out.println();
        return value;
        // or Direct 
        //return items [rowIndex][columnIndex];
    }

    public Object[] getRow(int rowIndex)
    {   
        /*
        //this way u have to give column index in func here
        Object rows[][] = items;
        System.out.println(rows[rowIndex][2]);
        return rows;
        */
        // this way u have to give dolumn index in calling
        return items[rowIndex];
    }
}
    
    public class MergeArray
{
    Object array1[];
    Object array2[];

    public MergeArray(Object array1[], Object array2[])
    {
        this.array1 = array1;
        this.array2 = array2;
    }
    public Object[] MergeToArray()
    {
        int a1 = array1.length;
        int a2 = array2.length;

        Object [] arrayR = new Object[a1 + a2];
        // (array u want to copy from, which index starting copy from, array copy to, which index, how many index(length))
        System.arraycopy(array1, 0, arrayR, 0, a1); //array1.length
        System.arraycopy(array2, 0, arrayR, a1, a2);
        System.out.println(arrayR);

        return arrayR;
    }
}
    

}