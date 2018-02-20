package sample.CommunicationHandler;

import sample.Model.DiscoverdPeer;
import sample.Model.Validate;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {


        String line=null;
        BufferedReader is = null;
        PrintWriter os=null;
        Socket s=null;
        InputStream is1;//**********
        ObjectInputStream ois;//***********
        OutputStream os1;//***********
        ObjectOutputStream oos;//********


        public ServerThread(Socket s){
            this.s=s;
        }

        public void run() {
            try{

                this.is1 = s.getInputStream();//********
                this.ois = new ObjectInputStream(is1);//************

                //this.os1 = s.getOutputStream();//****
                //this.oos = new ObjectOutputStream(os1);//***********

                //is= new BufferedReader(new InputStreamReader(s.getInputStream()));
                //os=new PrintWriter(s.getOutputStream());

            }catch(IOException e){
                System.out.println("IO error in server thread");
            }

            try {
                //line=is.readLine();
                DiscoverdPeer peer1=(DiscoverdPeer)this.ois.readObject();//**************8//read objects from client
                //System.out.println("ccould track the peer");
                if (peer1!=null) {

                    //if (peer1.getMsg()=="new join request"){

                    System.out.print(peer1.getMsg());
                    System.out.print(peer1.getUsername());
                    System.out.print(peer1.getIp());
                    System.out.print(peer1.getPort());
                    int result= Validate.getInstance().check(peer1);
                    if(result==1){
                        //The username is alrady reserved
                    }else if(result==2){
                        //already used IP port combination
                    }else{
                        //succesfully registered

                    }
                }

                //}


                //Peer peer=new Peer();
                //this.oos.writeObject(peer);//write objects  to client
                //this.oos.writeObject(new String("another object from the server"));

                //System.out.println((String)this.ois.readObject());
                /*
                while(line.compareTo("QUIT")!=0){

                    os.println(line);
                    os.flush();
                    System.out.println("Response to Client  :  server got your message");
                    System.out.println("Response to Client  :  "+line);
                    //line=is.readLine();
                }*/
            } catch (IOException e) {
                e.printStackTrace();
                //line=this.getName(); //reused String line for getting thread name
                //System.out.println("IO Error/ Client "+line+" terminated abruptly");
            }
            catch (ClassNotFoundException e) {//******************
                e.printStackTrace();
            }
            catch(NullPointerException e){
                e.printStackTrace();
                //line=this.getName(); //reused String line for getting thread name
                //System.out.println("Client "+line+" Closed");
            }
            /*
            finally{
                try{
                    System.out.println("Connection Closing..");
                    if (is!=null){
                        is.close();
                        System.out.println(" Socket Input Stream Closed");
                    }

                    if(os!=null){
                        os.close();
                        System.out.println("Socket Out Closed");
                    }
                    if (s!=null){
                        s.close();
                        System.out.println("Socket Closed");
                    }

                }
                catch(IOException ie){
                    System.out.println("Socket Close Error");
                }
            }*///end finally
        }
    }



