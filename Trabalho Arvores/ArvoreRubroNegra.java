import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ArvoreRubroNegra<T extends Comparable<T>> {
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

    public ArvoreRubroNegra() {
        nulo = new Elemento(null);
        nulo.cor = Cor.Preto;

        raiz = nulo;
    }
    
    private Elemento raiz;
    private Elemento nulo;
    private int cont;

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

        System.out.println("Adicionando " + valor);
        cont++;
        while (pai != nulo) {
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

    public void balanceamento(Elemento e) {
        cont++;
        while (e.pai.cor == Cor.Vermelho) {  // Garante que todos os níveis foram balanceados 
            cont++;
            Elemento pai = e.pai;
            Elemento avo = pai.pai;
            


            if (pai == avo.esquerda) {  // Identifica o lado (esquerda ou direita)
                Elemento tio = avo.direita;
                        
                if (tio.cor == Cor.Vermelho) {
                    tio.cor = Cor.Preto;  // Resolve o caso 2
                    pai.cor = Cor.Preto; 
                    avo.cor = Cor.Vermelho;
                    e = avo;  // Vai para o nível anterior (avô)
                } else {
                    if (e == pai.direita) {
                        e = pai;  // Vai para o nível anterior
                        rse(e);  // Resolve o caso 3
                        
                    } else {
                        pai.cor = Cor.Preto;  // Resolve o caso 4
                        avo.cor = Cor.Vermelho;
                        rsd(avo);
                        
                    }
                }
            } else {
                Elemento tio = avo.esquerda;
                        
                if (tio.cor == Cor.Vermelho) {
                    tio.cor = Cor.Preto;  // Resolve o caso 2
                    pai.cor = Cor.Preto; 
                    avo.cor = Cor.Vermelho;
                    e = avo;  // Vai para o nível anterior (avô)
                } else {
                    if (e == pai.esquerda) {
                        e = pai;  // Vai para o nível anterior
                        rsd(e);  // Resolve o caso 3
                        
                    } else {
                        pai.cor = Cor.Preto;  // Resolve o caso 4
                        avo.cor = Cor.Vermelho;
                        rse(avo);
                        
                    }
                }
            }
        }
        
        raiz.cor = Cor.Preto;  // Resolve caso 1

    }

    private int altura(Elemento e){
        int esquerda = 0,direita = 0;
    
        if (e.esquerda != nulo) {
            esquerda = altura(e.esquerda) + 1;
        }
    
        if (e.direita != nulo) {
            direita = altura(e.direita) + 1;
        }
      
        return esquerda > direita ? esquerda : direita;
    }

    private void rse(Elemento e) {
        cont++;
        Elemento direita = e.direita;
        e.direita = direita.esquerda; 
          
        if (direita.esquerda != nulo) {
          direita.esquerda.pai = e;
        }
          
        direita.pai = e.pai;  // Se houver filho à esquerda em direita, ele será pai do nó
              
        if (e.pai == nulo) {
            raiz = direita;  // Se nó for raiz, o nó direita será a nova raiz da árvore
        } else if (e == e.pai.esquerda) {
            e.pai.esquerda = direita;  // Corrige relação pai-filho do novo pai (esquerda)
        } else {
            e.pai.direita = direita;  // Corrige relação pai-filho do novo pai (direita)
        }
          
        direita.esquerda = e;  // Corrige relação pai-filho entre o nó pivô e o nó à direita
        e.pai = direita;
    }

    private void rsd(Elemento e) {
        cont++;
        Elemento esquerda = e.esquerda;
        e.esquerda = esquerda.direita;
              
        if (esquerda.direita != nulo) {
            esquerda.direita.pai = e;  // Se houver filho à direita em esquerda, ele será pai do nó
        }
              
        esquerda.pai = e.pai;  // Ajusta no pai do nó à esquerda
              
        if (e.pai == nulo) {
            raiz = esquerda;  // Se nó for raiz, o nó esquerda será a nova raiz da árvore
        } else if (e == e.pai.esquerda) {
            e.pai.esquerda = esquerda;  // Corrige relação pai-filho do novo pai (esquerda)
        } else {
            e.pai.direita = esquerda;  // Corrige relação pai-filho do novo pai (direita)
        }
              
        esquerda.direita = e;  // Corrige relação pai-filho entre o nó pivô e o nó à esquerda
        e.pai = esquerda;
    }


    public static void main(String args[]) throws IOException {
        

        FileWriter arq = new FileWriter("arvRubro.txt");
        PrintWriter gravararq = new PrintWriter(arq);

        int num = 1;
        


            ArvoreRubroNegra<Integer> a = new ArvoreRubroNegra<>();
            //a.cont = 0;
            while (num < 1000) {
                a.adicionar(num);
                
                System.out.println(a.cont);
                num++;

                gravararq.printf("%d;%d\n", a.cont, num);
                
            } 
            
            System.out.println("------------------------");
            System.out.println(a.cont);
        
        arq.close();
        
    }
}