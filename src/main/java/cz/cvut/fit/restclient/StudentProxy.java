package cz.cvut.fit.restclient;

import cz.cvut.fit.restclient.data.Student;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class StudentProxy {

    public static final StudentProxy inst = new StudentProxy();
    Client client = ClientBuilder.newClient();

    public StudentProxy() {
    }

    List<Student> allStudents() {
        WebTarget target = client.target("http://localhost:8080/students");
        return target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Student>>(){
        });
    }

    Response createStudent(String str) {
        WebTarget target = client.target("http://localhost:8080/students");
        return target.request(MediaType.APPLICATION_JSON).post(Entity.text(str));
    }

    Response updateStudent(int id, String str) {
        WebTarget target = client.target("http://localhost:8080/students/" + id);
        //System.out.println(target.getUri());
        return target.request(MediaType.APPLICATION_JSON).put(Entity.text(str));
    }
    Response deleteStudent(int id) {
        WebTarget target = client.target("http://localhost:8080/students/" + id);
        //System.out.println(target.getUri());
        return target.request(MediaType.APPLICATION_JSON).delete();
    }
}
