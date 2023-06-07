import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {

        //port number eka wenas wenne nethi nisa final dala thyenne
        //meka kelinma new ServerSocket(8000) kyla daanna plwn
        //methana mehema dala thyenne output ekak ganna ona nisa
        final int port = 8000;

        //Meken karanne ara listen karan inna server socket eka hadaganna eka.. ekata api port number ekakuth dila thyenw
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Server is running in port " + port);

        //clinet kenek request karoth accept karanna
        serverSocket.accept();
    }
}