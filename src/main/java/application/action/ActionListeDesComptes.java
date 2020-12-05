package application.action;

import application.GenericActionAgenceBancaire;
import banque.AgenceBancaire;

public class ActionListeDesComptes extends GenericActionAgenceBancaire {

    public ActionListeDesComptes(String message, String code) {
        super(message, code);
        // TODO Auto-generated constructor stub
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
        ag.afficher();
    }

}
