import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.*;

class Desert extends Observable {
    private Case[][] cases;
    private int taille;
    private List<Case> caseDesert = new ArrayList<>();
    private Case coordOeil;
    private int niveauTempete = 2;
    private Joueur[] joueurs;
    private int joueurActuel = 0;
    private Case pisteDecollage;

    public Desert(int taille) {
        this.taille = taille;
        this.joueurs = new Joueur[4];
        this.coordOeil = new Case(taille / 2, taille / 2, TypeCase.OEIL_TEMPETE);
        List<TypeCase> casesSpeciales = Arrays.asList(
                TypeCase.LIGNE_HELICE, TypeCase.COLONNE_HELICE,
                TypeCase.LIGNE_CAPTEUR, TypeCase.COLONNE_CAPTEUR,
                TypeCase.LIGNE_GOUVERNAIL, TypeCase.COLONNE_GOUVERNAIL,
                TypeCase.LIGNE_MOTEUR, TypeCase.COLONNE_MOTEUR,
                TypeCase.OASIS, TypeCase.OASIS, TypeCase.MIRAGE,
                TypeCase.HELICOPTERE_CRASH,
                TypeCase.TUNNEL, TypeCase.TUNNEL,
                TypeCase.PISTE_DECOLLAGE);

        // Pour calculer le nombre de cases normales pour compléter le plateau
        int numberOfNormalCases = (taille * taille) - casesSpeciales.size() - 4; // -4 pour les 4 pièces

        // On ajoute les cases normales à la liste
        List<TypeCase> extendedCasesSpeciales = new ArrayList<>(casesSpeciales);
        for (int i = 0; i < numberOfNormalCases; i++) {
            extendedCasesSpeciales.add(TypeCase.NORMAL);
        }

        // On crée une liste modifiable à partir de extendedCasesSpeciales
        List<TypeCase> modifiableCasesSpeciales = new ArrayList<>(extendedCasesSpeciales);
        Collections.shuffle(modifiableCasesSpeciales);

        this.cases = new Case[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (!modifiableCasesSpeciales.isEmpty()) {
                    TypeCase type = modifiableCasesSpeciales.remove(0);
                    cases[i][j] = new Case(i, j, type);
                    caseDesert.add(cases[i][j]);

                    if (type == TypeCase.PISTE_DECOLLAGE) {
                        this.pisteDecollage = cases[i][j];
                    }
                } else {
                    cases[i][j] = new Case(i, j, TypeCase.NORMAL);
                    caseDesert.add(cases[i][j]);
                }
            }
        }

        // Oeil de la tempête
        cases[taille / 2][taille / 2] = coordOeil;

        cases[0][2].ajouterSable(1);
        cases[1][1].ajouterSable(1);
        cases[1][3].ajouterSable(1);
        cases[2][0].ajouterSable(1);
        cases[2][4].ajouterSable(1);
        cases[3][1].ajouterSable(1);
        cases[3][3].ajouterSable(1);
        cases[4][2].ajouterSable(1);

