package PecasXadrez;

import Tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;
import Tabuleiro.Posicao;

public class Peao extends PecaXadrez {
    public Peao(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public boolean[][] possivelMovimento(){
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0,0);

        if(getCor() == Cor.BRANCO){
            p.setValores(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(posicao.getLinha() - 2, posicao.getColuna());
           Posicao p2 =  new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().temPeca(p2) && getMovieCount() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().existePosicao(p) && existePecaAdv(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().existePosicao(p) && existePecaAdv(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }



        }
        else{
            p.setValores(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2 =  new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().temPeca(p2) && getMovieCount() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().existePosicao(p) && existePecaAdv(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().existePosicao(p) && existePecaAdv(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
        }
        return mat;
    }
    public String toString(){
        return "p";
    }
}
