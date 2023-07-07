package cz.cvut.fit.restclient;

import cz.cvut.fit.restclient.data.Subject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class SubjectProxy {

    public static final SubjectProxy inst = new SubjectProxy();
    Client client = ClientBuilder.newClient();

    public SubjectProxy() {
    }

    List<Subject> allSubjects() {
        WebTarget target = client.target("http://localhost:8080/subjects");
        return target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Subject>>(){
        });
    }

    Response createSubject(String str) {
        WebTarget target = client.target("http://localhost:8080/subjects");
        return target.request(MediaType.APPLICATION_JSON).post(Entity.text(str));
    }

    Response updateSubject(int id, String str) {
        WebTarget target = client.target("http://localhost:8080/subjects/" + id);
        return target.request(MediaType.APPLICATION_JSON).put(Entity.text(str));
    }
    Response deleteSubject(int id) {
        WebTarget target = client.target("http://localhost:8080/subjects/" + id);
        return target.request(MediaType.APPLICATION_JSON).delete();
    }

    Response allSubjectStudents(int subjectId) {
        WebTarget target = client.target("http://localhost:8080/subjects/" + subjectId + "/students");
        //return target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Student>>(){});
        return target.request(MediaType.APPLICATION_JSON).get();

    }

    Response addStudentOnSubject(int subjectId, int studentId) {
        WebTarget target = client.target("http://localhost:8080/subjects/" + subjectId + "/students");
        return target.request(MediaType.APPLICATION_JSON).post(Entity.entity(studentId, MediaType.APPLICATION_JSON));
    }

    Response deleteStudentFromSubject(int subjectId, int studentId) {
        WebTarget target = client.target("http://localhost:8080/subjects/" + subjectId + "/students/" + studentId);
        return target.request(MediaType.APPLICATION_JSON).delete();
    }
}
