package application.actionList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import action.Action;
import action.ActionList;
import banque.AgenceBancaire;

public class ActionListAgenceBancaire implements ActionList<AgenceBancaire> {

    private String message;
    private String code;
    private String title;
    private List<Action> arAction;

    public ActionListAgenceBancaire(String code, String title) {
        this.code = code;
        this.title = title;
        this.message = "Menu " + title;
        this.arAction = new ArrayList<Action>();
    }

    @Override
    public String actionMessage() {
        return message;
    }

    @Override
    public String actionCode() {
        return code;
    }

    @Override
    public void execute(AgenceBancaire ag) throws Exception {
        boolean continuer;
        int choix;

        continuer = true;
        while (continuer) {
            this.printTitle(ag);
            this.printMenu();
            choix = this.readResponse();
            if (choix == 0) {
                System.out.println("Fin de " + this.title + "\n");
                continuer = false;
            } else
                this.getAction(choix - 1).execute(ag);
                // this.tempo();
        }
    }

    @Override
    public String listTitle() {
        return title;
    }

    @Override
    public int size() {
        return arAction.size();
    }

    @Override
    public boolean addAction(Action ac) {
        if (!this.arAction.contains(ac)) {
            this.arAction.add(ac);
            return true;
        }
        return false;
    }

    private int readResponse() {
        String choix;
        int rep;

        lect.useLocale(Locale.US);
        System.out.print("Votre choix ?\n");

        while (true) {
            choix = lect.next();
            choix = choix.toLowerCase();
            try{
                rep = Integer.parseInt(choix);
                if (rep >= 0 && rep <= this.size()) {
                    break;
                }
            } catch (Exception e ){
                System.out.println("Vous devez saisir un entier entre 0 et " + this.size() + " : ");
            }
        }
        return rep;
    }

    private void printMenu() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println("\t" + (i + 1) + " - " + arAction.get(i).actionMessage());
        }
        System.out.println("\n0 - Pour quitter ce menu");
    }

    private void printTitle(AgenceBancaire ac) {
        System.out.println("--\n  Agence " + ac.getNomAgence() + " de " + ac.getLocAgence() + "\n  " + this.listTitle()
                + "\n--\n");
    }

    // inutilisé par choix
    private void tempo() {
        // System.out.print("Tapper un car + return pour continuer ... ");
        lect.next();
    }

    // @Override
    // public boolean removeAction(Action ac) {
    //     Iterator<Action> it = this.arAction.iterator();
    //     Action action;
    //     while (it.hasNext()) {
    //         action = it.next();
    //         if (ac.equals(action)) {
    //             it.remove();
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    
    public Action getAction(int index) throws IndexOutOfBoundsException {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            return this.arAction.get(index);
        }
    }
}
