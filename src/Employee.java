import java.util.Random;

public class Employee {
    private String name;
    private String familyName;
    private int salary;
    private String department;


    Employee(String name, String familyName, Integer salary, String department) {
        this.name = name;
        this.familyName = familyName;
        this.salary = salary;
        this.department = department;
    }

    @Override
    public String toString() {
        return this.name + " " + this.familyName + " " + this.salary;
    }

    public String getName() {
        return this.name;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getDepartment() {
        return this.department;
    }

    public int getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
