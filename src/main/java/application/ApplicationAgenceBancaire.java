package application;

import action.ActionList;
import application.action.ActionAjouterCompte;
import application.action.ActionDeposer;
import application.action.ActionListeDesComptes;
import application.action.ActionRetirer;
import application.action.ActionSupprimerCompte;
import application.action.ActionVoirCompteNumero;
import application.actionList.ActionListAgenceBancaire;
import banque.AgenceBancaire;

public class ApplicationAgenceBancaire {

	// static String[] menuGeneral = { "Liste des comptes de l'agence", "Voir un
	// compte (par son numéro)",
	// "Menu opérations sur comptes", "Menu gestion des comptes" };
	// static String[] menuOperations = { "Déposer de l'argent sur un compte",
	// "Retirer de l'argent sur un compte", };
	// static String[] menuGestion = { "Ajouter un compte", "Supprimer un compte" };

	public static void main(String[] args) {
		ActionList<AgenceBancaire> menuGeneral, menuOperations, menuGestion;
		menuGeneral = new ActionListAgenceBancaire(null, "Menu général");
		menuOperations = new ActionListAgenceBancaire("3", "Menu opérations sur comptes");
		menuGestion = new ActionListAgenceBancaire("3", "Menu gestion des comptes");

		menuGeneral.addAction(new ActionListeDesComptes("1", "Liste des comptes de l'agence"));
		menuGeneral.addAction(new ActionVoirCompteNumero("2", "Voir un compte (par son numéro)"));
		menuGeneral.addAction(menuOperations);
		menuGeneral.addAction(menuGestion);

		menuOperations.addAction(new ActionDeposer("1", "Déposer de l'argent sur un compte"));
		menuOperations.addAction(new ActionRetirer("2", "Retirer de l'argent sur un compte"));

		menuGestion.addAction(new ActionAjouterCompte("1", "Ajouter un compte"));
		menuGestion.addAction(new ActionSupprimerCompte("2", "Supprimer un compte"));

		AgenceBancaire ac;
		ac = AccesAgenceBancaire.getAgenceBancaire();


		try {
			menuGeneral.execute(ac);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
