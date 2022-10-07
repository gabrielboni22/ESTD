import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ArvoreAvl<T extends Comparable<T>> {
    class Elemento {
        Elemento pai;
        Elemento esquerda;
        Elemento direita;
        T valor;
    
        public Elemento(T valor) {
            this.valor = valor;
        }
    }
  
    private Elemento raiz;
    private int cont;

    public boolean isVazia() {
        return raiz == null;
    }

    public Elemento adicionar(T valor) {
        Elemento e = new Elemento(valor);
        Elemento pai = this.raiz;

        System.out.println("Adicionando " + valor);
        cont++;
        while (pai != null) {
            if (valor.compareTo(pai.valor) < 0) {
                
                if (pai.esquerda == null) {
                    e.pai = pai;
                    pai.esquerda = e;
                    balanceamento(pai);
                    
                    return e;
                } else {
                    
                    pai = pai.esquerda;
                }
            } else {
                
                if (pai.direita == null) {
                    e.pai = pai;
                    pai.direita = e;
                    balanceamento(pai);
                    
                    return e;
                } else {
                    
                    pai = pai.direita;
                }
            }
        }

        this.raiz = e;
        return e;
    }

    public void balanceamento(Elemento elemento) {
        cont++;
        while (elemento != null) {
            int fator = fb(elemento);
            cont++;
            if (fator > 1) {
                
                //Arvore mais profunda para esquerda, rotação para a direita
                if (fb(elemento.esquerda) > 0) {
                    System.out.println("RSD(" + elemento.valor + ")");
                    rsd(elemento);
                    
                } else {
                    System.out.println("RDD(" + elemento.valor + ")");
                    rdd(elemento);
                    
                }
            } else if (fator < -1) {
                
                //Arvore mais profunda para direita, rotação para a esquerda
                if (fb(elemento.direita) < 0) {
                    System.out.println("RSE(" + elemento.valor + ")");
                    rse(elemento);
                    
                } else {
                    System.out.println("RDE(" + elemento.valor + ")");
                    rde(elemento);
                    
                }
            }

            elemento = elemento.pai;
        }
    }
    
    public Elemento adicionar(Elemento pai, T valor) {
        Elemento e = new Elemento(valor);
        
        e.pai = pai;
         
        if (pai == null) {
            raiz = e;
        }
        
        return e;
    }


    private int altura(Elemento e){
        int esquerda = 0,direita = 0;
    
        if (e.esquerda != null) {
            esquerda = altura(e.esquerda) + 1;
        }
    
        if (e.direita != null) {
            direita = altura(e.direita) + 1;
        }
      
        return esquerda > direita ? esquerda : direita;
    }

    private int fb(Elemento e) {
        int esquerda = 0,direita = 0;
      
        if (e.esquerda != null) {
            esquerda = altura(e.esquerda) + 1;
            
        }
    
        if (e.direita != null) {
            direita = altura(e.direita) + 1;
            
        }
      
        return esquerda - direita;
    }
    
    private Elemento rse(Elemento e) {
        Elemento pai = e.pai;
        Elemento direita = e.direita;
    
        e.direita = direita.esquerda;
        e.pai = direita;
    
        direita.esquerda = e;
        direita.pai = pai;

        if (direita.pai == null) {
            this.raiz = direita;
            
        } else {
            
             if (pai.esquerda == e) {
                
                pai.esquerda = direita;
            } else {
                
                pai.direita = direita;
            }
        }
      
        return direita;
    }

    private Elemento rsd(Elemento e) {
        Elemento pai = e.pai;
        Elemento esquerda = e.esquerda;
      
        e.esquerda = esquerda.direita;
        e.pai = esquerda;
      
        esquerda.direita = e;
        esquerda.pai = pai;

        if (esquerda.pai == null) {
            
            this.raiz = esquerda;
        } else {
            
            if (pai.esquerda == e) {
                
                pai.esquerda = esquerda;
            } else {
                
                pai.direita = esquerda;
            }
        }
      
        return esquerda;
    }
    
    private Elemento rde(Elemento e) {
        e.direita = rsd(e.direita);
        return rse(e);
    }
    
    private Elemento rdd(Elemento e) {
        e.esquerda = rse(e.esquerda);
        return rsd(e);
    }


    public static int[] worstcase(int n) {
        int v[] = new int[n];
        
        for (int i = 0; i < n; i++) {
            v[i] = n - i;
        }

        return v;
    }

    public static int[] averagecase(int n) {
        int v[] = new int[n];
        
        for (int i = 0; i < n; i++) {
            v[i] = (int) (Math.random() * n);
        }

        return v;
    }
    public static void main(String args[]) throws IOException {
        FileWriter arq = new FileWriter("arvAvl.txt");
        PrintWriter gravararq = new PrintWriter(arq);

        int num = 1;
        


            ArvoreAvl<Integer> a = new ArvoreAvl<>();
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