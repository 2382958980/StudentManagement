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
import java.util.Arrays;
import java.util.Date;

public class mainWindow extends JFrame
{
    public inputPanel inputP=new inputPanel();
    public editPanel editP=new editPanel();
    public searchPanel searchP=new searchPanel();
    public static Workbook studentWorkbook=new XSSFWorkbook();//存储学生信息的表格
    public static Workbook gradeWorkbook=new XSSFWorkbook();//存储班级信息的表格
    public static ArrayList<Student> studentArray=new ArrayList<Student>();
    public static ArrayList<Grade> gradeArray=new ArrayList<Grade>();
    public static ArrayList<String> attribute = new ArrayList<>(Arrays.asList("姓名", "学号", "籍贯", "出生日期", "所在班级", "性别"));

    int addButtonCount=1;
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
        add(searchP,BorderLayout.CENTER);
        inputP.setVisible(false);
        editP.setVisible(false);
        searchP.setVisible(false);
        menu1.setPreferredSize(new Dimension(140, getHeight()));
        menu1.setBackground(new Color(57, 96, 213));
        inputP.setPreferredSize(new Dimension(1780, getHeight()));
        //以下为录入功能的实现
        menu1.b1.addActionListener((ActionEvent e) ->{
            add(inputP,BorderLayout.CENTER);
            inputP.setVisible(true);
            editP.setVisible(false);
            searchP.setVisible(false);
        });
        //以下为编辑功能的实现
        menu1.b2.addActionListener((ActionEvent e) ->{
            add(editP,BorderLayout.CENTER);
            inputP.setVisible(false);
            editP.setVisible(true);
            searchP.setVisible(false);
            int count=1;
            int count2=1;
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

        JPanel cont2=new JPanel();
        JLabel amns2=new JLabel("   姓名                     学号                    性别                            籍贯                                  出生日期                    所在班级");
        amns2.setBounds(40,0,1000,20);
        amns2.setForeground(new Color(57,96,213));
        amns2.setFont(new Font("微软雅黑", Font.BOLD, 14));
        cont2.add(amns2);
        cont2.setLayout(null);
        cont2.setPreferredSize(new Dimension(1000,25));
        cont2.setBackground(new Color(200,221,242));
        editP.tab2.add(cont2);

        editP.tab1.setPreferredSize(new Dimension(800,50+mainWindow.gradeArray.size()*50));
        editP.tab2.setPreferredSize(new Dimension(800,50+mainWindow.studentArray.size()*50));
        
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
                editP.tab1.setPreferredSize(new Dimension(800,50+mainWindow.gradeArray.size()*50));
                editP.tab1.remove(GradeInfo);
                editP.tab1.revalidate();
                editP.tab1.repaint();
                mainWindow.gradeArray.remove(d2.index);
                Sheet sbjavaSheet=gradeWorkbook.getSheetAt(0);
                sbjavaSheet.shiftRows(d2.index + 1, sbjavaSheet.getLastRowNum(), -1, true, false);
            });
            GradeInfo.add(d2);
            count++;
        }
        for(Student s : mainWindow.studentArray)
        {
            //System.out.println(1);
            JPanel StudentInfo=new JPanel();
            StudentInfo.setLayout(null);
            StudentInfo.setPreferredSize(new Dimension(1000,50));
            StudentInfo.setBackground(count2 % 2 == 0 ? new Color(130, 161, 255) : Color.white);
            JTextField[] st=new JTextField[6];
            st[0]=new JTextField(s.name);
            st[1]=new JTextField(s.account);
            st[2]=new JTextField(s.gender);
            st[3]=new JTextField(s.location);
            st[4]=new JTextField(sdf.format(s.birthDate));
            st[5]=new JTextField(s.belongs);
            int ind=0;
            editbutton d=new editbutton("修改",count2-1);
            d.setFont(new Font("微软雅黑", Font.BOLD, 18));
            d.setForeground(new Color(57,96,213));
            d.setBounds(820,5,70,40);
            JRadioButton maleRadioButton = new JRadioButton("男");
            JRadioButton femaleRadioButton = new JRadioButton("女");
            ButtonGroup genderGroup = new ButtonGroup();
            genderGroup.add(maleRadioButton);
            genderGroup.add(femaleRadioButton);
            maleRadioButton.setBounds(280,0,50,50);
            femaleRadioButton.setBounds(330,0,50,50);
            maleRadioButton.setBackground(d.index % 2 != 0 ? new Color(130, 161, 255) : Color.white);
            femaleRadioButton.setBackground(d.index % 2 != 0 ? new Color(130, 161, 255) : Color.white);
            maleRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
            femaleRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
            maleRadioButton.setForeground(new Color(57,96,213));
            femaleRadioButton.setForeground(new Color(57,96,213));
            StudentInfo.add(maleRadioButton);
            StudentInfo.add(femaleRadioButton);
            maleRadioButton.setVisible(false);
            femaleRadioButton.setVisible(false);
            JComboBox<String> classComboBox = new JComboBox<>();
            DefaultComboBoxModel<String> classComboBoxModel = (DefaultComboBoxModel<String>) classComboBox.getModel();
            ArrayList<String> arr =new ArrayList<String>();
            for(Grade g : mainWindow.gradeArray)
            {
                arr.add(g.name);
            }
            String[] array = arr.toArray(new String[0]);
            for(String i : array)
            {
                //System.out.println(i);
                classComboBoxModel.addElement(i);
            }
            classComboBox.setBounds(700,0,80,50);
            classComboBox.setBackground(count2 % 2 == 0 ? new Color(130, 161, 255) : Color.white);
            classComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
            classComboBox.setForeground(new Color(57,96,213));
            classComboBox.setVisible(false);
            StudentInfo.add(classComboBox);
            d.addActionListener((ActionEvent am) ->
            {
                if(d.getText().equals("修改"))
                {
                    d.setText("确认");
                    for(int i=0;i<6;i++)
                    {
                        st[i].setEditable(true);
                        st[i].setBorder(new LineBorder(d.index % 2 == 0 ? new Color(130, 161, 255) : Color.white));
                        st[i].setForeground(new Color(80,80,80));
                    }
                    st[1].setEditable(false);
                    st[1].setBorder(new EmptyBorder(0, 0, 0, 0));
                    st[1].setForeground(new Color(57,96,213));
                    st[2].setVisible(false);
                    st[5].setVisible(false);
                    classComboBox.setVisible(true);
                    maleRadioButton.setVisible(true);
                    femaleRadioButton.setVisible(true);
                    if(st[2].getText().equals("男"))
                    {
                        maleRadioButton.setSelected(true);
                    }
                    else
                    {
                        femaleRadioButton.setSelected(true);
                    }
                }
                else
                {
                    if(isValidFormat("yyyy年MM月dd日", st[4].getText()))
                    {
                        maleRadioButton.setVisible(false);
                        femaleRadioButton.setVisible(false);
                        st[2].setVisible(true);
                        d.setText("修改");
                        st[5].setVisible(true);
                        classComboBox.setVisible(false);
                        st[5].setText(classComboBox.getSelectedItem().toString());
                        for(int i=0;i<6;i++)
                        {
                            st[i].setBorder(new EmptyBorder(0, 0, 0, 0));
                            st[i].setEditable(false);
                            st[i].setForeground(new Color(57,96,213));
                        }
                        try
                        {
                            if(maleRadioButton.isSelected())
                            {
                                Student s1=new Student(st[0].getText(), st[1].getText(), "男", st[3].getText(), sdf.parse(st[4].getText()), classComboBox.getSelectedItem().toString());
                                mainWindow.studentArray.set(d.index,s1);
                                st[2].setText("男");
                            }
                            if(femaleRadioButton.isSelected())
                            {
                                Student s1=new Student(st[0].getText(), st[1].getText(), "女", st[3].getText(), sdf.parse(st[4].getText()), classComboBox.getSelectedItem().toString());
                                mainWindow.studentArray.set(d.index,s1);
                                st[2].setText("女");
                            }
                        }
                        catch(Exception abc)
                        {
                            System.out.println("It can't be wrong");
                        }

                        Sheet sbjavaSheet=studentWorkbook.getSheetAt(0);
                        Row sbjavaRow=sbjavaSheet.getRow(d.index);
                        
                        for(int i=0;i<6;i++)
                        {
                            Cell sbjavaCell=sbjavaRow.getCell(i);
                            sbjavaCell.setCellValue(st[i].getText());
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(d, "日期不合法！", "警告",JOptionPane.WARNING_MESSAGE);  
                    }
                }
            });
            editbutton d2=new editbutton("删除",count2-1);
            d2.setFont(new Font("微软雅黑", Font.BOLD, 18));
            d2.setForeground(new Color(57,96,213));
            d2.setBounds(900,5,70,40);
            d2.addActionListener((ActionEvent sbjava) ->{
                editP.tab1.setPreferredSize(new Dimension(800,50+mainWindow.gradeArray.size()*50));
                editP.tab2.remove(StudentInfo);
                editP.tab2.revalidate();
                editP.tab2.repaint();
                mainWindow.studentArray.remove(d2.index);
                Sheet sbjavaSheet=studentWorkbook.getSheetAt(0);
                sbjavaSheet.shiftRows(d2.index + 1, sbjavaSheet.getLastRowNum(), -1, true, false);
            });
            StudentInfo.add(d);
            StudentInfo.add(d2);
            for(JTextField j : st)
            {
                j.setEditable(false);
                j.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                j.setBorder(new EmptyBorder(0,0,0,0));
                j.setForeground(new Color(57,96,213));
                j.setBackground(count2 % 2 == 0 ? new Color(130, 161, 255) : Color.white);
                if(ind==0)
                {
                    j.setBounds(50,0,75,50);
                }
                else if(ind<=1&&ind>0)
                {
                    j.setBounds(ind*140,0,140,50);
                }
                else if(ind==2)
                {
                    j.setBounds(ind*140,0,50,50);
                }
                else if(ind>2&&ind<4)
                {
                    j.setBounds(ind*100+100,0,100,50);
                }
                else
                {
                    j.setBounds(ind*170-140,0,140,50);
                }

                if(ind==5)
                {
                    j.setBounds(ind*170-150,0,80,50);
                }
                StudentInfo.add(j);
                ind++;
            }
            count2++;
            editP.tab2.add(StudentInfo);
        }
        });
        //以下为查询功能的实现
        menu1.b3.addActionListener((ActionEvent e) ->{
            attribute = new ArrayList<>(Arrays.asList("姓名", "学号", "籍贯", "出生日期", "所在班级", "性别"));
            add(searchP,BorderLayout.CENTER);
            inputP.setVisible(false);
            editP.setVisible(false);
            searchP.setVisible(true);
            searchP.searchCondition.removeAll();
            
            search sa=new search(attribute);
            searchP.searchCondition.add(sa);
            sa.addButton.addActionListener((ActionEvent fEvent) ->{
                addButtonCount++;
                if(addButtonCount==6)
                {
                    sa.addButton.setVisible(false);
                }
                else
                {
                    sa.addButton.setVisible(true);
                }
                attribute = new ArrayList<>(Arrays.asList("姓名", "学号", "籍贯", "出生日期", "所在班级", "性别"));
                Component[] components = searchP.searchCondition.getComponents();
                for (Component component : components) {
                    if (component instanceof search) 
                    {
                        String a=((search)component).jb.getSelectedItem().toString();
                        attribute.remove(a);
                    }
                }
                search sb=new search(attribute);
                sb.addButton.setText("<html><div style='text-align: center; vertical-align: middle;'>-</div></html>");
                sb.addButton.addActionListener((ActionEvent g) ->{
                    //attribute.add(sb.jb.getSelectedItem().toString());
                    searchP.searchCondition.remove(sb);
                    searchP.revalidate();
                    searchP.repaint();
                    addButtonCount--;
                    sa.addButton.setVisible(true);
                });
                searchP.searchCondition.add(sb);
                searchP.revalidate();
                searchP.repaint();
            });
            JButton se=new JButton("查询");
                se.setBounds(505,170,100,40);
                se.setFont(new Font("微软雅黑", Font.PLAIN, 20));
                se.setForeground(new Color(57, 96, 213));
                se.addActionListener((ActionEvent ae) ->{
                searchP.model.setRowCount(0);
                    for(Student s : studentArray)
                    {
                        boolean okay=true;
                        Component[] components3 = searchP.searchCondition.getComponents();
                        for (Component component : components3) 
                        {
                            if (component instanceof search) 
                            {
                                String att=((search)component).jb.getSelectedItem().toString();
                                if(att.equals("姓名"))
                                {
                                    if(!((search)component).jtf.getText().equals(s.name))
                                    {
                                        okay=false;
                                    }
                                }
                                else if(att.equals("学号"))
                                {
                                    if(!((search)component).jtf.getText().equals(s.account))
                                    {
                                        okay=false;
                                    }
                                }
                                else if(att.equals("籍贯"))
                                {
                                    if(!((search)component).jtf.getText().equals(s.location))
                                    {
                                        okay=false;
                                    }
                                }
                                else if(att.equals("性别"))
                                {
                                    if(!((search)component).jtf.getText().equals(s.gender))
                                    {
                                        okay=false;
                                    }
                                }
                                else if(att.equals("所在班级"))
                                {
                                    if(!((search)component).jtf.getText().equals(s.belongs))
                                    {
                                        okay=false;
                                    }
                                }
                                else if(att.equals("出生日期"))
                                {
                                    try 
                                    {
                                        if(!sdf.parse(((search)component).jtf.getText()).equals(s.birthDate))
                                        {
                                            okay=false;
                                        }
                                    } 
                                    catch (ParseException e1) 
                                    {
                                    }
                                }
                            }
                        }
                        if(okay)
                        {
                            searchP.model.addRow(new Object[]{s.name,s.account,s.gender,s.location,sdf.format(s.birthDate),s.belongs});
                        }
                    }
                });
                searchP.add(se);
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
            for(Cell cell :row)
            {
                cell.setCellType(CellType.STRING);
            }

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
            for(Cell cell :row)
            {
                cell.setCellType(CellType.STRING);
            }
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
            System.out.println("this look and feel doesn't work? how can it be?");
        }
        SwingConsole.run(window, 1920, 1080);
    }
}