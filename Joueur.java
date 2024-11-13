import java.util.ArrayList;
import java.util.List;
import java.awt.*;

class Joueur {
    private String nom;
    private int niveau_eau;
    private List<Piece> pieces;
    private int x;
    private int y;
    private Desert desert;
    private int actionsRestantes;
    private Color color;

    public Joueur(String nom, Desert desert) {
        this.nom = nom;
        this.niveau_eau = 5;
        this.desert = desert;
        this.actionsRestantes = 4;
        this.color = null;
        this.pieces = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public int getNiveauEau() {
        return niveau_eau;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        color = c;
    }

    public ArrayList<Piece> getPieces() {
        return (ArrayList<Piece>) pieces;
    }

    public void setPiece(ArrayList<Piece> p) { // POUR DEV
        pieces = (List<Piece>) p;
    }

    public String getStringPieces() {
        String res = "";
        for (Piece p : pieces) {
            res = res + p.toString() + "/";
        }
        return res;
    }

    public void ramasserPiece() {
        Case caseActuelle = desert.getCase(x, y);
        Piece piece = caseActuelle.getPiece();
        if (piece != null && caseActuelle.isRevealed() && actionsRestantes > 0) {
            this.pieces.add(piece);
            caseActuelle.setPiece(null);
            actionsRestantes--;
            desert.notifyObservers();
        } else if (actionsRestantes <= 0) {
            System.out.println("Vous n'avez plus d'action.");
            desert.notifyObservers();
        } else {
            // Afficher un message indiquant que la pièce n'est pas encore révélée
            desert.notifyObservers(); // Notifier les observateurs même si la pièce n'est pas révélée
        }
    }

    public boolean possedePiece(Piece piece) {
        return pieces.contains(piece);
    }

    public void boireEau(int quantite) {
        niveau_eau -= quantite;
        if (niveau_eau < 0) {
            niveau_eau = 0;
        }
    }

    public void remplirGourde(int quantite) {
        niveau_eau += quantite;
        if (niveau_eau > 5) {
            niveau_eau = 5;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getActions() {
        return actionsRestantes;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void deplacer(int newX, int newY) {
        if ((Math.abs(newX - x) == 1 && newY == y) || (Math.abs(newY - y) == 1 && newX == x)) {
            if (newX >= 0 && newX < 5 && newY >= 0 && newY < 5) {
                Case nouvelleCase = desert.getCase(newX, newY);
                if (!nouvelleCase.estBloquee() && nouvelleCase.getType() != TypeCase.OEIL_TEMPETE) {
                    this.x = newX;
                    this.y = newY;
                    actionsRestantes--;
                    desert.notifyObservers();
                }
            }
        }
    }

    public void creuser(int x, int y) { // On peut creuser dans l'oeil de la tempete, c'est inutile mais on peut
        if ((Math.abs(x - this.x) <= 1) && (Math.abs(y - this.y) <= 1)) {
            if (x >= 0 && x < 5 && y >= 0 && y < 5) {
                Case caseACreuser = desert.getCase(x, y);
                if (caseACreuser.getSable() > 0) {
                    caseACreuser.enleverSable(1);
                    actionsRestantes--;
                    desert.notifyObservers();
                }
            }
        }
    }

    public void resetActions() {
        actionsRestantes = 4;
    }

    public void explorer() {
        int x = this.getX();
        int y = this.getY();
        Case currentCase = desert.getCase(x, y);

        if (!currentCase.estExploree()) {
            if (actionsRestantes >= 1) {
                currentCase.explorer();
                actionsRestantes -= 1;
            }

            if (currentCase.getType() == TypeCase.OASIS) {
                for (Joueur j : desert.getJoueur()) {
                    if (j.getX() == x && j.getY() == y) {
                        j.niveau_eau += 2;
                        if (j.niveau_eau > 5) {
                            j.niveau_eau = 5;
                        }
                    }
                }
            }

            TypeCase type = currentCase.getType();
            if (type != null && type.getPiece() != null) {
                Piece piece = type.getPiece();
                if (desert.checkIndicesRevealed(piece)) {
                    System.out.println("Les deux indices de la piece " + piece
                            + " ont ete trouves, la piece est maintenant revelee.");
                    desert.revealPiece(piece);
                }
            }
            desert.notifyObservers();
        }
    }

    public void donnerEau(Joueur autreJoueur, int quantiteEau) {
        if (niveau_eau > 1 && autreJoueur.getNiveauEau() < 5) {
            if (this.x == autreJoueur.x && this.y == autreJoueur.y) {
                int quantiteEauDisponible = Math.min(quantiteEau,
                        Math.min(this.niveau_eau, 5 - autreJoueur.niveau_eau));
                this.niveau_eau -= quantiteEauDisponible;
                autreJoueur.niveau_eau += quantiteEauDisponible;
            }
            desert.notifyObservers();
            actionsRestantes -= 1;
        }
    }

    public void prendreEau(Joueur autreJoueur, int quantiteEau) {
        if (this.x == autreJoueur.x && this.y == autreJoueur.y) {
            int quantiteEauRecue = Math.min(quantiteEau,
                    Math.min(autreJoueur.niveau_eau, this.niveau_eau - this.niveau_eau));
            this.niveau_eau += quantiteEauRecue;
            autreJoueur.niveau_eau -= quantiteEauRecue;
        }
    }

    public void passageTunnel() {
        boolean crossed = false; // Si on ne vérifie pas, le passage dans le tunnel peut avoir lieu deux fois si
                                 // les coordonnées des case tunnels le permettent, on utilise donc cette
                                 // variable
                                 // pour s'assurer que le joueur traverse le tunnel qu'une unique fois
        for (int i = 0; i < desert.getTaille(); i++) {
            for (int j = 0; j < desert.getTaille(); j++) {
                if (desert.getCase(i, j).getType() == TypeCase.TUNNEL && (i != x || j != y) && (!crossed)) {
                    setX(i);
                    setY(j);
                    System.out.println("Passage tunnel");
                    crossed = true;
                }
            }
        }
        desert.notifyObservers();
    }

    public int getNombrePieces() {
        return this.pieces.size();
    }

}