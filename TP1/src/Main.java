import BasedReseau.Serveur;

import javax.imageio.IIOException;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try{
            Serveur serv = new Serveur(6969);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}