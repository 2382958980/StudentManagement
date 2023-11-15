import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class search extends JPanel
{
    DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
    JComboBox jb=new JComboBox<>(comboBoxModel);
    JTextField jtf=new JTextField();
    JButton addButton=new JButton("<html><div style='text-align: center; vertical-align: middle;'>+</div></html>");
    public search(ArrayList<String> a)
    {
        jb.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jb.setForeground(new Color(57, 96, 213));
        jtf.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jtf.setForeground(new Color(57, 96, 213));
        addButton.setForeground(new Color(57,96,213));
        setLayout(null);
        setPreferredSize(new Dimension(580,40));
        //setBorder(new LineBorder(Color.blue));
        for (String item : a) 
        {
            comboBoxModel.addElement(item);
        }
        jb.setBounds(0,0,100,40);
        jtf.setBounds(110,0,300,40);
        add(jb);
        add(jtf);
        addButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        Insets insets = new Insets(-2, 0, 0, 0);
        addButton.setMargin(insets);
        addButton.setBounds(415,5,30,30);
        add(addButton);
    }
}
