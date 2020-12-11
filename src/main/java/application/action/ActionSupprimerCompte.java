package application.action;

import application.GenericActionAgenceBancaire;
import banque.AgenceBancaire;
import banque.Compte;

public class ActionSupprimerCompte extends GenericActionAgenceBancaire<AgenceBancaire> {

    public ActionSupprimerCompte(String message, String code) {
        super(message, code);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute(AgenceBancaire ag) throws Exception {
        Compte c;

        String numero;

        System.out.print("Num compte -> ");
        numero = lect.next();


        c = ag.getCompte(numero);
        if (c == null) {
            System.out.println("Compte inexistant ...");
        } else {
            ag.removeCompte(numero);
        }
    }

}
