package application.action;

import application.GenericActionAgenceBancaire;
import banque.AgenceBancaire;

public class ActionDeposer extends GenericActionAgenceBancaire {

    public ActionDeposer(String message, String code) {
        super(message, code);
    }

    @Override
    public void execute(AgenceBancaire ag) throws Exception {
        // TODO Auto-generated method stub

    }

}
