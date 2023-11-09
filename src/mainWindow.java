import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class mainWindow extends JFrame
{
    public inputPanel inputP=new inputPanel();
    public editPanel editP=new editPanel();
    public static Workbook studentWorkbook=new XSSFWorkbook();//存储学生信息的表格
    public static Workbook gradeWorkbook=new XSSFWorkbook();//存储班级信息的表格
    public static ArrayList<Student> studentArray=new ArrayList<Student>();
    public static ArrayList<Grade> gradeArray=new ArrayList<Grade>();

    private mainMenu menu1=new mainMenu();
    private BorderLayout bl=new BorderLayout();

    public static boolean isValidFormat(String format, String value) {
        DateFormat df = new SimpleDateFormat(format);
        df.setLenient(false);
        try {
            df.parse(value);
            if(value.length()==11)
            return true;
            else
            return false;
        } catch (ParseException e) {
            return false;
        }
    }
    public mainWindow() 
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        bl.setHgap(0);
        bl.setVgap(0);
        
        setLayout(bl);
        add(menu1, BorderLayout.WEST);
        add(editP,BorderLayout.CENTER);
        add(inputP,BorderLayout.CENTER);
        inputP.setVisible(false);
        editP.setVisible(false);
        menu1.setPreferredSize(new Dimension(140, getHeight()));
        menu1.setBackground(new Color(57, 96, 213));
        inputP.setPreferredSize(new Dimension(1780, getHeight()));
        menu1.b1.addActionListener((ActionEvent e) ->{
            add(inputP,BorderLayout.CENTER);
            inputP.setVisible(true);
            editP.setVisible(false);
        });
        //以下为编辑班级panel
        menu1.b2.addActionListener((ActionEvent e) ->{
            add(editP,BorderLayout.CENTER);
            inputP.setVisible(false);
            editP.setVisible(true);
            int count=1;
            editP.tab1.removeAll();
            editP.tab2.removeAll();

        JPanel cont=new JPanel();
        JLabel amns=new JLabel("班级名称                                            专业                                                 开班日期");
        amns.setBounds(40,0,1000,20);
        amns.setForeground(new Color(57,96,213));
        amns.setFont(new Font("微软雅黑", Font.BOLD, 14));
        cont.add(amns);
        cont.setLayout(null);
        cont.setPreferredSize(new Dimension(1000,25));
        cont.setBackground(new Color(200,221,242));
        editP.tab1.add(cont);
        editP.tab1.setPreferredSize(new Dimension(800,200+mainWindow.gradeArray.size()*50));
        
        for(Grade g : mainWindow.gradeArray)
        {
            JPanel GradeInfo=new JPanel();
            GradeInfo.setLayout(null);
            GradeInfo.setBounds(0,count*50-25,1000,50);
            GradeInfo.setPreferredSize(new Dimension(1000,50));
            GradeInfo.setBackground(count % 2 == 0 ? new Color(130, 161, 255) : Color.white);
            //GradeInfo.setBorder(new LineBorder(Color.RED));
            JTextField a=new JTextField(g.name);
            JTextField b=new JTextField(g.department);
            JTextField c=new JTextField(sdf.format(g.startDate));
            a.setBorder(new EmptyBorder(0, 0, 0, 0));
            b.setBorder(new EmptyBorder(0, 0, 0, 0));
            c.setBorder(new EmptyBorder(0, 0, 0, 0));
            a.setEditable(false);
            b.setEditable(false);
            c.setEditable(false);
            a.setForeground(new Color(57,96,213));
            b.setForeground(new Color(57,96,213));
            c.setForeground(new Color(57,96,213));
            a.setBackground(count % 2 == 0 ? new Color(130, 161, 255) : Color.white);
            b.setBackground(count % 2 == 0 ? new Color(130, 161, 255) : Color.white);
            c.setBackground(count % 2 == 0 ? new Color(130, 161, 255) : Color.white);   
            a.setFont(new Font("微软雅黑", Font.PLAIN, 20));
            b.setFont(new Font("微软雅黑", Font.PLAIN, 20));
            c.setFont(new Font("微软雅黑", Font.PLAIN, 20));
            a.setBounds(30,0,200,50);
            b.setBounds(230,0,160,50);
            c.setBounds(450,0,300,50);
            editP.tab1.add(GradeInfo);
            GradeInfo.add(a);GradeInfo.add(b);GradeInfo.add(c);
            editbutton d=new editbutton("修改",count-1);
            d.setFont(new Font("微软雅黑", Font.BOLD, 18));
            d.setForeground(new Color(57,96,213));
            d.setBounds(820,5,70,40);
            d.addActionListener((ActionEvent am) ->{
                if(d.getText().equals("修改"))
                {
                    d.setText("确认");
                    a.setEditable(true);
                    b.setEditable(true);
                    c.setEditable(true);
                    a.setBorder(new LineBorder(d.index % 2 == 0 ? new Color(130, 161, 255) : Color.white));
                    b.setBorder(new LineBorder(d.index % 2 == 0 ? new Color(130, 161, 255) : Color.white));
                    c.setBorder(new LineBorder(d.index % 2 == 0 ? new Color(130, 161, 255) : Color.white));
                    a.setForeground(new Color(80,80,80));
                    b.setForeground(new Color(80,80,80));
                    c.setForeground(new Color(80,80,80));
                }
                else
                {
                    if(isValidFormat("yyyy年MM月dd日", c.getText()))
                    {
                        d.setText("修改");
                        a.setBorder(new EmptyBorder(0, 0, 0, 0));
                        b.setBorder(new EmptyBorder(0, 0, 0, 0));
                        c.setBorder(new EmptyBorder(0, 0, 0, 0));
                        a.setEditable(false);
                        b.setEditable(false);
                        c.setEditable(false);
                        a.setForeground(new Color(57,96,213));
                        b.setForeground(new Color(57,96,213));
                        c.setForeground(new Color(57,96,213));
                        try
                        {
                            Grade g1=new Grade(a.getText(), b.getText(), sdf.parse(c.getText()));
                            mainWindow.gradeArray.set(d.index, g1);
                        }
                        catch(Exception abc)
                        {
                            System.out.println("It can't be wrong");
                        }

                        Sheet sbjavaSheet=gradeWorkbook.getSheetAt(0);
                        Row sbjavaRow=sbjavaSheet.getRow(d.index);
                        Cell sbjavaCell=sbjavaRow.getCell(0);
                        sbjavaCell.setCellValue(a.getText());
                        sbjavaCell=sbjavaRow.getCell(1);
                        sbjavaCell.setCellValue(b.getText());
                        sbjavaCell=sbjavaRow.getCell(2);
                        sbjavaCell.setCellValue(c.getText());
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(d, "日期不合法！", "警告",JOptionPane.WARNING_MESSAGE);  
                    }
                }
            });
            GradeInfo.add(d);
            editbutton d2=new editbutton("删除",count-1);
            d2.setFont(new Font("微软雅黑", Font.BOLD, 18));
            d2.setForeground(new Color(57,96,213));
            d2.setBounds(900,5,70,40);
            d2.addActionListener((ActionEvent sbjava) ->{
                editP.tab1.remove(GradeInfo);
                editP.tab1.revalidate();
                editP.tab1.repaint();
                mainWindow.gradeArray.remove(g);
                Sheet sbjavaSheet=gradeWorkbook.getSheetAt(0);
                //Row sbjavaRow=sbjavaSheet.getRow(d2.index);
                sbjavaSheet.shiftRows(d2.index + 1, sbjavaSheet.getLastRowNum(), -1, true, false);
            });
            GradeInfo.add(d2);
            count++;
        }
        for(Student s : mainWindow.studentArray)
        {
            JTextField[] st=new JTextField[6];
            st[0].setText(s.name);
            st[1].setText(s.account);
            st[2].setText(s.gender);
            st[3].setText(s.location);
            st[4].setText(sdf.format(s.birthDate));
            st[5].setText(s.belongs);
            //for(JTextField j : )
        }
        });
        menu1.b3.addActionListener((ActionEvent e) ->{
            add(inputP,BorderLayout.CENTER);
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
              // 窗口关闭时写入xlsx
            try (FileOutputStream fos1 = new FileOutputStream("Students.xlsx")) 
            {
                WorkbookWriting(fos1, studentWorkbook);
            } 
            catch (Exception f) 
            {

            }
            try (FileOutputStream fos2 = new FileOutputStream("Grades.xlsx")) 
            {
                WorkbookWriting(fos2, gradeWorkbook);
            } 
            catch (Exception f) 
            {

            }
        }
        });      
    }
    //读取所有学生和班级的信息
    private static void WorkbookReading() {
        try (FileInputStream fis = new FileInputStream("Students.xlsx")) {
            studentWorkbook = new XSSFWorkbook(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (FileInputStream fis2 = new FileInputStream("Grades.xlsx")) {
            gradeWorkbook = new XSSFWorkbook(fis2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Sheet Studentsheet = studentWorkbook.getSheetAt(0);
        Sheet Gradesheet =gradeWorkbook.getSheetAt(0);
        for (Row row : Studentsheet) {
            Cell firstCell = row.getCell(0); // 获取第一个单元格
            if (firstCell !=null && firstCell.toString()!="") {
                String sname = firstCell.getStringCellValue(); // 姓名
                String saccount = row.getCell(1).getStringCellValue(); // 学号
                String sgender = row.getCell(2).getStringCellValue(); // 性别
                String slocation = row.getCell(3).getStringCellValue(); // 籍贯
                String sbirthDate = row.getCell(4).getStringCellValue(); // 出生日期
                String sbelong = row.getCell(5).getStringCellValue(); // 所在班级
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                Date date = new Date();
                try {
                    date = sdf.parse(sbirthDate);
                } catch (Exception e) {
        
                }
                Student a = new Student(sname, saccount, sgender, slocation, date, sbelong);
                studentArray.add(a);
            }
        }
        for (Row row : Gradesheet) {
            Cell firstCell = row.getCell(0); // 获取第一个单元格
            if (firstCell != null && firstCell.toString()!="") {
                String gname = firstCell.getStringCellValue();
                String gdepartment = row.getCell(1).getStringCellValue();
                String gstartDate = row.getCell(2).getStringCellValue();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                Date date = new Date();
                try {
                    date = sdf.parse(gstartDate);
                } catch (Exception e) {
        
                }
                Grade b = new Grade(gname, gdepartment, date);
                gradeArray.add(b);
            }
        }
    }

    private void WorkbookWriting(FileOutputStream fos, Workbook workbook) {
        try {
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WorkbookReading();
        mainWindow window = new mainWindow();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            
        }
        SwingConsole.run(window, 1920, 1080);
    }
}