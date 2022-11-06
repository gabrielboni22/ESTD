package arvores;

public class ArvoreRubroNegraOrdenada<T extends Comparable<T>> {
    enum Cor {
        Vermelho,
        Preto
    }
    
    class Elemento {
        Elemento pai;
        Elemento esquerda;
        Elemento direita;
        Cor cor;
        T valor;
    
        public Elemento(T valor) {
            this.valor = valor;
        }
    }

    public ArvoreRubroNegraOrdenada() {
        nulo = new Elemento(null);
        nulo.cor = Cor.Preto;

        raiz = nulo;
    }
    
    private Elemento raiz;
    private Elemento nulo;

    public static int count;

    public boolean isVazia() {
        return raiz == nulo;
    }

    public Elemento adicionar(T valor) {

        Elemento e = new Elemento(valor);
        e.cor = Cor.Vermelho;
        e.esquerda = nulo;
        e.direita = nulo;
        e.pai = nulo;
        
        Elemento pai = this.raiz;

        while (pai != nulo) {

            count ++; 

            if (valor.compareTo(pai.valor) < 0) {
                if (pai.esquerda == nulo) {
                    e.pai = pai;
                    pai.esquerda = e;
                    balanceamento(e);
                    
                    return e;
                } else {
                    pai = pai.esquerda;
                }
            } else {
                if (pai.direita == nulo) {
                    e.pai = pai;
                    pai.direita = e;
                    balanceamento(e);

                    return e;
                } else {
                    pai = pai.direita;
                }
            }
        }

        this.raiz = e;
        balanceamento(e);
        
        return e;
    }

    public void balanceamento(Elemento e) 
    {
        while (e.pai.cor == Cor.Vermelho) {  
            
            count ++; 

            Elemento pai = e.pai;
            Elemento avo = pai.pai;

            if (pai == avo.esquerda) {  
                Elemento tio = avo.direita;
                        
                if (tio.cor == Cor.Vermelho) {
                    tio.cor = Cor.Preto; 
                    pai.cor = Cor.Preto; 
                    avo.cor = Cor.Vermelho;
                    e = avo; 
                } else {
                    if (e == pai.direita) {
                        e = pai; 
                        rse(e);  
                    } else {
                        pai.cor = Cor.Preto;  
                        avo.cor = Cor.Vermelho;
                        rsd(avo);
                    }
                }
            } else {
                Elemento tio = avo.esquerda;
                        
                if (tio.cor == Cor.Vermelho) {
                    tio.cor = Cor.Preto;  
                    pai.cor = Cor.Preto; 
                    avo.cor = Cor.Vermelho;
                    e = avo;  
                } else {
                    if (e == pai.esquerda) {
                        e = pai;  
                        rsd(e);  
                    } else {
                        pai.cor = Cor.Preto;  
                        avo.cor = Cor.Vermelho;
                        rse(avo);
                    }
                }
            }
        }
        
        raiz.cor = Cor.Preto;  
    }

    private void rse(Elemento e) {

        count ++;

        Elemento direita = e.direita;
        e.direita = direita.esquerda; 
          
        if (direita.esquerda != nulo) {
          direita.esquerda.pai = e;
        }
          
        direita.pai = e.pai;  
              
        if (e.pai == nulo) {
            raiz = direita;  
        } else if (e == e.pai.esquerda) {
            e.pai.esquerda = direita; 
        } else {
            e.pai.direita = direita;  
        }
          
        direita.esquerda = e;  
        e.pai = direita;
    }

    private void rsd(Elemento e) {

        count ++; 

        Elemento esquerda = e.esquerda;
        e.esquerda = esquerda.direita;
              
        if (esquerda.direita != nulo) {
            esquerda.direita.pai = e;  
        }
              
        esquerda.pai = e.pai;  
              
        if (e.pai == nulo) {
            raiz = esquerda;  
        } else if (e == e.pai.esquerda) {
            e.pai.esquerda = esquerda;  
        } else {
            e.pai.direita = esquerda;  
        }
              
        esquerda.direita = e;  
        e.pai = esquerda;
    }


    public Elemento pesquisar(Elemento e, T valor) {
        while (e != nulo) {
            if (e.valor.equals(valor)) {
                return e;
            } else if (valor.compareTo(e.valor) > 0) {
                e = e.direita;
            } else {
                e = e.esquerda;
            }
        }
        
        return null;
    }

    public int caminho(Elemento e) {
        int contador = 1;

        while (e.pai != nulo) { 
            contador++;
            e = e.pai;
        }

        return contador;
    }

    public static void vetorOrdenado(int n) {

        ArvoreRubroNegraOrdenada<Integer> a = new ArvoreRubroNegraOrdenada<>();

        for (int i = 0; i < n; i++) {
            a.adicionar(i);
        }

    }

}