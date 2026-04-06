package ds_project;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        StudentList list = new StudentList();
        HashTable hash = new HashTable();

        list.loadFromFile();

        String[] teachers = {"Teacher A", "Teacher B", "Teacher C", "Teacher D", "Teacher E"};
        int[] teacherCount = new int[teachers.length];

        String[][] subjects = {
            {"Mathematics", "Physics"},
            {"DBMS", "Operating Systems"},
            {"Computer Networks", "Software Engg"},
            {"Machine Learning", "AI"}
        };

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            int roll = rand.nextInt(9000) + 1000;

            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Year (1-4): ");
            int year = sc.nextInt();
            sc.nextLine();
            System.out.print("Branch: ");
            String branch = sc.nextLine();
            System.out.print("Attendance: ");
            int att = sc.nextInt();
            System.out.print("CGPA: ");
            double cg = sc.nextDouble();
            sc.nextLine();

            Student s = new Student(roll, name, year, branch, att, cg);
            list.insert(s);
        }

        Student[] arr = list.toArray();

        PriorityQueueLL pq = new PriorityQueueLL();
        for (int i = 0; i < arr.length; i++) {
            pq.insert(arr[i]);
        }

        // Teacher allocation using Priority Queue
        for (int i = 0; i < arr.length; i++) {

            Student s = pq.delete();

            while (true) {
                System.out.println("\n" + s.name + " (CGPA: " + s.cgpa + ")");
                System.out.print("Choose teacher (A-E): ");
                String choice = sc.nextLine().toUpperCase();

                int index = choice.charAt(0) - 'A';

                if (index >= 0 && index < teachers.length) {
                    if (teacherCount[index] < 2) {
                        s.teacher = teachers[index];
                        teacherCount[index]++;
                        break;
                    } else {
                        System.out.println("Teacher full. Choose another.");
                    }
                } else {
                    System.out.println("Invalid choice!");
                }
            }
        }

        list.saveToFile();

        int ch;
        do {
            System.out.println("\n1.Sort Name");
            System.out.println("2.Sort Roll");
            System.out.println("3.Sort CGPA");
            System.out.println("4.Search by Name");
            System.out.println("5.Search by Roll");
            System.out.println("6.<75 Attendance");
            System.out.println("7.Delete");
            System.out.println("8.Display All");
            System.out.println("9.Teacher-wise grouping");
            System.out.println("10.Year-wise subjects");
            System.out.println("11.Exit");

            ch = sc.nextInt();
            sc.nextLine();

            arr = list.toArray();

            switch (ch) {
                case 1:
                    HeapSort.sortName(arr);
                    for (Student s : arr) s.display(subjects);
                    break;

                case 2:
                    SelectionSort.sortRoll(arr);
                    for (Student s : arr) s.display(subjects);
                    break;

                case 3:
                    SelectionSort.sortCGPA(arr);
                    for (Student s : arr) s.display(subjects);
                    break;

                case 4:
                    hash.rebuild(arr);
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    Student f = hash.search(name);
                    if (f != null) f.display(subjects);
                    else System.out.println("Not Found");
                    break;

                case 5:
                    System.out.print("Enter Roll: ");
                    int key = sc.nextInt();
                    SelectionSort.sortRoll(arr);
                    int idx = BinarySearch.search(arr, key);
                    if (idx != -1) arr[idx].display(subjects);
                    else System.out.println("Not Found");
                    break;

                case 6:
                    for (Student s : arr)
                        if (s.attendance < 75)
                            s.display(subjects);
                    break;

                case 7:
                    System.out.print("Enter Roll: ");
                    int d = sc.nextInt();

                    if (list.delete(d)) {
                        list.saveToFile();
                        System.out.println("Deleted");
                    } else {
                        System.out.println("Roll number not found");
                    }
                    break;

                case 8:
                    for (Student s : arr)
                        s.display(subjects);
                    break;

                case 9:
                    for (int i = 0; i < teachers.length; i++) {
                        System.out.println("\n" + teachers[i] + ":");
                        for (Student s : arr)
                            if (s.teacher.equals(teachers[i]))
                                s.display(subjects);
                    }
                    break;

                case 10:
                    for (int i = 1; i <= 4; i++) {
                        System.out.println("\nYear " + i + " Subjects: " +
                                subjects[i-1][0] + ", " + subjects[i-1][1]);

                        for (Student s : arr)
                            if (s.year == i)
                                s.display(subjects);
                    }
                    break;
            }

        } while (ch != 11);
        sc.close();
    }
    
}