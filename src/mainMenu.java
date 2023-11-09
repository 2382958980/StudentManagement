import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainMenu extends JPanel {
    ImageIcon input = new ImageIcon(getClass().getClassLoader().getResource("input.png"));
    ImageIcon edit = new ImageIcon(getClass().getClassLoader().getResource("edit.png"));
    ImageIcon search = new ImageIcon(getClass().getClassLoader().getResource("search.png"));
    ImageIcon input2 = new ImageIcon(getClass().getClassLoader().getResource("input2.png"));
    ImageIcon edit2 = new ImageIcon(getClass().getClassLoader().getResource("edit2.png"));
    ImageIcon search2 = new ImageIcon(getClass().getClassLoader().getResource("search2.png"));
    public menuButton b1 = new menuButton("录入",input2);
    public menuButton b2 = new menuButton("编辑",edit2);
    public menuButton b3 = new menuButton("查询",search2);
    private JPanel jp1=new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JLabel jl1=new JLabel("学生管理系统");
    private Font customFont = new Font("微软雅黑", Font.PLAIN, 16);
    private Font BoldFont = new Font("微软雅黑", Font.BOLD, 16);
    private Font BoldFont2 = new Font("微软雅黑", Font.BOLD, 20);
    private menuButton currentSelectedButton = null;

    

    public class menuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            b1.setBackground(new Color(57, 96, 213));
            b2.setBackground(new Color(57, 96, 213));
            b3.setBackground(new Color(57, 96, 213));
            b1.setForeground(Color.white);
            b2.setForeground(Color.white);
            b3.setForeground(Color.white);
            b1.setFont(customFont);
            b2.setFont(customFont);
            b3.setFont(customFont);
            b1.setIcon(input2);
            b2.setIcon(edit2);
            b3.setIcon(search2);
            currentSelectedButton = (menuButton) e.getSource();
            currentSelectedButton.setBackground(Color.white);
            currentSelectedButton.setForeground(Color.gray);
            currentSelectedButton.setFont(BoldFont);
            if(currentSelectedButton.equals(b1))
            {
                b1.setIcon(input);
            }
            else if(currentSelectedButton.equals(b2))
            {
                b2.setIcon(edit);
            }
            else
            {
                b3.setIcon(search);
            }
        }
    }
    public mainMenu() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        b1.addActionListener(new menuListener());
        b2.addActionListener(new menuListener());
        b3.addActionListener(new menuListener());
        jl1.setFont(BoldFont2);
        jl1.setForeground(Color.white);
        jl1.setVerticalAlignment(SwingConstants.CENTER);
        jp1.setPreferredSize(new Dimension(140, 80));
        jp1.setBackground(new Color(57, 96, 213));
        JPanel jp2=new JPanel();
        jp2.setPreferredSize(new Dimension(140, 40));
        jp2.setBackground(new Color(57, 96, 213));
        JLabel jl2=new JLabel("Version 1.0");
        jl2.setForeground(Color.white);
        add(jp2);
        add(jp1);
        jp1.add(jl1);
        jp1.add(jl2);
        add(b1);
        add(b2);
        add(b3);
    }
}
