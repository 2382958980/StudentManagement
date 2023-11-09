import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class menuButton extends JButton {
    private Font customFont = new Font("微软雅黑", Font.PLAIN, 16);

    public menuButton(String text,Icon ico) {
        super(text,ico);
        setPreferredSize(new Dimension(140, 70));
        setBorderPainted(false);
        setFocusPainted(false);
        setFont(customFont);
        setBackground(new Color(57, 96, 213));
        setForeground(Color.white);
        addMouseListener(new MouseAdapter() 
        {
            Color defColor=new Color(57, 96, 213);
            @Override
            public void mouseEntered(MouseEvent e) {
                defColor=getBackground();
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                if(defColor.equals(new Color(57, 96, 213)))
                {
                    setBackground(new Color(130,161,255));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                if(getBackground().equals(new Color(130,161,255)))
                {
                    setBackground(defColor);
                }
            }
        });
    }
}
