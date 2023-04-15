import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Employee[] employees = new Employee[10];
        Map<Integer, Employee> employees = new HashMap<>();
        Scanner s = new Scanner(System.in);
        String command = "";
        boolean listCreated = false;
        do {
            System.out.print("Введите команду (? - список команд): ");
            command = s.nextLine();
            switch (command) {
                case "?": {
                    System.out.println(
                            "Список команд:\n" +
                                    "create - создать список сотрудников случайным образом;\n" +
                                    "show - вывести список сотрудников;\n" +
                                    "delete - удалить сотудника (ввод ID);\n" +
                                    "add - добавить сотрудника;\n" +
                                    "index - индексация зарплаты сотрудников (ввод % инднксации);\n" +
                                    "max - максимальная уровень ЗП;\n" +
                                    "min - минимальный уровень ЗП;\n" +
                                    "average - средняя ЗП;\n" +
                                    "exit - завершение программы.");
                    break;
                }
                case "exit": {
                    System.out.println("Выход.");
                    break;
                }
                case "create": {
                    if (!listCreated) {
                        employees = creatEmployeesList(10);
                        listCreated = true;
                        System.out.println("Список сотрудников создан.");
                    } else {
                        System.out.println("Список сотрудников полон.");
                    }
                    break;
                }
                case "show": {
                    if (listCreated) {
                        showEmployeesList(employees);
                    } else {
                        System.out.println("Список сотрудников пуст.");
                    }
                    break;
                }
                case "delete": {
                    employees = deleteEmployee(employees);
                    break;
                }
                case "add": {
                    employees = addEmployee(employees);
                    break;
                }
                case "index": {
                    employees = indexSalary(employees);
                    break;
                }
                case "max": {
                    System.out.println("Максимальная зарплата сотрудника: " + employees.get(maxSalaryIndex(employees)));
                    break;
                }
                case "min": {
                    System.out.println("Минимальная зарплата сотрудника: " + employees.get(minSalaryIndex(employees)));
                    break;
                }
                case "average": {
                    System.out.println("Средняя зарплата: " + averageSalary(employees));
                    break;
                }
                default: {
                    System.out.printf("Команды %s не существует. Список команд - \"?\".\n", command);
                }
            }
        } while (!command.equals("exit"));
    }

    private static void showEmployeesList(Map<Integer, Employee> employees) {
        for (Map.Entry<Integer, Employee> entry: employees.entrySet()) {
            //System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
            System.out.printf("%-4d %-30s %-10d %-10s \n", entry.getKey(), entry.getValue(), entry.getValue().getSalary(), entry.getValue().getDepartment());

        }
    }

    public static Map<Integer, Employee> creatEmployeesList(int numberOfEmployees) {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        String[] names = {"John", "Sarah", "Mike", "Bob", "Robert", "Donna", "Anna", "Lisa", "George", "Peter", "Denny"};
        String[] fNames = {"Melory", "Gray", "Berg", "Davis", "Wild", "Shield", "Chain", "Chan"};
        String[] deparments = {"Sale Department", "Top Management Department", "Storage Department", "Manufactory Department"};
        Random s = new Random();
        for (int i = 0; i < numberOfEmployees; i++) {
            employeeMap.put(i, new Employee(
                    names[s.nextInt(names.length - 1)],
                    fNames[s.nextInt(fNames.length - 1)],
                    s.nextInt(100_000) + 50_000,
                    deparments[s.nextInt(deparments.length - 1)]
            ));
        }
        return employeeMap;
    }

    public static Map<Integer, Employee> deleteEmployee(Map<Integer, Employee> employees) {
        int deleteID;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID сотрудника для удаления: ");
        deleteID = scanner.nextInt();
        if (employees.containsKey(deleteID)) {
            employees.remove(deleteID);
        }
        return employees;
    }

    public static Map<Integer, Employee> addEmployee(Map<Integer, Employee> employees) {
        String name;
        String fname;
        Integer salary;
        String department;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Имя: ");
        name = (scanner.next());
        System.out.print("Фамилия: ");
        fname = (scanner.next());
        System.out.print("Зарплата: ");
        salary  = (scanner.nextInt());
        scanner.nextLine();
        System.out.print("Департамент: ");
        department = (scanner.nextLine());
        System.out.println();

        TreeMap<Integer, Employee> sorted = new TreeMap<>();
        sorted.putAll(employees);
        Map.Entry<Integer, Employee> lastEntry = sorted.lastEntry();

        employees.put(lastEntry.getKey() + 1,new Employee(name, fname, salary, department));

        System.out.println("Сотрудник успешно добавлен.");
        return employees;
    }

    public static Employee[] nullsToTheEnd(Employee[] employees) {
        Employee[] sortedEmployees = new Employee[employees.length];
        int j = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                sortedEmployees[j] = employees[i];
                j++;
            }
        }
        return sortedEmployees;
    }

    public static Map<Integer, Employee> indexSalary(Map<Integer, Employee> employees) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите % индексации зарплаты: ");
        int salatyIndex = (int) scanner.nextInt();
        for (Map.Entry<Integer, Employee> entry: employees.entrySet()) {
            if (entry.getValue() != null) {
                Employee employee = entry.getValue();
                employee.setSalary(employee.getSalary() + (int) (employee.getSalary() / 100 * salatyIndex));
                entry.setValue(employee);
                employee = null;
            }
        }
        return employees;
    }

    public static int maxSalaryIndex(Map<Integer, Employee> employees) {
        int max = 0;
        int maxIndex = 0;
        for (Map.Entry<Integer, Employee> entry: employees.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().getSalary() > max) {
                    max = entry.getValue().getSalary();
                    maxIndex = entry.getKey();
                }
            }
        }
        return maxIndex;
    }

    public static int minSalaryIndex(Map<Integer, Employee> employees) {
        int min = 0;
        int minIndex = 0;
        for (Map.Entry<Integer, Employee> entry: employees.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().getSalary() < min) {
                    min = entry.getValue().getSalary();
                    minIndex = entry.getKey();
                }
            }
        }
        return minIndex;
    }

    public static int averageSalary(Map<Integer, Employee>employees) {
        int counteEmpoyees = 0;
        int sumSalary = 0;
        for (Map.Entry<Integer, Employee> entry: employees.entrySet()) {
            if (entry.getValue() != null) {
                sumSalary += entry.getValue().getSalary();
                counteEmpoyees++;
            }
        }
        return (int) (sumSalary / counteEmpoyees);
    }
}