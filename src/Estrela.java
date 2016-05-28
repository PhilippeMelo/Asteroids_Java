
import java.awt.Image;
import javax.swing.ImageIcon;

public class Estrela {

    public double x,y;
    public Image imagem;
    public double VELOCIDADE;
    private boolean isVisivel;

    public Estrela(double x, double y, double vel, String color){
    	
    	this.VELOCIDADE = vel;
        this.x = x;
        this.y = y;

        ImageIcon referencia = new ImageIcon("res//p_"+color+".png");
        imagem = referencia.getImage();
        setIsVisivel(true);

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

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void mexer(){

            if (this.x < - 10){
                this.x = 1000;
            }else{
            this.x -= this.VELOCIDADE;
        }
        
    }
}