import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Displayer {
    private static final int PORT = 9000;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        while (true) {
            Socket no = serverSocket.accept();
            ThreadProcessamento tdProcessamento = new ThreadProcessamento(no);
            tdProcessamento.start();
        }
    }
}

class ThreadProcessamento extends Thread {
    private Socket no;

    ThreadProcessamento(Socket no) {this.no = no;}

    public void run() {
        InputStreamReader is = null;
        try {
            is = new InputStreamReader(no.getInputStream());
            BufferedReader reader = new BufferedReader(is);

            OutputStream os = no.getOutputStream();
            DataOutputStream writer = new DataOutputStream(os);

            String cotacao = reader.readLine();
            System.out.println(cotacao);

        } catch (IOException e) { }
    }
}
