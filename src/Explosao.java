import java.awt.Image;
import javax.swing.ImageIcon;


public class Explosao {

    public int x,y;
    private Image imagem;
    private boolean isVisivel;

    public Explosao(int x, int y){
        this.x = x;
        this.y = y;

        ImageIcon referencia = new ImageIcon("res//exp.gif");
        imagem = referencia.getImage();
        setIsVisivel(true);

    }

    public void mexer(){
        this.x += 1;
    }

    public boolean isIsVisivel() {
        return isVisivel;
    }

    public void setIsVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
