import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.toedter.calendar.JDateChooser;

class DrawingPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(57, 96, 213));
        g.fillRect(0, 0, 800, 800);
    }
}

public class inputGrade extends JPanel {
    public JButton jb2=new JButton("录入");
    public String[] labels = { "班级名称", "专业", "班级开始时间" };
    public JTextField textField = new JTextField();
    public String[] professions = { "交通运输工程", "电气工程","电子信息工程", "计算机与信息技术", 
                "土木建筑工程", "数学与统计","马克思主义","新闻传播","法学" };
    public JComboBox<String> professionComboBox = new JComboBox<>(professions);
    public JDateChooser dateChooser = new JDateChooser();
    public inputGrade() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("this look and feel doesn't work? how can it be?");
        }
        setLayout(null);
        setPreferredSize(new Dimension(890, 1080));

        // 创建中间的标签
        JLabel centerLabel = new JLabel("录入班级信息");
        centerLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));
        centerLabel.setForeground(Color.white);
        centerLabel.setBounds(270, 40, 300, 40);

        JPanel back1 = new JPanel();
        back1.setBackground(new Color(130, 161, 255));
        back1.setBounds(0, 0, 890, 135);

        

        for (int i = 0; i < labels.length; i++) {
            JLabel leftLabel = new JLabel(labels[i]);
            leftLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
            leftLabel.setForeground(new Color(57, 96, 213));
            leftLabel.setBounds(120, 170 + 60 * i, 200, 40);
            add(leftLabel);

            if (i == 0) {
                textField.setBounds(260, 170 + 60 * i, 300, 40);
                textField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
                textField.setForeground(new Color(57, 96, 213));
                add(textField);
            } else if (i == 1) {
                professionComboBox.setBounds(260, 170 + 60 * i, 300, 40);
                professionComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
                professionComboBox.setForeground(new Color(57, 96, 213));
                add(professionComboBox);
            } else if (i == 2) {
                dateChooser.setBounds(260, 170 + 60 * i, 160, 40);
                dateChooser.setFont(new Font("微软雅黑", Font.PLAIN, 14));
                JTextField dateTextField = (JTextField) dateChooser.getDateEditor().getUiComponent();
dateTextField.setEditable(false); 
                //dateChooser.setForeground(new Color(57, 96, 213));
                add(dateChooser);
            }
        }
        
        jb2.setBounds(460, 170 + 60*2, 100, 40);
        jb2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jb2.setForeground(new Color(57, 96, 213));

        

        JTextArea textArea = new JTextArea("暂无本次录入记录");
        textArea.setEditable(false);
        textArea.setBackground(Color.white);
        textArea.setBounds(50, 460, 800, 500);
        textArea.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        textArea.setForeground(new Color(130, 161, 255));

        DrawingPanel drawingPanel = new DrawingPanel();
        drawingPanel.setBounds(50,380,600,2);

        JLabel centerLabel2 = new JLabel("班级录入记录");
        centerLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 24));
        centerLabel2.setForeground(new Color(57, 96, 213));
        centerLabel2.setBounds(265, 380, 300, 60);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 460, 600, 300);
        scrollPane.setBorder(null);
        add(scrollPane);

        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                String a=textField.getText();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date currentTime = new Date();
                String formattedTime = sdf.format(currentTime);
                //textArea.append(dateChooser.getDate().toString());
                if(textArea.getText().equals("暂无本次录入记录"))
                {
                    textArea.setText("");
                }
                textArea.append("["+formattedTime+"]");
                if(a.equals(""))
                {
                    textArea.append("录入失败：班级名称为空\n");
                }
                else if(dateChooser.getDate()==null)
                {
                    textArea.append("录入失败：开班日期为空\n");
                }
                else
                {
                    textArea.append("班级“" + a + "”已录入成功\n");
                    
 

                    Sheet s=mainWindow.gradeWorkbook.getSheetAt(0);
                    int lastRowNum = s.getLastRowNum();
                    Row newRow = s.createRow(lastRowNum + 1);
                    Cell cell1 = newRow.createCell(0);
                    cell1.setCellValue(a);
                    cell1 = newRow.createCell(1); 
                    cell1.setCellValue(professionComboBox.getSelectedItem().toString());
                    cell1 = newRow.createCell(2);
                    Date date = dateChooser.getDate();
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
                    String dateString = sdf2.format(date);
                    cell1.setCellValue(dateString);
                    dateChooser.setDate(null);
                    professionComboBox.setSelectedItem("交通运输工程");
                    textField.setText("");
                } 
            }
        });
        
        add(drawingPanel);
        add(jb2);
        add(centerLabel);
        add(centerLabel2);
        add(back1);
    }
}
