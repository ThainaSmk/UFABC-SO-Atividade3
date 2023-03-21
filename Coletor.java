
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Coletor {
    private static final int PORT = 9000;
    private static ArrayList<Cotacao> cotacoesInstanciadas = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        /* input para simular o "envio" de cotações para o coletor
        // Num sistema mais desenvolvido, ficaria sempre esperando o buffer conter alguma informação e enviaria
        // para tratamento. Para essa prática, será apenas um loop simples
        */
        BufferedReader inputFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Quantas cotações serão enviadas para o displayer? ");
        Integer qtdCotacoes = Integer.parseInt(inputFromUser.readLine());
        for(int i = 1; i <= qtdCotacoes; i++) {
            System.out.println("Digita a cotação #" + i + " no formato [Moeda Origem] [Moeda Destino] [Cotacao] [dd/MM/YYYY hh:mm:ss]");
            String input = inputFromUser.readLine();
            Cotacao novaCotacao = new Cotacao(
                    input.split(" ")[0],
                    input.split(" ")[1],
                    input.split(" ")[2],
                    input.split(" ")[3] + " " + input.split(" ")[4]
                    );
            cotacoesInstanciadas.add(novaCotacao);
        }

        while (!cotacoesInstanciadas.isEmpty()) {
            Socket socket = new Socket("127.0.0.1", PORT);
            Cotacao ultimaCotacaoEnviada = null;

            // Writer fará a escrita para o displayer ler a informação do socket
            OutputStream os = socket.getOutputStream();
            DataOutputStream writer = new DataOutputStream(os);

            //System.out.println(response);
            if (!cotacoesInstanciadas.isEmpty() && ultimaCotacaoEnviada != cotacoesInstanciadas.get(0)) {
                ultimaCotacaoEnviada = cotacoesInstanciadas.remove(0);
                writer.writeBytes(ultimaCotacaoEnviada.cotacaoToString());
            }
            socket.close();
        }
    }
}
