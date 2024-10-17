import BasedReseau.Client;

import java.io.IOException;

public class LittleMain {
    public static void main(String[] args) throws IOException {

        try{
            Client client = new Client("127.0.0.1",6969);
        }
        catch(IOException io){
            System.out.println("io");
        }

    }
}
