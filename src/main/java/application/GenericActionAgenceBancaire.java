package application;

import action.Action;
import banque.AgenceBancaire;

public abstract class GenericActionAgenceBancaire <AgenceBancaire> implements Action<AgenceBancaire> {
    protected String message;
    protected String code;

    public GenericActionAgenceBancaire(String code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String actionMessage() {
        return message;
    }

    @Override
    public String actionCode() {
        return code;
    }
}
