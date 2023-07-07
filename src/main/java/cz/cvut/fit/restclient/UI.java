package cz.cvut.fit.restclient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class UI {

    Service service;
    BufferedReader reader;

    public UI() {
        service = new Service();
        this.reader = new BufferedReader( new InputStreamReader(System.in));
    }

    private String getMsg() throws IOException {
        return reader.readLine();
    }

    public void  callInit() {
        service.init();
    }

    void run() throws IOException {
        while(true) {
            System.out.println("Enter next command, for help write: \"help\".");
            String msg = getMsg();;
            if(Objects.equals(msg, "exit"))
                break;
            if(Objects.equals(msg, "help")) {
                showHelp();
                continue;
            }
            String[] arr = msg.split(" ");
            service.makeCommand(arr);
        }
    }

    private void showHelp() {
        System.out.println("This is list of all commands: ");
        System.out.println("command \"addStud name\" creates new student.");
        System.out.println("command \"getStuds\" shows all students.");
        System.out.println("command \"delStud id\" deletes student.");
        System.out.println("command \"updateStud id name\" changes the name of student with this id.");
        System.out.println("command \"addSub name\" created new subject.");
        System.out.println("command \"getSubs\" shows all subjects.");
        System.out.println("command \"updateSub id name\" changed name of subject with this id.");
        System.out.println("command \"delSub id\" deletes subject.");
        System.out.println("command \"getSubStuds id\" shows all students assigned to subject with this id.");
        System.out.println("command \"addStudToSub id1 id2\" adds student with id2 to subject with id1.");
        System.out.println("command \"delStudFromSub id1 id2\" deletes student with id2 from subject with id1.");
    }


}
