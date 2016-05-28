
import javax.swing.JFrame;

public class Jogo extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int larguraTela = 600;
	public int comprimentoTela = 1000;
	
	Fase fase;
	
    public Jogo(){
    	fase = new Fase(comprimentoTela, larguraTela);
    	add(fase);
        
        setTitle("Asteroides V1.1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(comprimentoTela,larguraTela);
        setLocationRelativeTo(null); //depois de setar o tamanho para aparecer no centro
        setResizable(false);
        setVisible(true);
    }

    public static void main (String [] args){
        new Jogo();
    }
}
