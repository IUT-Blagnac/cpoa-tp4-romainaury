package application;

import java.util.Locale;
import java.util.Scanner;

import banque.AgenceBancaire;
import banque.Compte;
import banque.exception.ABCompteDejaExistantException;
import banque.exception.ABCompteInexistantException;
import banque.exception.ABCompteNullException;
import banque.exception.CompteException;

public class ApplicationAgenceBancaire {

	static String[] menuGeneral = { "Liste des comptes de l'agence", "Voir un compte (par son numéro)",
			"Menu opérations sur comptes", "Menu gestion des comptes" };
	static String[] menuOperations = { "Déposer de l'argent sur un compte", "Retirer de l'argent sur un compte", };
	static String[] menuGestion = { "Ajouter un compte", "Supprimer un compte" };

	/**
	 * Affichage du menu de l'application
	 * 
	 * @param ag AgenceBancaire pour r�cup�rer le nom et la localisation
	 */
	public static void afficherMenu(AgenceBancaire ag, int typeMenu) {
		switch (typeMenu) {
			case 0:
				System.out.println(
						"--\n  Agence " + ag.getNomAgence() + " de " + ag.getLocAgence() + "\n  Menu Général\n--\n");
				for (int i = 0; i < menuGeneral.length; i++) {
					System.out.println("\t" + (i + 1) + " - " + menuGeneral[i]);
				}
				System.out.println("\n0 - Pour quitter ce menu");
				System.out.print("Votre choix ?\n");

				break;
			case 1:
				System.out.println("--\n  Agence " + ag.getNomAgence() + " de " + ag.getLocAgence()
						+ "\n  Menu opérations sur comptes\n--\n");
				for (int i = 0; i < menuOperations.length; i++) {
					System.out.println("\t" + (i + 1) + " - " + menuOperations[i]);
				}
				System.out.println("\n0 - Pour quitter ce menu");
				System.out.print("Votre choix ?\n");
				break;
			case 2:
				System.out.println("--\n  Agence " + ag.getNomAgence() + " de " + ag.getLocAgence()
						+ "\n  Menu gestion des comptes\n--\n");
				for (int i = 0; i < menuGestion.length; i++) {
					System.out.println("\t" + (i + 1) + " - " + menuGestion[i]);
				}
				System.out.println("\n0 - Pour quitter ce menu");
				System.out.print("Votre choix ?\n");
				break;
		}
	}

