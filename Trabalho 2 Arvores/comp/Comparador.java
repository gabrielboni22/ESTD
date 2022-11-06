package comp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import arvores.ArvoreAvlAleatoria;
import arvores.ArvoreAvlOrdenada;
import arvores.ArvoreBAleatoria1;
import arvores.ArvoreBOrdenada1;
import arvores.ArvoreBAleatoria5;
import arvores.ArvoreBOrdenada5;
import arvores.ArvoreBAleatoria10;
import arvores.ArvoreBOrdenada10;
import arvores.ArvoreRubroNegraAleatoria;
import arvores.ArvoreRubroNegraOrdenada;

public class Comparador {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("output.csv")));

        int n = 100;
        int repeticoes = 2;

        writer.write("N;AvoreAVL-Ordenada;AvoreAVL-Aleatoria;AvoreRubroNegra-Ordenada;AvoreRubroNegra-Aleatoria;ArvoreB-Ordenada-1;ArvoreB-Aleatoria-1;ArvoreB-Ordenada-5;ArvoreB-Aleatoria-5;ArvoreB-Ordenada-10;ArvoreB-Aleatoria-10;\n");

        for (int i = 1; i <= n; i++) {

            int somaAVLOrd = 0;
            int somaAvlAlt = 0;

            int somaRubroNegraOrd = 0;
            int somaRubroNegraAlt = 0;

            int somaArvoreBOrd1 = 0;
            int somaArvoreBAlt1 = 0;
            
            int somaArvoreBOrd5 = 0;
            int somaArvoreBAlt5 = 0;
            
            int somaArvoreBOrd10 = 0;
            int somaArvoreBAlt10 = 0;

            for (int j = 0; j < repeticoes; j++) {

                ArvoreAvlOrdenada.vetorOrdenado(i);
                ArvoreAvlAleatoria.vetorAleatorio(i);

                ArvoreRubroNegraOrdenada.vetorOrdenado(i);
                ArvoreRubroNegraAleatoria.vetorAleatorio(i);

                ArvoreBOrdenada1.vetorOrdenado(i, 1);
                ArvoreBAleatoria1.vetorAleatorio(i, 1);

                ArvoreBOrdenada5.vetorOrdenado(i, 5);
                ArvoreBAleatoria5.vetorAleatorio(i, 5);

                ArvoreBOrdenada10.vetorOrdenado(i, 10);
                ArvoreBAleatoria10.vetorAleatorio(i, 10);

                somaAVLOrd += ArvoreAvlOrdenada.count;
                somaAvlAlt += ArvoreAvlAleatoria.count;

                somaRubroNegraOrd += ArvoreRubroNegraOrdenada.count;
                somaRubroNegraAlt += ArvoreRubroNegraAleatoria.count;

                somaArvoreBOrd1 += ArvoreBOrdenada1.count;
                somaArvoreBAlt1 += ArvoreBAleatoria1.count;

                somaArvoreBOrd5 += ArvoreBOrdenada5.count;
                somaArvoreBAlt5 += ArvoreBAleatoria5.count;

                somaArvoreBOrd10 += ArvoreBOrdenada10.count;
                somaArvoreBAlt10 += ArvoreBAleatoria10.count;

            }

            writer.write(i + ";" +
                    (somaAVLOrd) + ";" + (somaAvlAlt / repeticoes ) + ";" + 
                    (somaRubroNegraOrd) + ";" +  (somaRubroNegraAlt / repeticoes) + ";" + 
                    (somaArvoreBOrd1) + ";" +(somaArvoreBAlt1 / repeticoes) + ";" +
                    (somaArvoreBOrd5) + ";" +(somaArvoreBAlt5 / repeticoes) + ";" +
                    (somaArvoreBOrd10) + ";" +(somaArvoreBAlt10 / repeticoes) + ";" +
                    "\n");

            ArvoreAvlOrdenada.count = 0;
            ArvoreAvlAleatoria.count = 0;

            ArvoreRubroNegraOrdenada.count = 0;
            ArvoreRubroNegraAleatoria.count = 0;

            ArvoreBOrdenada1.count = 0;
            ArvoreBAleatoria1.count = 0;

            ArvoreBOrdenada5.count = 0;
            ArvoreBAleatoria5.count = 0;

            ArvoreBOrdenada10.count = 0;
            ArvoreBAleatoria10.count = 0;
        }

        writer.close();
    }
}
