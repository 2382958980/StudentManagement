import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

public class inputPanel extends JPanel
{
    public inputStudent inputs=new inputStudent();
    public inputGrade inputg=new inputGrade();
    public inputPanel()
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("this look and feel doesn't work? how can it be?");
        }
        setLayout(new BorderLayout());
        inputg.jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {      
                String a=inputg.textField.getText();
                if(!a.equals("")&&!inputg.professionComboBox.getSelectedItem().toString().equals("")&&inputg.dateChooser.getDate()!=null)
                mainWindow.gradeArray.add(new Grade(a, inputg.professionComboBox.getSelectedItem().toString(), inputg.dateChooser.getDate()));
                ArrayList<String> arr =new ArrayList<String>();
                for(Grade g : mainWindow.gradeArray)
                {
                    arr.add(g.name);
                }
                String[] array = arr.toArray(new String[0]);
                inputs.classComboBoxModel.removeAllElements();
                for(String i : array)
                {
                    //System.out.println(i);
                    inputs.classComboBoxModel.addElement(i);
                }
                
            }
        });
        JSplitPane jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,inputg,inputs);
        jsp.setResizeWeight(0.5);
        jsp.setDividerLocation(0.5);
        jsp.setEnabled(false);
        //Border border = BorderFactory.createLineBorder(Color.RED, 2);

        // 应用边框到面板
        //setBorder(border);
        add(jsp,BorderLayout.CENTER);
    }
}
