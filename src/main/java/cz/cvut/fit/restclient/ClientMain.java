package cz.cvut.fit.restclient;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.io.IOException;

@QuarkusMain
public class ClientMain {

    public static void main(String[] args) throws IOException {
        Quarkus.run();
        UI ui = new UI();
        ui.callInit();
        ui.run();
    }
}