        placerPieces();
    }

    public int getTaille() {
        return taille;
    }

    public int getTempete() {
        return (int) niveauTempete;
    }

    public Joueur[] getJoueur() {
        return joueurs;
    }

    public int getJoueurActuel() {
        return joueurActuel;
    }

    public void next() {
        if ((joueurs.length - 1) <= joueurActuel) {
            joueurActuel = 0;
        } else {
            joueurActuel += 1;
        }
    }

    public int getPisteDecollageX() {
        return pisteDecollage.getX();
    }

    public int getPisteDecollageY() {
        return pisteDecollage.getY();
    }

    public void addTabJoueur() {
        Joueur[] joueurs_temp = new Joueur[5]; // Creation des joueurs RAPPEL : Fonction a deplacer ? // A changer pour
                                               // laisser les utilisateur les creer
        Joueur j = new Joueur("Squelette", this);
        Joueur ja = new Joueur("Archeologue", this);
        Joueur jb = new Joueur("Pilote", this);
        Joueur jc = new Joueur("Mecanicien", this);
        Joueur jd = new Joueur("Medecin", this);
        int posX;
        int posY;
        do {
            posX = (int) (Math.random() * 5);
            posY = (int) (Math.random() * 5);
        } while (posX == 2 && posY == 2);
        j.setX(posX);
        j.setY(posY);
        do {
            posX = (int) (Math.random() * 5);
            posY = (int) (Math.random() * 5);
        } while (posX == 2 && posY == 2);
        ja.setX(posX);
        ja.setY(posY);
        do {
            posX = (int) (Math.random() * 5);
            posY = (int) (Math.random() * 5);
        } while (posX == 2 && posY == 2);
        jb.setX(posX);
        jb.setY(posY);
        do {
            posX = (int) (Math.random() * 5);
            posY = (int) (Math.random() * 5);
        } while (posX == 2 && posY == 2);
        jc.setX(posX);
        jc.setY(posY);
        do {
            posX = (int) (Math.random() * 5);
            posY = (int) (Math.random() * 5);
        } while (posX == 2 && posY == 2);
        jd.setX(posX);
        jd.setY(posY);
        ArrayList<Piece> p = new ArrayList<>(4);
        p.add(Piece.MOTEUR);
        p.add(Piece.CAPTEUR);
        p.add(Piece.GOUVERNAIL);
        p.add(Piece.HELICE);
        j.setPiece(p); // POUR VERIFIER VICTOIRE
        j.setColor(Color.RED);
        ja.setColor(new Color(181, 151, 53));
        jb.setColor(Color.BLACK);
        jc.setColor(Color.GREEN);
        jd.setColor(new Color(226, 53, 191));
        joueurs_temp[0] = j;
        joueurs_temp[1] = ja;
        joueurs_temp[2] = jb;
        joueurs_temp[3] = jc;
        joueurs_temp[4] = jd;
        joueurs = joueurs_temp;
    }

    public Case getCase(int i, int j) {
        return cases[i][j];
    }

    public void show() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                System.out.print("#");
            }
            System.out.println();
        }
    }

    public void actionDesert(Joueur[] joueurs) {
        System.out.println();
        int nombreActions = (int) niveauTempete;
        for (int i = 0; i < nombreActions; i++) {
            // Choisir une action au hasard parmi les 3
            int action = (int) (Math.random() * 3);

            switch (action) {
                case 0:
                    System.out.println("action:VentSouffle");
                    ventSouffle();
                    break;
                case 1:
                    System.out.println("action:CHALEUR");
                    vagueDeChaleur(joueurs);
                    break;
                case 2:
                    System.out.println("action:Dechainement");
                    tempeteSeDechaine();
                    break;
            }
        } // On vérifie le sable total du desert pour arrêter la partie à 43 tonnes
        this.notifyObservers();
    }

    private void ventSouffle() {
        // Choisir une direction aléatoire (0: haut, 1: droite, 2: bas, 3: gauche)
        int direction = (int) (Math.random() * 4);

        // Choisir une force aléatoire entre 1 et 3
        int force = (int) (Math.random() * 3) + 1;
        System.out.println("direction :" + direction + " / force :" + force);
        // Calculer les déplacements en x et y en fonction de la direction
        int dx = 0;
        int dy = 0;
        switch (direction) {
            case 0:
                dy = -1;
                break;
            case 1:
                dx = 1;
                break;
            case 2:
                dy = 1;
                break;
            case 3:
                dx = -1;
                break;
        }

        // Trouver l'œil de la tempête
        int oeilX = -1;
        int oeilY = -1;
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                if (cases[x][y].getType() == TypeCase.OEIL_TEMPETE) {
                    oeilX = x;
                    oeilY = y;
                    break;
                }
            }
        }

        // Boucle pour déplacer les tuiles
        for (int i = 0; i < force; i++) {
            int newX = oeilX - dx;
            int newY = oeilY - dy;

            // Vérifier si la nouvelle position est dans les limites du désert
            if (newX >= 0 && newX < taille && newY >= 0 && newY < taille) {

                // Déplacer la tuile et ajouter une tonne de sable
                cases[newX][newY].ajouterSable(1);

                // Échanger l'oeil de la tempête et la case adjacente
                Case temp = cases[newX][newY];
                cases[newX][newY] = cases[oeilX][oeilY];
                cases[oeilX][oeilY] = temp;

                // Mettre à jour la position de l'oeil de la tempête
                oeilX = newX;
                oeilY = newY;
            }
        }
    }

    private void vagueDeChaleur(Joueur[] joueurs) {
        for (Joueur joueur : joueurs) {
            if (!(cases[joueur.getX()][joueur.getY()].getType() == TypeCase.TUNNEL)) {
                joueur.boireEau(1);
            }
        }
        notifyObservers();
    }

    private void tempeteSeDechaine() {
        niveauTempete += 0.5;
    }

    public boolean partieGagnee() {
        int piecesRamassees = 0;
        boolean tousLesJoueursSurPiste = true;
        for (Joueur joueur : getJoueur()) {
            piecesRamassees += joueur.getNombrePieces();
            if (!(joueur.getX() == getPisteDecollageX() && joueur.getY() == getPisteDecollageY())) {
                tousLesJoueursSurPiste = false;
            }
        }

        if (piecesRamassees == 4 && tousLesJoueursSurPiste && pisteDecollage.getSable() == 0) {
            return true;
        }
        return false;
    }

    public void placerPieces() {
        List<Piece> pieces = Arrays.asList(Piece.MOTEUR, Piece.GOUVERNAIL, Piece.HELICE, Piece.CAPTEUR);
        Collections.shuffle(pieces);

        List<TypeCase> indices = Arrays.asList(
                TypeCase.LIGNE_MOTEUR, TypeCase.COLONNE_MOTEUR,
                TypeCase.LIGNE_GOUVERNAIL, TypeCase.COLONNE_GOUVERNAIL,
                TypeCase.LIGNE_HELICE, TypeCase.COLONNE_HELICE,
                TypeCase.LIGNE_CAPTEUR, TypeCase.COLONNE_CAPTEUR);
        Collections.shuffle(indices);

        int nombreDePiecesPlacees = 0;
        int nombreDIndicesPlaces = 0;

        while (nombreDePiecesPlacees < pieces.size() && nombreDIndicesPlaces < 2 * pieces.size()) {
            int randomX = (int) (Math.random() * taille);
            int randomY = (int) (Math.random() * taille);

            Case randomCase = this.getCase(randomX, randomY);
            TypeCase caseType = randomCase.getType();

            if (caseType == TypeCase.NORMAL) {
                if (randomCase.getPiece() == null && nombreDePiecesPlacees < pieces.size()) {
                    randomCase.setPiece(pieces.get(nombreDePiecesPlacees));
                    nombreDePiecesPlacees++;
                } else if (randomCase.getPiece() == null && nombreDIndicesPlaces < indices.size()) {
                    randomCase.setType(indices.get(nombreDIndicesPlaces));
                    nombreDIndicesPlaces++;
                }
            }
        }
    }

    public boolean checkIndicesRevealed(Piece piece) {
        int indicesCount = 0;
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                TypeCase type = getCase(i, j).getType();
                if (type != null && type.getPiece() == piece && getCase(i, j).estExploree()) {
                    indicesCount++;
                }
            }
        }
        return indicesCount >= 2;
    }

    public void revealPiece(Piece piece) {
        boolean found = false;
        for (int i = 0; i < taille && !found; i++) {
            for (int j = 0; j < taille && !found; j++) {
                Case currentCase = getCase(i, j);
                if (currentCase.getPiece() == piece) {
                    currentCase.setRevealed(true);
                    found = true;
                }
            }
        }
    }

}