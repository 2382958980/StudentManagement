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

class inputStudent extends JPanel {
    public JComboBox<String> classComboBox = new JComboBox<>();
    public DefaultComboBoxModel<String> classComboBoxModel = (DefaultComboBoxModel<String>) classComboBox.getModel();
    public inputStudent() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("this look and feel doesn't work? how can it be?");
        }
        setLayout(null);
        setPreferredSize(new Dimension(890, 1080));

        JLabel titleLabel = new JLabel("录入学生信息");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));
        titleLabel.setForeground(Color.white);
        titleLabel.setBounds(270, 40, 300, 40);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(130, 161, 255));
        headerPanel.setBounds(0, 0, 890, 135);

        String[] labels = { "姓名/性别", "学号", "籍贯", "所在班级", "出生年月" };
        JTextField nameTextField = new JTextField();
        JTextField idTextField = new JTextField();
        JTextField hometownTextField = new JTextField();
        JDateChooser birthDateChooser = new JDateChooser();
        String[] classOptions = { "班级A", "班级B", "班级C" };
        JRadioButton maleRadioButton = new JRadioButton("男");
        JRadioButton femaleRadioButton = new JRadioButton("女");

        ArrayList<String> arr =new ArrayList<String>();
        for(Grade g : mainWindow.gradeArray)
        {
            arr.add(g.name);
        }
        String[] array = arr.toArray(new String[0]);
        for(String i : array)
        {
            System.out.println(i);
            classComboBoxModel.addElement(i);
        }
        //classComboBox = new JComboBox<>(array);

        JLabel label2 = new JLabel("性别");
            label2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
            label2.setForeground(new Color(57, 96, 213));
            label2.setBounds(400, 170, 200, 40);
            //add(label2);

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
            label.setForeground(new Color(57, 96, 213));
            label.setBounds(120, 170 + 60 * i, 200, 40);
            add(label);

            if (i == 0) {
                nameTextField.setBounds(260, 170 + 60 * i, 200, 40);
                nameTextField.setForeground(new Color(57, 96, 213));
                nameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
                add(nameTextField);
            } else if (i == 1) {
                idTextField.setBounds(260, 170 + 60 * i, 300, 40);
                idTextField.setForeground(new Color(57, 96, 213));
                idTextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
                add(idTextField);
            } else if (i == 2) {
                ButtonGroup genderGroup = new ButtonGroup();
                genderGroup.add(maleRadioButton);
                genderGroup.add(femaleRadioButton);
                maleRadioButton.setBounds(480, 170, 40, 40);
                maleRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
                femaleRadioButton.setBounds(525, 170, 40, 40);
                femaleRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
                add(maleRadioButton);
                add(femaleRadioButton);
            } else if (i == 3) {
                hometownTextField.setBounds(260, 170 + 60 * (i-1), 300, 40);
                hometownTextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
                hometownTextField.setForeground(new Color(57, 96, 213));
                add(hometownTextField);
            } else if (i == 4) {
                birthDateChooser.setBounds(260, 170 + 60 * i, 160, 40);
                birthDateChooser.setFont(new Font("微软雅黑", Font.PLAIN, 14));
                JTextField dateTextField = (JTextField) birthDateChooser.getDateEditor().getUiComponent();
dateTextField.setEditable(false); 
                add(birthDateChooser);
                classComboBox.setBounds(260, 170 + 60 * (i-1), 300, 40);
                classComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
                classComboBox.setForeground(new Color(57, 96, 213));
                add(classComboBox);
            }
        }

        JButton submitButton = new JButton("录入");
        submitButton.setBounds(460, 170 + 60 * 4, 100, 40);
        submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        submitButton.setForeground(new Color(57, 96, 213));
        
        JTextArea textArea = new JTextArea("暂无本次录入记录\n");
        textArea.setEditable(false);
        textArea.setBackground(Color.white);
        textArea.setBounds(50, 580, 800, 500);
        textArea.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        textArea.setForeground(new Color(130, 161, 255));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 580, 600, 180);
        scrollPane.setBorder(null);
        add(scrollPane);

        DrawingPanel drawingPanel = new DrawingPanel();
        drawingPanel.setBounds(50, 500, 600, 2);

        JLabel recordsLabel = new JLabel("学生录入记录");
        recordsLabel.setFont(new Font("微软雅黑", Font.PLAIN, 24));
        recordsLabel.setForeground(new Color(57, 96, 213));
        recordsLabel.setBounds(265, 500, 300, 60);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentName = nameTextField.getText();
                String studentID = idTextField.getText();
                String studentHometown = hometownTextField.getText();
                String selectedClass = (String) classComboBox.getSelectedItem();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date currentTime = new Date();
                String formattedTime = sdf.format(currentTime);

                if(textArea.getText().equals("暂无本次录入记录\n"))
                {
                    textArea.setText("");
                }
                textArea.append("["+formattedTime+"]");
                if (!studentID.matches("[0-9]+")) 
                {
                    textArea.append("录入失败：学号输入不合法\n");
                } 
                else if(studentName.equals(""))
                {
                    textArea.append("录入失败：学生姓名为空\n");
                }
                else if(!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())
                {
                    textArea.append("录入失败：未选择学生性别\n");
                }
                else if(studentHometown.equals(null))
                {
                    textArea.append("录入失败：学生籍贯为空\n");
                }
                else if(birthDateChooser.getDate()==null)
                {
                    textArea.append("录入失败：学生出生日期为空\n");
                }
                else if(selectedClass.equals(""))
                {
                    textArea.append("录入失败：学生所在班级为空\n");
                }
                else
                {
                    textArea.append("学号为"+studentID+"的学生录入成功\n");
                    
                    String gender=maleRadioButton.isSelected()?"男":"女";
                    mainWindow.studentArray.add(new Student(studentName, studentID, gender, studentHometown, birthDateChooser.getDate(), selectedClass));
                    Sheet s=mainWindow.studentWorkbook.getSheetAt(0);
                    int lastRowNum = s.getLastRowNum();
                    Row newRow = s.createRow(lastRowNum + 1);
                    Cell cell1 = newRow.createCell(0);
                    cell1.setCellValue(studentName);
                    cell1 = newRow.createCell(1);
                    cell1.setCellValue(studentID);
                    cell1 = newRow.createCell(2);
                    cell1.setCellValue(gender);
                    cell1 = newRow.createCell(3);
                    cell1.setCellValue(studentHometown);
                    Date date = birthDateChooser.getDate();
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
                    String dateString = sdf2.format(date);
                    cell1 = newRow.createCell(4);
                    cell1.setCellValue(dateString);
                    cell1 = newRow.createCell(5);
                    cell1.setCellValue(selectedClass);      

                    nameTextField.setText("");
                    idTextField.setText("");
                    hometownTextField.setText("");
                    birthDateChooser.setDate(null);
                    classComboBox.setSelectedItem(classOptions[0]);
                }
            }
        });

        add(drawingPanel);
        add(submitButton);
        add(titleLabel);
        add(recordsLabel);
        add(headerPanel);
    }
}