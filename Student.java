package ds_project;

class Student {
    int rollNo;
    String name, branch, teacher;
    int year;
    int attendance;
    double cgpa;

    Student(int r, String n, int y, String b, int a, double c) {
        rollNo = r;
        name = n;
        year = y;
        branch = b;
        attendance = a;
        cgpa = c;
        teacher = "Not Assigned";
    }

    String toFileString() {
        return rollNo + "," + name + "," + year + "," + branch + "," +
                attendance + "," + cgpa + "," + teacher;
    }

    static Student fromFileString(String line) {
        String[] p = line.split(",");
        Student s = new Student(
                Integer.parseInt(p[0]), p[1], Integer.parseInt(p[2]), p[3],
                Integer.parseInt(p[4]), Double.parseDouble(p[5])
        );
        s.teacher = p[6];
        return s;
    }

    void display(String[][] subjects) {
        System.out.print(rollNo + " | " + name +
                " | Year: " + year +
                " | CGPA: " + cgpa +
                " | Attendance: " + attendance + "%" +
                " | " + teacher);

        if (year >= 1 && year <= 4) {
            System.out.println(" | Subjects: " +
                    subjects[year-1][0] + ", " +
                    subjects[year-1][1]);
        } else {
            System.out.println(" | Invalid Year");
        }
    }
}

