package application.action;

import application.GenericActionAgenceBancaire;
import banque.AgenceBancaire;
import banque.Compte;
import banque.exception.ABCompteDejaExistantException;
import banque.exception.ABCompteNullException;

public class ActionAjouterCompte<E> extends GenericActionAgenceBancaire<E> {

    public ActionAjouterCompte(String message, String code) {
        super(message, code);
    }

    @Override
    public void execute(E ag) throws Exception {
        String numero;
        String proprietaire;

        System.out.print("Num compte -> ");
        numero = lect.next();

        System.out.print("Nom propriétaire -> ");
        proprietaire = lect.next();
        try {
            ag.addCompte(new Compte(numero, proprietaire));
        } catch (ABCompteNullException e) {
            e.printStackTrace();
        } catch (ABCompteDejaExistantException e) {
            e.printStackTrace();
        }
    }

}
