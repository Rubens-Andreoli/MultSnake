
import br.unip.engine.State;
import br.unip.engine.State.StateListener;




public class Main {

    public static void main(String[] args) {
        new GameWindow();	
    
	State.addStateListener(new StateListener(){
	    @Override
	    public void started() {
		System.out.println("stated");
	    }

	    @Override
	    public void ended() {
		System.out.println("ended");
	    }

	    @Override
	    public void paused(boolean isPause) {
		System.out.println("isPause = "+isPause);
	    }
	});
    }
    
    
    
    
}
