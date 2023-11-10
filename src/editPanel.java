import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



public class editPanel extends JPanel {
    public JPanel tab1=new JPanel();
    public JPanel tab2=new JPanel();
    public editPanel() {
        setLayout(null);
        FlowLayout a=new FlowLayout();
        a.setHgap(0);
        a.setVgap(0);
        tab1.setLayout(a);
        tab2.setLayout(a);
        tab1.setForeground(new Color(57, 96, 213));
        tab2.setForeground(new Color(57, 96, 213));
        
        JTabbedPane tabbedPane = new JTabbedPane();
        JScrollPane scrollPane1 = new JScrollPane(tab1);
        JScrollPane scrollPane2 = new JScrollPane(tab2);
        tabbedPane.addTab("编辑班级", scrollPane1);
        tabbedPane.addTab("编辑学生", scrollPane2);
        tabbedPane.setBounds(0, 102, 1000, 650);
        tabbedPane.setForegroundAt(0, new Color(57, 96, 213));
        tabbedPane.setForegroundAt(1, new Color(57, 96, 213));
        tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        add(tabbedPane);
        JLabel centerLabel = new JLabel("编辑学生及班级信息");
        centerLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));
        centerLabel.setForeground(Color.white);
        centerLabel.setBounds(500, 40, 300, 40);

        JPanel back1 = new JPanel();
        back1.setBackground(new Color(130, 161, 255));
        back1.setBounds(0, 0, 1400, 135);
        add(centerLabel);
        add(back1);
        
    }
}
