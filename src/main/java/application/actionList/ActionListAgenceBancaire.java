package application.actionList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import action.Action;
import action.ActionList;
import banque.AgenceBancaire;

public class ActionListAgenceBancaire implements ActionList {

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
                arAction.get(choix - 1).execute(ag);
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
        Scanner lect;
        int rep;

        lect = new Scanner(System.in);
        lect.useLocale(Locale.US);

        while (true) {
            choix = lect.next();
            choix = choix.toLowerCase();
            rep = Integer.parseInt(choix);
            if (rep >= 0 && rep <= this.size()) {
                lect.close();
                break;
            }
        }
        return rep;
    }

    private void printMenu() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println("\t" + (i + 1) + " - " + arAction.get(i));
        }
        System.out.println("\n0 - Pour quitter ce menu");
        System.out.print("Votre choix ?\n");
    }

    private void printTitle(AgenceBancaire ac) {
        System.out.println("--\n  Agence " + ac.getNomAgence() + " de " + ac.getLocAgence() + "\n  " + this.listTitle()
                + "\n--\n");
    }

    private void tempo() {
        Scanner lect;

        lect = new Scanner(System.in);

        System.out.print("Tapper un car + return pour continuer ... ");
        lect.next(); // Inutile � stocker mais ...
        lect.close();
    }
}
