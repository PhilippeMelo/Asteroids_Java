import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;



public class Nave {

    int x,y;
    private int dx, dy;
    private Image imagem;

    private List<Missil> misseis;
    private int altura, largura;
    private boolean isVisivel;
    
  

    public Nave(){
    	
        ImageIcon referencia = new ImageIcon("res//nave1.png");
        imagem = referencia.getImage();

        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);

        this.x = 100;
        this.y = 100;

        misseis = new ArrayList<Missil>();
    }
    
    public void setFormaNave(String s){
    	ImageIcon referencia = new ImageIcon(s);
        imagem = referencia.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    public boolean isIsVisivel() {
        return isVisivel;
    }

    public void setIsVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public List<Missil> getMisseis() {
        return misseis;
    }

    public void setMisseis(List<Missil> misseis) {
        this.misseis = misseis;
    }

    public void setAltura(int a){
    	this.altura = a;
    }
    
    public void setLargura(int a){
    	this.largura = a;
    }
    
    public void atirar(){
        this.misseis.add(new Missil(x + largura - 10, y + altura/2));
    }

    public Rectangle getBounds(){
       return new Rectangle(x,y,largura,altura);
    }
    
    public void mexer(){
        
        //System.out.println("cordenadas: "+x +" "+y);
        x += dx; // 1 e 413
        y += dy; //1 e 267

        if (this.x < 1){
            x = 1;
        }
        if (this.y < 1){
            y = 1;
        }
        if (this.x > 950 && this.x != 8000){ //Na posição 8000x8000 impedimos que a nave choque com os meteoros apos o Game-Over
            x = 950;
        }
        if (this.y > 480 && this.x != 8000){
            y = 480;
        }
    }

    public void keyPressed(KeyEvent tecla){ //Tecla pressionada
        int codigo = tecla.getKeyCode();

        //Se apertar ESPAÇO, atira
        if (codigo == KeyEvent.VK_SPACE){
            atirar();
        }
        
        //Se apertar CIMA, diminue cordenada Y (sobe)
        if (codigo == KeyEvent.VK_UP){ 
            dy = -6;
        }
        
        //Se apertar BAIXO, aumenta cordenada Y (desce)
        if (codigo == KeyEvent.VK_DOWN){ 
            dy = 6;
        }
         if (codigo == KeyEvent.VK_LEFT){
            dx = -6;
        }
        if (codigo == KeyEvent.VK_RIGHT){
            dx = 6;
        }
    }
    
    public void keyReleased(KeyEvent tecla){ //Tecla não pressionada
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP){
            dy = 0;
        }
        if (codigo == KeyEvent.VK_DOWN){
            dy = 0;
        }
         if (codigo == KeyEvent.VK_LEFT){
            dx = 0;
        }
        if (codigo == KeyEvent.VK_RIGHT){
            dx = 0;
        }
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


}