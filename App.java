import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        PilhaContato pilha = new PilhaContato(3);
        Scanner scan = new Scanner(System.in);

        ArrayList<Contato> agenda = new ArrayList<>();
        int escolha = 0;
        while(escolha != 5){
            System.out.println("\n------------MENU----------------");
            System.out.println("    O que você deseja fazer?    ");
            System.out.println("     1 - Adicionar Contato      ");
            System.out.println("     2 - Alterar Contato        ");
            System.out.println("     3 - Desfazer Alteração     ");
            System.out.println("     4 - Exibir Contatos        ");
            System.out.println("     5 - Sair                   ");
            
            escolha = scan.nextInt();

            switch (escolha) {
                case 1:
                    boolean maisContatos = false;
                    int id = 0;
                    do{
                        System.out.println("\nInforme seu nome:");
                        String nome = scan.next();
                        System.out.println("Informe seu e-mail:");
                        String email = scan.next();
                        System.out.println("Informe seu telefone:");
                        String telefone = scan.next();
                        id++;
                        agenda.add(new Contato(id, nome, telefone, email));
            
                        System.out.println("\nDeseja adicionar mais algum contato? Sim/Não");
                        String res = scan.next();
            
                        maisContatos = res.equalsIgnoreCase("Sim");
                    }while(maisContatos);
                    break;
                case 2:
                    for (Contato contato : agenda) {
                        System.out.println(contato);
                    }
                    System.out.println("\nIndique o ID do contato que você deseja alterar: ");
                    int idAlterar = scan.nextInt();
                    System.out.println("\nInforme seu nome:");
                    String nome = scan.next();
                    System.out.println("Informe seu e-mail:");
                    String email = scan.next();
                    System.out.println("Informe seu telefone:");
                    String telefone = scan.next();
                    Contato contatoAlterar = new Contato(idAlterar, nome, telefone, email);
                    pilha.push(agenda.get(idAlterar-1));
                    agenda.set(idAlterar-1, contatoAlterar);
                    System.out.println("\nContato Alterado:");
                    System.out.println(agenda.get(idAlterar-1));
                    break;
                case 3:
                    for (Contato contato : agenda) {
                        System.out.println(contato);
                    }
                    System.out.println("\nIndique o ID do contato que você deseja desfazer a alteração: ");
                    int idDesfazer = scan.nextInt();
                    agenda.set(idDesfazer-1, pilha.pop());
                    System.out.println("\nAlteração Desfeita!!!");
                    System.out.println(agenda.get(idDesfazer-1));
                    break;
                case 4:
                    System.out.println("\nSua Agenda:");
                    for (Contato contato : agenda) {
                        System.out.println(contato);
                    }
                    break;
                default:
                    break;
            }
        }
        scan.close();
    }
}


