package Aplicacao;
import Tabuleiro.Tabuleiro;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;

public class Programa {
    public static void main(String[] args) {

        PartidaXadrez partidaXadrez = new PartidaXadrez();
        //recebe a matriz de peças da minha partida
        UI.printTabuleiro(partidaXadrez.getPecas());

    }
}