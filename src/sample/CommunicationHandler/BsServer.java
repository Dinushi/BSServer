package sample.CommunicationHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BsServer {
        ServerSocket ss=null;
        Socket s=null;
        static final int port_no=55554;

        public BsServer(){
            try {
                this.ss = new ServerSocket(port_no);
                System.out.println("Server Listening......");
                while(true) {
                    try {
                        this.s = ss.accept();
                        System.out.println("connection Established");
                        ServerThread st = new ServerThread(s);
                        System.out.println("go onnn");
                        st.start();

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Connection Error");

                    }
                }

            }catch(IOException e){
                e.printStackTrace();
                System.out.println("Server error");

            }
        }

    }



