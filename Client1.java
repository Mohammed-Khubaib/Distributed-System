import java.net.*;
import java.io.*;

class Client1 {
    static String name = "";

    public Client1(String n) {
        name = n;
    }

    public static void main(String args[]) throws Exception {
        System.out.println("connecting to server");
        System.out.println("client1 connected to server");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /* Sending message to the server */
        System.out.println("Hi\t" + name + " u can start chating");
        while (true) {
            Socket s = new Socket("localhost", 1010);
            String n = br.readLine();
            OutputStream os = s.getOutputStream();
            os.write(n.getBytes());
            /* Reading data from client */
            InputStream is = s.getInputStream();
            byte data[] = new byte[50];
            is.read(data);
            String mfc = new String(data);
            mfc = mfc.trim();
            System.out.println(mfc);
        }
    }
}