import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FTPServer {
    public static void main(String[] args) {
        try {
            while (true) {
                ServerSocket ss = new ServerSocket(1010);
                Socket sl = ss.accept();
                System.out.println("Server scoket is created....");
                System.out.println(" test1");
                DataInputStream fromserver = new DataInputStream(sl.getInputStream());
                System.out.println(" test2");
                String option = fromserver.readLine();
                if (option.equalsIgnoreCase("upload")) {
                    System.out.println("upload test");
                    String filefromclient = fromserver.readLine();
                    File clientfile = new File(filefromclient);
                    FileOutputStream fout = new FileOutputStream(clientfile);
                    int ch;
                    while ((ch = fromserver.read()) != -1) {
                        fout.write((char) ch);
                    }
                    fout.close();
                }
                if (option.equalsIgnoreCase("download")) {
                    System.out.println("download test");
                    String filefromclient = fromserver.readLine();
                    File clientfile = new File(filefromclient);
                    FileInputStream fis = new FileInputStream(clientfile);
                    PrintStream out = new PrintStream(sl.getOutputStream());
                    int n = fis.read();
                    while (n != -1) {
                        out.print((char) n);
                        n = fis.read();
                    }
                    fis.close();
                    out.close();
                } // while
            }
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        }
    }
}