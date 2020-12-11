package application.action;

import java.util.Locale;
import java.util.Scanner;

import application.GenericActionAgenceBancaire;
import banque.AgenceBancaire;
import banque.Compte;

public class ActionVoirCompteNumero extends GenericActionAgenceBancaire<AgenceBancaire> {

    public ActionVoirCompteNumero(String message, String code) {
        super(message, code);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute(AgenceBancaire ag) throws Exception {
    
		lect.useLocale(Locale.US);

        System.out.print("Num compte -> ");
        String numero = lect.next();
        Compte c = ag.getCompte(numero);
        if (c == null) {
            System.out.println("Compte inexistant ...");
        } else {
            c.afficher();
        }
    }

}
