package DMRefractorCode;
import java.util.Scanner;
import java.util.ArrayList;

public class Refactorcode {
    public static void main(String[] args) {
        Personnel per = new Personnel();
        TotalObjects total = new TotalObjects();
        Scanner scan = new Scanner(System.in);
        String firstN, lastN, middleN;
        int empID;
        double salary;
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

            switch(choice) {
                case 1:
                    System.out.println("Enter first name: ");
                    firstN = scan.nextLine();
                    System.out.println("Enter last name: ");
                    lastN = scan.nextLine();
                    System.out.println("Enter middle name: ");
                    middleN = scan.nextLine();

                    System.out.println("Enter employee id: ");
                    empID = scan.nextInt();
                    System.out.println("Enter base salary: ");
                    salary = scan.nextDouble();
                    scan.nextLine();

                    Employee e1 = new Employee(lastN, firstN, middleN, empID, salary);
                    per.addPersonnel(e1);
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
                        foundPerson.printName(0);
                    } else {
                        System.out.println("Not found");
                        Person p1 = new Person(lastN, firstN, " ");
                        per.addPersonnel(p1);
                        total.objectAdded();
                    }

                    break;

                case 3:
                    System.out.println("Enter the order 0: first, middle, last, 1: first, last, middle, 2: last, first, middle ");
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

