import java.net.*;
import java.io.*;

class A implements Runnable {
    Thread t;
    Socket s;

    A(Socket x) {
        s = x;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        try {
            /* Reading data from client */
            InputStream is = s.getInputStream();
            byte data[] = new byte[50];
            is.read(data);
            String mfc = new String(data);
            mfc = mfc.trim();
            System.out.println(mfc);
            /* Sending message to the server */
            // System.out.println("Hi"+name+"u can start chating");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String n = br.readLine();
            OutputStream os = s.getOutputStream();
            os.write(n.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ChatMultiServer {
    static int c = 0;

    public static void main(String args[]) throws Exception {
        System.out.println("ServerSocket is creating");
        ServerSocket ss = new ServerSocket(1010);
        System.out.println("ServerSocket is created");
        System.out.println("waiting for the client from the client");
        while (true) {
            Socket s = ss.accept();
            new A(s);
        }
    }
}