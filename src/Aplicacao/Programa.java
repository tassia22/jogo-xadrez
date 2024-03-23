package Aplicacao;
import Tabuleiro.Tabuleiro;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.XadrezPosicao;

import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        PartidaXadrez partidaXadrez = new PartidaXadrez();
        while (true){
            //recebe a matriz de peças da minha partida
            UI.printTabuleiro(partidaXadrez.getPecas());
            System.out.println();
            System.out.print("origem: ");
            XadrezPosicao origem = UI.lerPosicaoXadrez(scan);

            System.out.println();
            System.out.print("destino: ");
            XadrezPosicao destino = UI.lerPosicaoXadrez(scan);

            PecaXadrez capturaPeca = partidaXadrez.executarMovimentoXadrez(origem, destino);
        }


    }
}