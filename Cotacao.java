public class Cotacao {
    private String moedaOrigem, moedaDestino, cotacao, dataDaCotacao;

    public Cotacao(String moedaOrigem, String moedaDestino, String cotacao, String dataDaCotacao) {
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.cotacao = cotacao;
        this.dataDaCotacao = dataDaCotacao;
    }

    public String cotacaoToString() {
        //Exemplo de string = Cotação: BRL para USD - 5,50 (20/03/2023 13:03)
        StringBuilder sb = new StringBuilder();
        sb.append("Cotacao: ");
        sb.append(this.moedaOrigem);
        sb.append(" para ");
        sb.append(this.moedaDestino);
        sb.append(" - ");
        sb.append(this.cotacao);
        sb.append(" (");
        sb.append(this.dataDaCotacao);
        sb.append(")\n");

        return sb.toString();
    }
}
