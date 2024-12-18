package BasedReseau;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
    private String address;
    private Socket server;
    private DataOutputStream out;
    private DataInputStream in;
    private DataInputStream Cin;

    public Client(String address, int port) throws IOException {
        server = new Socket(address,port);
        System.out.println("Connected");

        in = new DataInputStream(System.in);

        Cin = new DataInputStream(server.getInputStream());
        out = new DataOutputStream(server.getOutputStream());

        String line= "";
        String ServLine = "";

        Scanner scan = new Scanner(in);

        while(!line.equals("Fini")){
            try{
                line = scan.nextLine();
                out.writeUTF(line);
                ServLine = Cin.readUTF();
                System.out.println(ServLine);
            }
            catch(IOException e){
                System.out.println(e);
            }

        }
        in.close();
        out.close();
        server.close();
    }
}
