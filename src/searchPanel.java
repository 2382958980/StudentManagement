import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.Flow;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.*;

public class searchPanel extends JPanel
{
    Object[][] data = {
    };
    String[] columnNames = {"姓名", "学号", "性别", "籍贯", "出生日期", "所在班级"};
    public JPanel searchCondition=new JPanel();
    public DefaultTableModel model = new DefaultTableModel(data,columnNames);
    
    public JTable table = new JTable(model);

    public searchPanel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("this look and feel doesn't work? how can it be?");
        }
    setLayout(null);
    JLabel centerLabel = new JLabel("查询及统计");
    centerLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));
    centerLabel.setForeground(Color.white);
    centerLabel.setBounds(600, 40, 300, 40);
    JPanel back1 = new JPanel();
    back1.setBackground(new Color(130, 161, 255));
    back1.setBounds(0, 0, 1400, 135);
    
    searchCondition.setBounds(40,160,460,305);
    //searchCondition.setBorder(new LineBorder(Color.red));
    searchCondition.setLayout(new FlowLayout(0,0,10));
    add(searchCondition);
    add(centerLabel);
    add(back1);
    
    JPanel searchCondition2=new JPanel();
    searchCondition2.setLayout(new FlowLayout());
    add(searchCondition2);
    searchCondition2.setBounds(40,480,600,320);
    //searchCondition2.setBorder(new LineBorder(Color.red));
    searchCondition2.setLayout(new FlowLayout(0,0,10));

    //table.setPreferredSize(new Dimension(565,400));
    table.setBackground(Color.white);
    //table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    TableColumnModel columnModel = table.getColumnModel();
    columnModel.getColumn(3).setPreferredWidth(200);
    columnModel.getColumn(4).setPreferredWidth(200); // 设置列宽
    columnModel.getColumn(5).setPreferredWidth(150);

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(565,260));
    searchCondition2.add(scrollPane);
    searchCondition2.revalidate();
    searchCondition2.repaint();

    
    String[] stu={"姓名", "学号", "籍贯", "出生日期", "所在班级", "性别"};
    JComboBox<String> classComboBox = new JComboBox<>(stu);
    classComboBox.setBounds(880,170,100,40);
    add(classComboBox);

    JButton tj=new JButton("生成统计图");
    tj.setBounds(1000,170,200,40);
    tj.addActionListener((ActionEvent efg) ->
    {
        Component[] components = getComponents();
        for (Component component : components) 
        {
            if (component instanceof ChartPanel) 
            {
                remove(component);
            }
        }
    StandardChartTheme theme = new StandardChartTheme("CN");
    theme.setExtraLargeFont(new Font("微软雅黑", Font.BOLD, 20));
    theme.setRegularFont(new Font("微软雅黑", Font.PLAIN, 15));
    theme.setLargeFont(new Font("微软雅黑", Font.PLAIN, 15));
    ChartFactory.setChartTheme(theme);
    DefaultPieDataset dataset = new DefaultPieDataset();

    for (Student student : mainWindow.studentArray) {
    String genshin = student.gender;
    switch (classComboBox.getSelectedItem().toString()) {
        case "姓名":
            genshin=student.name;
            break;
        case "学号":
            genshin=student.account;
            break;
        case "籍贯":
            genshin=student.location;
            break;
        case "出生日期":
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            genshin=sdf.format(student.birthDate);
            break;
        case "性别":
            genshin=student.gender;
            break;
        case "所在班级":
            genshin=student.belongs;
            break;
        default:
            break;
    }

    // 检查键是否存在
    if (dataset.getIndex(genshin) != -1) {
        // 键存在，执行加法操作
        int intValue = dataset.getValue(genshin).intValue();
        dataset.setValue(genshin, intValue + 1);
    } else {
        // 键不存在，添加新键并设置值为1
        dataset.setValue(genshin, 1);
    }
}
    JFreeChart chart = ChartFactory.createPieChart(
                "学生"+classComboBox.getSelectedItem().toString()+"统计图",  // 图表标题
                dataset,                           // 数据集
                true,                              // 是否显示图例
                true,                              // 是否生成工具提示
                false                              // 是否生成URL
        );
    //Font chineseFont = new Font("宋体", Font.PLAIN, 12);
    //chart.setFont(chineseFont);

    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setBounds(790,250,500,500);
    chart.setBackgroundPaint(Color.white);

    add(chartPanel);
    revalidate();
    repaint();
    });
    add(tj);
    
}
}
