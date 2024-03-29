package PecasXadrez;

import Tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Tabuleiro.Posicao;

public class Peao extends PecaXadrez {
    private PartidaXadrez partidaXadrez;
    public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
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
            //movimento especial en passant branca
            if (posicao.getLinha() == 3){
                Posicao left = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().existePosicao(left) && existePecaAdv(left) && getTabuleiro().peca(left) == partidaXadrez.getEnPassanteVulneravel()){
                    mat[left.getLinha() -1][left.getColuna()] = true;
                }
                Posicao rigth = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().existePosicao(rigth) && existePecaAdv(left) && getTabuleiro().peca(rigth) == partidaXadrez.getEnPassanteVulneravel()){
                    mat[rigth.getLinha() -1][rigth.getColuna()] = true;
                }

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
        //movimento especial en passant preto
        if (posicao.getLinha() == 4){
            Posicao left = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
            if (getTabuleiro().existePosicao(left) && existePecaAdv(left) && getTabuleiro().peca(left) == partidaXadrez.getEnPassanteVulneravel()){
                mat[left.getLinha() + 1][left.getColuna()] = true;
            }
            Posicao rigth = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
            if (getTabuleiro().existePosicao(rigth) && existePecaAdv(left) && getTabuleiro().peca(rigth) == partidaXadrez.getEnPassanteVulneravel()){
                mat[rigth.getLinha() + 1][rigth.getColuna()] = true;
            }

        }
        return mat;
    }
    public String toString(){
        return "P";
    }
}
