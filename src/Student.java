import java.util.Date;

public class Student 
{
    public String name;
    public String account;
    public String gender;
    public String location;
    public Date birthDate;
    public String belongs;
    public Student(String name,String account,String gender,String location,Date birthDate,String belongs)
    {
        this.name=name;
        this.account=account;
        this.gender=gender;
        this.location=location;
        this.birthDate=birthDate;
        this.belongs=belongs;
    }
}
