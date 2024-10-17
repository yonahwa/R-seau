package BasedReseau;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    private Socket client = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public Serveur(int port) throws IOException {

        this.server = new ServerSocket(port);
        client = server.accept();
        in = new DataInputStream(client.getInputStream());

        String line = "";

        while(!line.equals("Fini")){
            try{
                line = in.readUTF();
                System.out.println(line);
            }
            catch (IOException e){
                System.out.println(e);
            }
            if (line.startsWith("GET")){

            }
        }
        System.out.println("Connection Fermer");

        client.close();
        in.close();
    }
}
