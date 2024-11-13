enum TypeCase {
    NORMAL(null),
    OEIL_TEMPETE(null),
    HELICOPTERE_CRASH(null),
    PISTE_DECOLLAGE(null),
    OASIS(null),
    TUNNEL(null),
    MIRAGE(null),
    MOTEUR(Piece.MOTEUR),
    GOUVERNAIL(Piece.GOUVERNAIL),
    HELICE(Piece.HELICE),
    CAPTEUR(Piece.CAPTEUR),
    LIGNE_MOTEUR(Piece.MOTEUR),
    COLONNE_MOTEUR(Piece.MOTEUR),
    LIGNE_HELICE(Piece.HELICE),
    COLONNE_HELICE(Piece.HELICE),
    LIGNE_GOUVERNAIL(Piece.GOUVERNAIL),
    COLONNE_GOUVERNAIL(Piece.GOUVERNAIL),
    LIGNE_CAPTEUR(Piece.CAPTEUR),
    COLONNE_CAPTEUR(Piece.CAPTEUR);

    private final Piece piece;

    TypeCase(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        switch (this) {
            case NORMAL:
                return "Desert";
            case OEIL_TEMPETE:
                return "";
            case HELICOPTERE_CRASH:
                return "Crash";
            case PISTE_DECOLLAGE:
                return "Piste";
            case OASIS:
                return "Oasis";
            case MIRAGE:
                return "Mirage";
            case TUNNEL:
                return "Tunnel";
            case MOTEUR:
                return "Moteur";
            case HELICE:
                return "Helice";
            case GOUVERNAIL:
                return "Gouvernail";
            case CAPTEUR:
                return "Capteur";
            case LIGNE_MOTEUR:
                return "Ligne Moteur";
            case COLONNE_MOTEUR:
                return "Colonne Moteur";
            case LIGNE_HELICE:
                return "Ligne Helice";
            case COLONNE_HELICE:
                return "Colonne Helice";
            case LIGNE_GOUVERNAIL:
                return "Ligne Gouvernail";
            case COLONNE_GOUVERNAIL:
                return "Colonne Gouvernail";
            case LIGNE_CAPTEUR:
                return "Ligne Capteur";
            case COLONNE_CAPTEUR:
                return "Colonne Capteur";
            default:
                throw new IllegalArgumentException("Type de case non reconnu");
        }
    }

}