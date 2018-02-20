package sample.Model;

import java.net.InetAddress;
import sample.DBHandler.DatabaseHandler;

public class Validate {
    private static Validate single_instance_validate = null;
    private Validate()
    { }

    // static method to create instance of Singleton class
    public static Validate getInstance()
    {
        if (single_instance_validate == null)
            single_instance_validate = new Validate();

        return single_instance_validate ;
    }

    public synchronized int check(DiscoverdPeer peer){ //since all connected clients want to access the same table,method should be synchronized
        String username=peer.getUsername();
        InetAddress ip=peer.getIp();
        int port=peer.getPort();
        DatabaseHandler db=new DatabaseHandler();
        //write a sql to see whether the same username exists in table
        //nexet check if same ip port combination is with some other entry
        //then only update the table with new data
        /*
        if(db.selectFromRegisteredUserTable()){
            return 1;
        }
        else(db.updateRegisteredUserTable(username,ip,port)) {
            return 3;
        }
        */
        db.updateRegUserTable(username,ip,port);
        return 3;
    }
}
