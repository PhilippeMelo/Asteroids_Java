import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;



public class Missil {

    private Image imagem;
    private int x,y;
    private boolean isVisivel;
    private int largura, altura;

    private static final int LARGURA_TELA = 1000;
    private static final int VELOCIDADE = 8;

    public Missil(int x, int y){
        this.x = x;
        this.y = y;

        ImageIcon referencia = new ImageIcon("res//tiro.png");
        imagem = referencia.getImage();
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

        isVisivel = true;
    }

    public Image getImagem() {
        return imagem;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public boolean isVisivel(){
        return isVisivel;
    }

    public Missil(boolean Visivel) {
        this.isVisivel = Visivel;
    }

    public void mexer(){
        this.x += VELOCIDADE;

        if (this.x > LARGURA_TELA){
            isVisivel = false;
        }
    }
      public Rectangle getBounds(){
        return new Rectangle(x,y,largura,altura);
    }

}