	public static void menuGestion(AgenceBancaire monAg) {
		String choix;
		boolean continuer;
		Scanner lect;

		String numero;

		lect = new Scanner(System.in);
		lect.useLocale(Locale.US);

		continuer = true;
		while (continuer) {
			ApplicationAgenceBancaire.afficherMenu(monAg, 2);
			choix = lect.next();
			choix = choix.toLowerCase();
			switch (choix) {
				case "0":
					System.out.println("Fin de Menu de gestion des comptes\n");

					continuer = false;
					break;

				case "1":
					String proprietaire;

					System.out.print("Num compte -> ");
					numero = lect.next();

					System.out.print("Nom propriétaire -> ");
					proprietaire = lect.next();
					try {
						monAg.addCompte(new Compte(numero, proprietaire));
					} catch (ABCompteNullException e) {
						e.printStackTrace();
					} catch (ABCompteDejaExistantException e) {
						e.printStackTrace();
					}
					break;

				case "2":
					System.out.print("Num compte -> ");
					numero = lect.next();
					try {
						monAg.removeCompte(numero);
					} catch (ABCompteInexistantException e) {
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("Erreur de saisie ...");
					break;
			}
		}
	}

	public static void menuOperations(AgenceBancaire monAg) {
		String choix;
		boolean continuer;
		Scanner lect;

		String numero;
		double montant;

		lect = new Scanner(System.in);
		lect.useLocale(Locale.US);

		continuer = true;
		while (continuer) {
			ApplicationAgenceBancaire.afficherMenu(monAg, 1);
			choix = lect.next();
			choix = choix.toLowerCase();
			switch (choix) {
				case "0":
					continuer = false;
					System.out.println("Fin de Menu des opérations sur comptes\n");
					break;
				case "1":
					System.out.print("Num compte -> ");
					numero = lect.next();
					System.out.print("Montant à déposer -> ");
					montant = lect.nextDouble();
					ApplicationAgenceBancaire.deposerSurUnCompte(monAg, numero, montant);
					break;
				case "2":
					System.out.print("Num compte -> ");
					numero = lect.next();
					System.out.print("Montant à retirer -> ");
					montant = lect.nextDouble();
					ApplicationAgenceBancaire.retirerSurUnCompte(monAg, numero, montant);
					break;

				default:
					System.out.println("Erreur de saisie ...");
					break;
			}
		}
	}

	public static void menuGeneral(AgenceBancaire monAg) {
		String choix;
		boolean continuer;
		Scanner lect;

		lect = new Scanner(System.in);
		lect.useLocale(Locale.US);
		
		continuer = true;
		while (continuer) {
			ApplicationAgenceBancaire.afficherMenu(monAg, 0);

			choix = lect.next();
			choix = choix.toLowerCase();
			switch (choix) {
				case "0":
					System.out.println("Fin de Menu Général\n");
					;
					continuer = false;
					break;
				case "1":
					monAg.afficher();
					break;
				case "2":
					System.out.print("Num compte -> ");
					String numero = lect.next();
					Compte c = monAg.getCompte(numero);
					if (c == null) {
						System.out.println("Compte inexistant ...");
					} else {
						c.afficher();
					}
					break;
				case "3":
					ApplicationAgenceBancaire.menuOperations(monAg);
					break;
				case "4":
					ApplicationAgenceBancaire.menuGestion(monAg);
					break;
				default:
					System.out.println("Erreur de saisie ...");
					ApplicationAgenceBancaire.tempo();
					break;
			}
		}
	}

	/**
	 * Temporisation : Affiche un message et attend la frappe de n'importe quel
	 * caract�re.
	 */
	public static void tempo() {
		Scanner lect;

		lect = new Scanner(System.in);

		System.out.print("Tapper un car + return pour continuer ... ");
		lect.next(); // Inutile � stocker mais ...
	}

	public static void main(String argv[]) {
		AgenceBancaire monAg;
		monAg = AccesAgenceBancaire.getAgenceBancaire();

		ApplicationAgenceBancaire.menuGeneral(monAg);

	}

	public static void comptesDUnPropretaire(AgenceBancaire ag, String nomProprietaire) {
		Compte[] t;

		t = ag.getComptesDe(nomProprietaire);
		if (t.length == 0) {
			System.out.println("pas de compte � ce nom ...");
		} else {
			System.out.println("  " + t.length + " comptes pour " + nomProprietaire);
			for (int i = 0; i < t.length; i++)
				t[i].afficher();
		}
	}

	public static void deposerSurUnCompte(AgenceBancaire ag, String numeroCompte, double montant) {
		Compte c;

		c = ag.getCompte(numeroCompte);
		if (c == null) {
			System.out.println("Compte inexistant ...");
		} else {
			System.out.println("Solde avant d�p�t: " + c.soldeCompte());
			try {
				c.deposer(montant);
				System.out.println("Montant d�pos�, solde : " + c.soldeCompte());
			} catch (CompteException e) {
				System.out.println("Erreur de d�pot, solde inchang� : " + c.soldeCompte());
				System.out.println(e.getMessage());
			}
		}
	}

	public static void retirerSurUnCompte(AgenceBancaire ag, String numeroCompte, double montant) {
		Compte c;

		c = ag.getCompte(numeroCompte);
		if (c == null) {
			System.out.println("Compte inexistant ...");
		} else {
			System.out.println("Solde avant retrait : " + c.soldeCompte());
			try {
				c.retirer(montant);
				System.out.println("Montant retir�, solde : " + c.soldeCompte());
			} catch (CompteException e) {
				System.out.println("Erreur de d�pot, solde inchang� : " + c.soldeCompte());
				System.out.println(e.getMessage());
			}
		}

	}
}
