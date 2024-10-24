package BasedReseau;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.nio.file.Path;

public class Serveur {
    private Socket client = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    public Serveur(int port) throws IOException {

        this.server = new ServerSocket(port);
        client = server.accept();
        in = new DataInputStream(client.getInputStream());
        out = new DataOutputStream(client.getOutputStream());

        String line = "";

        while(!line.equals("Fini")){
            try{
                line = in.readUTF();
                System.out.println(line);
            }
            catch (IOException e){
                System.out.println(e);
            }
            String[] Array = line.split(" ");
            String content = "Content Type"+ g
            if (Array[0] == "HEAD"){
                out.writeUTF("Head command receive");
                if(!Array[1].startsWith("/") || !Array[2].startsWith("/")){
                    out.writeUTF(Array[2] + "400 Bad Request");
                    continue;
                }
                File file = new File("."+ Array[2]);
                if(!file.exists()){
                    out.writeUTF(Array[2] + "404 Not Found");
                    continue;
                }
                if(Array[2] != "HTTP/1.1"){
                    out.writeUTF(Array[2] + "505 HTTP Version Not Supported");
                    continue;
                }
                out.writeUTF(Array[2] + "200 Ok");


            }
        }
        System.out.println("Connection Fermer");

        client.close();
        in.close();
    }
}
