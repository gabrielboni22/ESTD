package arvores;
import java.util.ArrayList;
import java.util.List;

public class ArvoreAvlAleatoria<T extends Comparable<T>> {
    class Elemento {
        Elemento pai;
        Elemento esquerda;
        Elemento direita;
        T valor;

        public Elemento(T valor) {
            this.valor = valor;
        }
    }

    public static int count;
    private Elemento raiz;

    public boolean isVazia() {
        return raiz == null;
    }

    public Elemento adicionar(T valor) {
        Elemento e = new Elemento(valor);
        Elemento pai = this.raiz;
        count++;

        while (pai != null) {
            count++;

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

        while (elemento != null) {
            int fator = fb(elemento);

            if (fator > 1) {
                if (fb(elemento.esquerda) > 0) {
                    rsd(elemento);
                } else {
                    rdd(elemento);
                }
            } else if (fator < -1) {
                if (fb(elemento.direita) < 0) {
                    rse(elemento);
                } else {
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

    public Elemento pesquisar(Elemento e, T valor) {
        while (e != null) {
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

        while (e.pai != null) { // Enquanto não alcançamos a raiz
            contador++;
            e = e.pai;
        }
        return contador;
    }

    private int altura(Elemento e) {        
        try {            
            int esquerda = 0, direita = 0;
    
            if (e.esquerda != null) {
                esquerda = altura(e.esquerda) + 1;
            }    
            if (e.direita != null) {
                direita = altura(e.direita) + 1;
            }
            return esquerda > direita ? esquerda : direita;
        } catch (StackOverflowError ex) {
            while(e != null) {
                e = e.pai;
            }            
            throw ex;
        }
    }

    private int fb(Elemento e) {
        count++;

        int esquerda = 0, direita = 0;

        if (e.esquerda != null) {
            esquerda = altura(e.esquerda) + 1;
        }
        if (e.direita != null) {
            direita = altura(e.direita) + 1;
        }
        return esquerda - direita;
    }

    private Elemento rse(Elemento e) {
        count++;
        Elemento pai = e.pai;
        Elemento direita = e.direita;

        if(direita.esquerda !=  null){
            direita.esquerda.pai = e;
        }
        e.direita = direita.esquerda;
        e.pai = direita;

        direita.esquerda = e;
        direita.pai = pai;

        if (pai == null) {
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
        count++;
        Elemento pai = e.pai;
        Elemento esquerda = e.esquerda;

        if(esquerda.direita != null){
            esquerda.direita.pai = e;
        }
        e.esquerda = esquerda.direita;
        e.pai = esquerda;

        esquerda.direita = e;
        esquerda.pai = pai;

        if (pai == null) {
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

    public static void vetorAleatorio(int n) {
        ArvoreAvlAleatoria<Integer> a = new ArvoreAvlAleatoria<>();
        int numero;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            do {
                numero = ArvoreAvlAleatoria.gerarNumeroAleatorio(n);
            } while (list.contains(numero));
            list.add(numero);
            a.adicionar(numero);
        }
    }

    public static int gerarNumeroAleatorio(int n) {
        int v = (int) (Math.random() * n * 1000);
        return v;
    }
}