package BasedReseau;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Serveur {
    private Socket client;
    private ServerSocket server;
    private DataInputStream in;
    private DataOutputStream out;
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

            if (Array[0].equals("HEAD")){
                out.writeUTF("Head command receive");
                //code d'erreur
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
                //Date
                Instant currentInstant = Instant.now();

                DateTimeFormatter regulation = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("GMT"));

                String formattedDateTime = regulation.format(currentInstant);

                out.writeUTF("Date et heure en GMT : " + formattedDateTime);

                //Content Type
                if(Array[1].endsWith(".html")){
                    out.writeUTF("Content Type : text/html");

                }
                if(Array[1].endsWith(".js")){
                    out.writeUTF("Content Type : text/js");

                }
                if(Array[1].endsWith(".txt")){
                    out.writeUTF("Content Type : text/txt");

                }

            }
            if (Array[0].equals("GET")){
                out.writeUTF("Get command receive");
                //code d'erreur
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
                //Date
                Instant currentInstant = Instant.now();

                DateTimeFormatter regulation = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("GMT"));

                String formattedDateTime = regulation.format(currentInstant);

                out.writeUTF("Date et heure en GMT : " + formattedDateTime);

                //Content Type
                if(Array[1].endsWith(".html")){
                    out.writeUTF("Content Type : text/html");

                }
                if(Array[1].endsWith(".js")){
                    out.writeUTF("Content Type : text/js");

                }
                if(Array[1].endsWith(".txt")){
                    out.writeUTF("Content Type : text/txt");

                }

                try {
                    Files.lines(Paths.get(file.toURI())).forEach(System.out::println);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            if (Array[0].equals("GET")){
                out.writeUTF("Get command receive");
                //code d'erreur
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
                //Date
                Instant currentInstant = Instant.now();

                DateTimeFormatter regulation = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("GMT"));

                String formattedDateTime = regulation.format(currentInstant);

                out.writeUTF("Date et heure en GMT : " + formattedDateTime);

                //Content Type
                if(Array[1].endsWith(".html")){
                    out.writeUTF("Content Type : text/html");

                }
                if(Array[1].endsWith(".js")){
                    out.writeUTF("Content Type : text/js");

                }
                if(Array[1].endsWith(".txt")){
                    out.writeUTF("Content Type : text/txt");

                }

                try {
                    Files.lines(Paths.get(file.toURI())).forEach(System.out::println);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            if(Array[0]=="PUT"){
                out.writeUTF("418 I'm a teapot");
            }
            if(Array[0]=="DELETE"){
                out.writeUTF("418 I'm a teapot");
            }
        }
        System.out.println("Connection Fermer");

        client.close();
        in.close();
    }
}
