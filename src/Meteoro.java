import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Meteoro {

    public Image imagem;
    private double x,y;
    private double altura, largura;
    private boolean isVisivel;

    public double COMPRIMENTO_TELA;
    public double VELOCIDADE;

    private static int contador = 0; //se tirar o static nao funciona

    public Meteoro(int x, int y, double comp){
    	this.COMPRIMENTO_TELA = comp;
        this.x = x;
        this.y = y;
                    
        //Sorteia a velocidade entre 0.5 e 1.5 <-- Cada inimigo com velocidade especifica
        Random r = new Random();
        Double in_vel = r.nextDouble() + 0.5;
        this.VELOCIDADE = in_vel;
        
        //Fim do sorteio da velocidade

        ImageIcon referencia;
        //System.out.println(contador % 3); //testando o contador para escolher o tipo de meteoro
        if (contador % 3 == 0){

            referencia = new ImageIcon("res\\pedra11.png");
            ++contador;
        }else{
            referencia = new ImageIcon("res\\pedra22.png");
            ++contador;
        }
                
        imagem = referencia.getImage();

        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);
        isVisivel = true;
    }

    public Image getImagem() {
        return imagem;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    
    public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVELOCIDADE(double vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public boolean isVisivel(){
        return isVisivel;
    }

    public Meteoro(boolean Visivel) {
        this.isVisivel = Visivel;
    }

    public void mexer(){
        if (this.x < -70){
        	
        	Random r = new Random();
            Double novoX = r.nextDouble()*500 + COMPRIMENTO_TELA + 100;
            Double novoY = r.nextDouble()*400 + 50;
            setX(novoX);
            setY(novoY);
        	
            
        }else{
            this.x -= VELOCIDADE;
        }
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,(int)largura,(int)altura);
    }

}
