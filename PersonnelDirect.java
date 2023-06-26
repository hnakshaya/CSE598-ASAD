package PersonnelDirectory;
import java.util.Scanner;
import java.util.ArrayList;

public class PersonnelDirect {
    public static void main(String[] args) {
        Personnel per = new Personnel();
        TotalObjects total = new TotalObjects();
        Scanner scan = new Scanner(System.in);
        String firstN, lastN, middleN;
        int empID;
        double salary;
        String personnelType;
        int choice = -1;

        do {
            System.out.println("Welcome to the Personnel Directory Management System");
            System.out.println("====================================================");
            System.out.println("\n\n\t 1. Add Personnel");
            System.out.println("\n\t 2. Find Personnel");
            System.out.println("\n\t 3. Print Names");
            System.out.println("\n\t 4. Number of Entries in the Directory");
            System.out.println("\n\t Select one of the options above (1, 2, 3, 4)");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter personnel type (Employee/Person/Executive/Security/Volunteer):");
                    personnelType = scan.nextLine();
                    System.out.println("Enter first name: ");
                    firstN = scan.nextLine();
                    System.out.println("Enter last name: ");
                    lastN = scan.nextLine();
                    System.out.println("Enter middle name: ");
                    middleN = scan.nextLine();
                    System.out.println("Enter employee ID: ");
                    empID = scan.nextInt();
                    System.out.println("Enter base salary: ");
                    salary = scan.nextDouble();
                    scan.nextLine();

                    Person personnel = PersonnelFactory.createPersonnel(personnelType, lastN, firstN, middleN, empID, salary);

                    per.addPersonnel(personnel);
                    total.objectAdded();
                    break;

                case 2:
                    System.out.println("Enter first name: ");
                    firstN = scan.nextLine();
                    System.out.println("Enter last name: ");
                    lastN = scan.nextLine();

                    Person foundPerson = per.findPerson(firstN, lastN);
                    if (foundPerson != null) {
                        System.out.println("Found");
                        if (foundPerson instanceof Employee) {
                            System.out.println("Personnel Type: Employee");
                        } else if (foundPerson instanceof Executive) {
                            System.out.println("Personnel Type: Executive");
                        } else if (foundPerson instanceof Security) {
                            System.out.println("Personnel Type: Security");
                        } else if (foundPerson instanceof Volunteer) {
                            System.out.println("Personnel Type: Volunteer");
                        } else {
                            System.out.println("Personnel Type: Person");
                        }
                        foundPerson.printName(0);
                        
                    } else {
                        System.out.println("Not found");
                        Person p1 = new Person(lastN, firstN, " ");
                        per.addPersonnel(p1);
                        total.objectAdded();
                    }
                    break;

                case 3:
                    System.out.println("Enter the order (0: first, middle, last | 1: first, last, middle | 2: last, first, middle): ");
                    int order = scan.nextInt();
                    for (Person person : per.getPersonnel()) {
                        person.printName(order);
                    }
                    break;

                case 4:
                    System.out.println("Total Entries: " + total.getTotalObjects());
                    break;
            }
        } while (true);
    }
}

class Employee extends Person {
    private int empID;
    private double baseSalary;

    public Employee(String last, String first, String middle, int id, double sal) {
        super(last, first, middle);
        empID = id;
        baseSalary = sal;
    }

    public int getID() {
        return empID;
    }
}

class Person {
    private String last;
    private String first;
    private String middle;

    public Person(String last, String first, String middle) {
        this.last = last;
        this.first = first;
        this.middle = middle;
    }

    public void printName(int order) {
        if (order == 0) {
            System.out.println(first + " " + middle + " " + last);
        } else if (order == 1) {
            System.out.println(first + " " + last + " " + middle);
        } else if (order == 2) {
            System.out.println(last + " " + first + " " + middle);
        }
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }
}

class Personnel {
    private ArrayList<Person> personList;

    public Personnel() {
        personList = new ArrayList<Person>();
    }

    public void addPersonnel(Person p) {
        personList.add(p);
    }

    public Person findPerson(String first, String last) {
        for (Person person : personList) {
            if (person.getFirst().equals(first) && person.getLast().equals(last)) {
                return person;
            }
        }
        return null;
    }

    public ArrayList<Person> getPersonnel() {
        return personList;
    }
}

class TotalObjects {
    private static int numObjects = 0;

    public TotalObjects() {
        numObjects = 0;
    }

    public void objectAdded() {
        numObjects++;
    }

    public int getTotalObjects() {
        return numObjects;
    }
}

class PersonnelFactory {
    public static Person createPersonnel(String type, String last, String first, String middle, int id, double sal) {
        if (type.equalsIgnoreCase("Employee")) {
            return new Employee(last, first, middle, id, sal);
        } else if (type.equalsIgnoreCase("Person")) {
            return new Person(last, first, middle);
        } else if (type.equalsIgnoreCase("Executive")) {
            return new Executive(last, first, middle, id, sal);
        } else if (type.equalsIgnoreCase("Security")) {
            return new Security(last, first, middle, id, sal);
        } else if (type.equalsIgnoreCase("Volunteer")) {
            return new Volunteer(last, first, middle);
        } else {
            throw new IllegalArgumentException("Invalid personnel type: " + type);
        }
    }
}

class Executive extends Employee {
    public Executive(String last, String first, String middle, int id, double sal) {
        super(last, first, middle, id, sal);
    }
}

class Security extends Employee {
    public Security(String last, String first, String middle, int id, double sal) {
        super(last, first, middle, id, sal);
    }
}

class Volunteer extends Person {
    public Volunteer(String last, String first, String middle) {
        super(last, first, middle);
    }
}
