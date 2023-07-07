package cz.cvut.fit.restclient;

import cz.cvut.fit.restclient.data.Student;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class Service {

    StudentProxy studentProxy = StudentProxy.inst;
    SubjectProxy subjectProxy = SubjectProxy.inst;



    public void init() {
        studentProxy.createStudent("Pepa");
        studentProxy.createStudent("Jirka");
        studentProxy.createStudent("Honza");

        subjectProxy.createSubject("ag1");
        subjectProxy.createSubject("matematika");
        subjectProxy.createSubject("pjv");
    }

    private int parseInt (String str) {
        int i = 444666;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("Bad command, not a number.");
        }
        return i;
    }

    public void callAddStud(String name) {
        studentProxy.createStudent(name);
    }
    public void callGetStuds() {
        System.out.println(studentProxy.allStudents());
    }
    public void callUpdateStud(int id, String name) {
        Response r = studentProxy.updateStudent(id, name);
        if(r.getStatus() == 400)
            System.out.println("ERROR: Invalid id.");
    }
    public void callDelStud(int id) {
        Response r = studentProxy.deleteStudent(id);
        if(r.getStatus() == 400)
            System.out.println("ERROR: Invalid id.");
    }
    public void callAddSub(String name) {
        subjectProxy.createSubject(name);
    }
    public void callGetSubs() {
        System.out.println(subjectProxy.allSubjects());
    }
    public void callUpdateSub(int id, String name) {
        Response r = subjectProxy.updateSubject(id, name);
        if(r.getStatus() == 400)
            System.out.println("ERROR: Invalid id.");
    }
    public void callDelSub(int id) {
        Response r = subjectProxy.deleteSubject(id);
        if(r.getStatus() == 400)
            System.out.println("ERROR: Invalid id.");
    }
    public void callGetSubStuds(int id) {
        Response r = subjectProxy.allSubjectStudents(id);
        if(r.getStatus() == 400)
            System.out.println("ERROR: Invalid id.");
        else {
            System.out.println(r.readEntity(new GenericType<List<Student>>() {
            }));
        }
    }
    public void callAddStudToSub(int idSub, int idStud) {
        Response r = subjectProxy.addStudentOnSubject(idSub, idStud);
        var message = r.readEntity(new GenericType<String>() {});
        if(r.getStatus() == 400)
            if(message.equals("Bad subject id"))
                System.out.println("ERROR: Invalid subject id.");
            else
                System.out.println("ERROR: Invalid student id.");
    }
    public void callDelStudFromSub(int idSub, int idStud) {
        Response r = subjectProxy.deleteStudentFromSubject(idSub, idStud);
        var message = r.readEntity(new GenericType<String>() {});
        if(r.getStatus() == 400) {
            if (message.equals("Bad subject id"))
                System.out.println("ERROR: Invalid subject id.");
            else if (message.equals("Bad student id"))
                System.out.println("ERROR: Invalid student id.");
            else
                System.out.println("ERROR: Student not on this subject.");
        }
    }
    private boolean checkArgumentCount (String[] arr, int expected) {
        if(expected != arr.length) {
            System.out.println("ERROR: Bad arguments count.");
            return false;
        }
        return true;
    }

    public void makeCommand(String[] arr) {
        switch (arr[0]) {
            case "addStud" -> {
                if (!checkArgumentCount(arr, 2))
                    break;
                callAddStud(arr[1]);
            }
            case "getStuds" -> {
                callGetStuds();
            }
            case "delStud" -> {
                if (!checkArgumentCount(arr, 2))
                    break;
                int id = parseInt(arr[1]);
                if(id == 444666)
                    break;
                callDelStud(id);
            }
            case "updateStud" -> {
                if (!checkArgumentCount(arr, 3))
                    break;
                int id = parseInt(arr[1]);
                if(id == 444666)
                    break;
                callUpdateStud(id, arr[2]);
            }
            case "addSub" -> {
                if (!checkArgumentCount(arr, 2))
                    break;
                callAddSub(arr[1]);
            }
            case "getSubs" -> {
                callGetSubs();
            }
            case "updateSub" -> {
                if (!checkArgumentCount(arr, 3))
                    break;
                int id = parseInt(arr[1]);
                if(id == 444666)
                    break;
                callUpdateSub(id, arr[2]);
            }
            case "delSub" -> {
                if (!checkArgumentCount(arr, 2))
                    break;
                int id = parseInt(arr[1]);
                if(id == 444666)
                    break;
                callDelSub(id);
            }
            case "getSubStuds" -> {
                if (!checkArgumentCount(arr, 2))
                    break;
                int id = parseInt(arr[1]);
                if(id == 444666)
                    break;
                callGetSubStuds(id);
            }
            case "addStudToSub" -> {
                if (!checkArgumentCount(arr, 3))
                    break;
                int subId = parseInt(arr[1]);
                if(subId == 444666)
                    break;
                int studId = parseInt(arr[2]);
                if(studId == 444666)
                    break;
                callAddStudToSub(subId, studId);
            }
            case "delStudFromSub" -> {
                if (!checkArgumentCount(arr, 3))
                    break;
                int subId = parseInt(arr[1]);
                if(subId == 444666)
                    break;
                int studId = parseInt(arr[2]);
                if(studId == 444666)
                    break;
                callDelStudFromSub(subId, studId);
            }
            default ->
                System.out.println("Unknown command");

        }
    }
}
