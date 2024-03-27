package PecasXadrez;

import Tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Tabuleiro.Posicao;

public class Rei extends PecaXadrez {

    private PartidaXadrez partidaXadrez;

    public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
    }

    public String toString(){
        return  "R";
    }

   private boolean podeMover(Posicao posicao ){
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return  p == null || p.getCor() != getCor();
   }
   private boolean testeRoque(Posicao posicao){
        PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
        return p != null && p instanceof Roque && p.getCor() == getCor() && p.getMovieCount() == 0;
   }

    @Override
    public boolean[][] possivelMovimento(){
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0,0);

        //a cima
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //a baixo
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //a esquerda
        p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //a direita
        p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //noro-oeste
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        //nordeste
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //sudo-oeste
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }


        //sudeste
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        if (getMovieCount() && !partidaXadrez.getCheck()){
            Posicao posicaoT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
            if (testeRoque(posicaoT1)){
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null){
                    mat[posicao.getLinha()][posicao.getColuna() + 2] = true;

                }
            }
            Posicao posicaoT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
            if (testeRoque(posicaoT2)){
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null){
                    mat[posicao.getLinha()][posicao.getColuna() - 2] = true;

                }
            }
        }

        return mat;

    }

}
