package application.action;

import application.GenericActionAgenceBancaire;
import banque.AgenceBancaire;
import banque.Compte;
import banque.exception.CompteException;

public class ActionRetirer extends GenericActionAgenceBancaire {

    public ActionRetirer(String message, String code) {
        super(message, code);
    }

    @Override
    public void execute(AgenceBancaire ag) throws Exception {
        Compte c;

        String numero;
        double montant;

        System.out.print("Num compte -> ");
        numero = lect.next();
        System.out.print("Montant à retirer -> ");
        montant = lect.nextDouble();

        c = ag.getCompte(numero);
        if (c == null) {
            System.out.println("Compte inexistant ...");
        } else {
            System.out.println("Solde avant retrait : " + c.soldeCompte());
            try {
                c.retirer(montant);
                System.out.println("Montant retiré, solde : " + c.soldeCompte());
            } catch (CompteException e) {
                System.out.println("Erreur de dépôt, solde inchangé : " + c.soldeCompte());
                System.out.println(e.getMessage());
            }
        }

    }

}
