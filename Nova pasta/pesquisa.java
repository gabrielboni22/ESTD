public class pesquisa {
    public static int  pesquisaSequencial(int chave, int v[]){
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < v.length; i++){
            count1 += 2;
            if(v[i] == chave){
                count2 += 1;
                
                return i;

            }
            System.out.println(count2);
            System.out.println(count1);
        }
        
       
    return -1;
    } 


    public static int pesquisaSequencialSentinela(int chave, int v[]){
        int i = 0 ;
        int c[] = new int [v.length + 1];
        System.arraycopy(v, i, c, i, v.length);
        c[v.length] = chave;

        while (c[i] != chave) {
            i++;
        }

        

        if (i < v.length) return i;
        return -1;
    }



    public static int pesquisaBinaria(int chave, int v[]){
        int inicio = 0, meio, fim = v.length-1, count = 0;
        while(inicio<=fim){
            meio = (inicio + fim) / 2;
            if(chave <v[meio]){
                count ++;
                fim = meio - 1;
            } else if (chave > v[meio]){
                count ++;
                inicio = meio + 1;
            } else {
                return count;
            }
        };
        return count;
    }

    public static int pesquisaBinariaRecursiva(int chave, int v[], int ini, int fim){
        int meio = (ini + fim) / 2, count = 0;
        if(ini > fim){
            count ++;
            return -1;
        } 
        if (chave == v[meio]){
            count ++;
            return meio;
        } else if (chave < v[meio]) {
            return pesquisaBinariaRecursiva(chave, v, ini, meio - 1);
        } else {
            return pesquisaBinariaRecursiva(chave, v, meio + 1, fim );
        }
    };


    public static int[] bubblesort2(int v[]) {
        boolean troca;
        do {
            troca = false;
            for (int i = 0; i < v.length - 1; i++) {
                if (v[i] > v[i + 1]) {
                    int aux = v[i];
                    v[i] = v[i + 1];
                    v[i + 1] = aux;
                    troca = true;
                }
            }
        } while (troca);
        return v;
    }

    public static void main(String[] args) {

        int n = 1000;
        int x[] = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = (int) (Math.random() * n);
        };


        x = bubblesort2(x);

        System.out.println(pesquisaBinaria(x[400], x));
        System.out.println(pesquisaBinariaRecursiva(x[400], x, x[0], x[x.length - 1]));
        
        

    }
}




