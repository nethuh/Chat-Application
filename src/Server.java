import model.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
//
//        //port number eka wenas wenne nethi nisa final dala thyenne
//        //meka kelinma new ServerSocket(8000) kyla daanna plwn
//        //methana mehema dala thyenne output ekak ganna ona nisa
//        final int port = 8000;
//
//        //Meken karanne ara listen karan inna server socket eka hadaganna eka.. ekata api port number ekakuth dila thyenw
//        ServerSocket serverSocket = new ServerSocket(port);
//
//        System.out.println("Server is running in port " + port);
//
//        //clinet kenek request karoth accept karanna
////        serverSocket.accept();
//        //client kenek gen ena rqst ek accept krl iwr wela e ena rq eka connection ek hdgen thawa scket ekkt dgnnw.(local socket ekt)
//        Socket localSocket = serverSocket.accept();
    private static ArrayList<Client> clients = new ArrayList<Client>();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(6000);

        Socket accept;


        while (true) {

            System.out.println("Waiting for Client ...");
            accept = serverSocket.accept();
            System.out.println("Client Connected");
            Client clientThread = new Client(accept, clients);
            clients.add(clientThread);
            clientThread.start();
        }
    }
}