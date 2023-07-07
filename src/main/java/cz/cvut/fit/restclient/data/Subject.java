package cz.cvut.fit.restclient.data;

import java.util.HashMap;

public class Subject {
    private int id;
    private String name;
    private HashMap<Integer, Student> students = new HashMap<>();

    public Subject() {
    }

    public Subject(int id, String name, HashMap<Integer, Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, Student> getStudents() {
        return students;
    }

    public void setStudents(HashMap<Integer, Student> students) {
        this.students = students;
    }

    public Student addStudent(Student student) {
        return students.put(student.getId(),student);
    }

    public Student getStudent(Integer id) {
        return students.get(id);
    }

    public Student deleteStudent(Integer id) {
        return students.remove(id);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
