public class PilhaContato {
    private Contato vetor[];
    private int topo;

    public PilhaContato(int tamanho){
        vetor = new Contato[tamanho];
        topo = 0;
    }

    public boolean vazia(){
        return topo == 0;
    }

    public boolean cheia(){
        return vetor.length == topo;
    }

    public void push(Contato valor) throws PilhaCheiaException{
        if (cheia()){
            throw new PilhaCheiaException();
        }
        vetor[topo] = valor;
        topo++; 
    }

    public Contato pop() throws PilhaVaziaException{
        if (vazia()){
            throw new PilhaVaziaException();
        } 
        topo--;
        Contato valor = vetor[topo];
        return valor;
    }
}