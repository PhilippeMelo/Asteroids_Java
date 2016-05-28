import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fase extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int largTela; //Largura da Tela -> Recebe de Jogo
	public int compTela; //Comprimento da Tela -> Recebe de Jogo
	
	public int PONTOS = 0;
	public int vidas;
    private Image fundo;
    private Nave nave;
    private Timer timer;
    private boolean emJogo;
    
    List<Meteoro> meteoros;
    List<Estrela> stars;
    Explosao explosao;
    int x_explosao, y_explosao;
    boolean ativarExplosao;
    int temporizadorExplosao;

    

    public Fase(int comprimentoDaTela, int larguraDaTela){
    	
    	largTela = larguraDaTela;
    	compTela = comprimentoDaTela;
    	
    	temporizadorExplosao = 1;
    	vidas = 5;
        
        ativarExplosao = false;
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());

        ImageIcon referencia = new ImageIcon ("res//fundo2.png");
        fundo = referencia.getImage();
        
        explosao = new Explosao(0,0);
        nave = new Nave();

        emJogo = true;

        inicializaEstrelas(); //Cria as estrelas que compõem o cenário
        inicializaMeteoros(); //Cria os meteoros

        timer = new Timer(5, this);
        timer.start();
        
        //Esse codigo está aqui apenas para inicializar o Start do Audio - Isso poderia ser melhorado
        PlayAudio p = new PlayAudio("res\\inicializeAudio.mp3");
        p.start();
        
    }
    
    public void inicializaEstrelas(){
        stars = new ArrayList<Estrela>();
        for (int i=0; i<25; i++){
        	
        	Random r = new Random();
            Double db_y = r.nextDouble()*600;
            Double db_x = r.nextDouble()*1100;
            Double s_vel = r.nextDouble()/6;
        	stars.add(new Estrela(db_x, db_y, s_vel,"white"));
        }
        for (int i=0; i<25; i++){
        	Random r = new Random();
            Double db_y = r.nextDouble()*600;
            Double db_x = r.nextDouble()*1100;
            Double s_vel = r.nextDouble()/6;
        	stars.add(new Estrela(db_x, db_y, s_vel,"green"));
        }
        for (int i=0; i<25; i++){
        	Random r = new Random();
            Double db_y = r.nextDouble()*600;
            Double db_x = r.nextDouble()*1100;
            Double s_vel = r.nextDouble()/3;
        	stars.add(new Estrela(db_x, db_y, s_vel,"blue"));
        }
        for (int i=0; i<25; i++){
        	Random r = new Random();
            Double db_y = r.nextDouble()*600;
            Double db_x = r.nextDouble()*1100;
            Double s_vel = r.nextDouble()/3;
        	stars.add(new Estrela(db_x, db_y, s_vel,"yellow"));
        }
    }

    public void inicializaMeteoros(){

        meteoros = new ArrayList<Meteoro>();
        
        //O jogo terá 3 asteróides, que se movem em forma de loop, sempre retornando
        meteoros.add(new Meteoro(900, 300, (double)compTela));
        meteoros.add(new Meteoro(1100, 40, (double)compTela));
        meteoros.add(new Meteoro(1200, 500, (double)compTela));

    }

    public void tocarMp3Explosao(){

        Random r = new Random();
        int numero = r.nextInt(10);
        int musicaEscolhida = 1;
        if (numero >= 0 && numero <4){
            musicaEscolhida = 1;
        }
        if (numero >= 4 && numero <8){
            musicaEscolhida = 2;
        }
        if (numero >= 8 && numero <=10){
            musicaEscolhida = 3;
        }
        
        String dirMP3 = "res\\exp"+musicaEscolhida+".mp3";
        PlayAudio p = new PlayAudio(dirMP3);
        p.start();
        
    }

    public void tocarMp3GameOver(){
    	
    	String dirMP3 = "res\\gameover.mp3";
        PlayAudio p = new PlayAudio(dirMP3);
        p.start();

    }
    
    public void atualizaFormaNave(){
    	
    	 if (vidas >= 2 && vidas <=3){ //Se as vidas estiverem entre 2 ou 3, a nave quebra as asas
         	nave.setFormaNave("res//nave2.png");
    	 }
         if (vidas <2){ //Se as vidas forem igual a 1, a nave quebra aerofolio
         	nave.setFormaNave("res//nave3.png");
         }
    }
    
    public void checarColisoes(){
    	
        Rectangle formaNave = nave.getBounds();
        Rectangle formaMeteoro;
        Rectangle formaMissil;

        for (int i=0; i<meteoros.size(); i++){
            Meteoro tempMeteoro = meteoros.get(i);
            formaMeteoro = tempMeteoro.getBounds();

            if (formaNave.intersects(formaMeteoro) && emJogo == true){
                
                vidas -= 1;
                Random r = new Random();
                Double novoX = r.nextDouble()*500 + 1100;
                Double novoY = r.nextDouble()*400 + 50;
                tempMeteoro.setX(novoX);
                tempMeteoro.setY(novoY);
                tempMeteoro.VELOCIDADE += 0.4;
                
                atualizaFormaNave();
                x_explosao = (int)nave.getX()+20;
                y_explosao = (int)nave.getY()-10;

                ativarExplosao = true;
                
                tocarMp3Explosao();
                                
                if (vidas < 1){
                	emJogo = false;
                	nave.setIsVisivel(false);
                    
                }
            }
        }

        List<Missil> misseis = nave.getMisseis();
        for (int i=0; i < misseis.size(); i++){
            Missil tempMissil = misseis.get(i);
            formaMissil = tempMissil.getBounds();

            for (int j=0; j<meteoros.size(); j++){
                Meteoro tempMeteoro = meteoros.get(j);
                formaMeteoro = tempMeteoro.getBounds();

                if (formaMissil.intersects(formaMeteoro) && emJogo == true){
                    
                	x_explosao = tempMissil.getX()+30;
                    y_explosao = tempMissil.getY()-50;
                    
                    ativarExplosao = true;
                    
                    //Caso o tiro acerte a pedra, muda a posicao da pedra
                    Random r = new Random();
                    Double novoX = r.nextDouble()*500 + 1100;
                    Double novoY = r.nextDouble()*400 + 50;
                    tempMeteoro.setX(novoX);
                    tempMeteoro.setY(novoY);
                    tempMeteoro.VELOCIDADE += 0.3;
                    PONTOS += 5;
                    
                    tempMissil.setVisivel(false);
                    tocarMp3Explosao();
                   
                    
                }
            }
        }
    }

    public void paint(Graphics g){
        
    	Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo,0,0,null);
        
        if (emJogo){
        	
        	//Desenha estrelas
        	for (int i=0; i<stars.size(); i++){
        		graficos.drawImage(stars.get(i).getImagem(),(int)stars.get(i).x,(int)stars.get(i).y,this);
        	}
        	
        	//Desenha nave
            graficos.drawImage(nave.getImagem(),nave.getX(),nave.getY(),this);
            
            //Desenha mísseis
            List<Missil> misseis = nave.getMisseis();
            for (int i=0; i<misseis.size(); i++){
                Missil m = (Missil)misseis.get(i);
                graficos.drawImage(m.getImagem(),m.getX(),m.getY(),this);
            }
            
            //Desenha meteoros (inimigos)
            for (int i=0; i<meteoros.size(); i++){
                Meteoro in = meteoros.get(i);
                int xx = (int) in.getX();
                int yy = (int) in.getY();
                graficos.drawImage(in.getImagem(),xx,yy,this);
            }
            
            //Desenha explosão, caso seja a hora certa (colisão)
            if (ativarExplosao == true){
                                
                graficos.drawImage(explosao.getImagem(), x_explosao, y_explosao,this);
                
                temporizadorExplosao++;
                if (temporizadorExplosao > 150){ //a tela atualiza a cada 5 milisegundos. Quando o loop atualizar 400 vezes = 2 segundos (duracao do GIF)
               	 temporizadorExplosao = 0;
               	 ativarExplosao = false;
                }
           	
           }
            
            graficos.setColor(Color.WHITE);
            graficos.drawString("PONTOS: "+ PONTOS , 5, 15); //Desenha a pontuacao na tela
            graficos.drawString("VIDAS: "+ vidas , 5, 30); //Desenha a quantidade de vidas na tela
            
        }else{
        	
           	ImageIcon fimJogo;
            fimJogo = new ImageIcon ("res\\game_over.png");
            graficos.drawImage(fimJogo.getImage(),compTela/2 - fimJogo.getIconWidth()/2,largTela/4,null);

            graficos.setColor(Color.WHITE);
            graficos.drawString("VOCÊ PERDEU! SUA PONTUAÇÃO FOI: "+ PONTOS , compTela/2 - fimJogo.getIconWidth()/2, largTela/4 + 
            		fimJogo.getIconHeight()*2);

            g.dispose();
        }
    }

    public void actionPerformed(ActionEvent arg0){
    	
    	if (vidas < 1){ //Essa condição foi feita para criar somente uma única thread TOCARMP3GAMEOVER
    		tocarMp3GameOver();
			vidas += 1;
    	}

    	//Move os mísseis, caso eles estejam visíveis
        List<Missil> misseis = nave.getMisseis();
        for(int i=0; i<misseis.size(); i++){
            Missil m = (Missil) misseis.get(i);
            if (m.isVisivel()){
                m.mexer();
            }else{
                misseis.remove(i);
            }
        }
        
      //Move os meteoros, caso eles estejam visíveis
        for(int i=0; i<meteoros.size(); i++){
            Meteoro in = meteoros.get(i);
            if (in.isVisivel()){
                in.mexer();
            }else{
                meteoros.remove(i);
            }
        }
        
        //move as estrelas
        for (int i=0; i<stars.size(); i++){
    		stars.get(i).mexer();
    	}

        nave.mexer(); //move a nave
        checarColisoes(); //checa colisoes
        repaint();
       
       
    }


    public void setLargTela(int largTela) {
		this.largTela = largTela;
	}

	public void setCompTela(int compTela) {
		this.compTela = compTela;
	}

	private class TecladoAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e){
            nave.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e){
            nave.keyReleased(e);
        }
    }

}

