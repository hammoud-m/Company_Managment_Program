package Controls;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import javax.swing.JButton;


public class myJButton extends JButton
{

    public myJButton() {
        setOpaque(false); // make it unvisible (shaffaf)
        setContentAreaFilled(false);
        setForeground(Color.WHITE); //text inside the button
        setCursor(new Cursor(Cursor.HAND_CURSOR)); // when u set mouse on btn draws hand, cursor means mouse ishara
        
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(Color.darkGray);
        g.fillRoundRect(0, 0, getWidth()-2, getHeight()-2, 20, 20);
        super.paintComponent(g);
    }
    
    @Override
    protected void paintBorder(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.drawRoundRect(0, 0, getWidth()-2, getHeight()-2, 20, 20);
    }
    
}
