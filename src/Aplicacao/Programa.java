package Aplicacao;
import Tabuleiro.Tabuleiro;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.XadrezException;
import Xadrez.XadrezPosicao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        PartidaXadrez partidaXadrez = new PartidaXadrez();
        List<PecaXadrez> cap = new ArrayList<>();

        while (true) {
            try {
                UI.clearScreen();
                //recebe a matriz de peças da minha partida
                UI.printMatch(partidaXadrez, cap);
                System.out.println();
                System.out.print("origem: ");
                XadrezPosicao origem = UI.lerPosicaoXadrez(scan);

                boolean[][] possivelMovimento = partidaXadrez.possivelMovimento(origem);
                UI.clearScreen();
                UI.printTabuleiro(PartidaXadrez.getPecas(), possivelMovimento);
                System.out.println();
                System.out.print("destino: ");
                XadrezPosicao destino = UI.lerPosicaoXadrez(scan);

                PecaXadrez capturaPeca = partidaXadrez.executarMovimentoXadrez(origem, destino);

                if (capturaPeca != null){
                    cap.add(capturaPeca);
                }
            }
            catch (XadrezException e){
                System.out.println(e.getMessage());
                scan.nextLine();
            }
            catch (InputMismatchException e){
                System.out.println(e.getMessage());
                scan.nextLine();
            }
        }

    }
}