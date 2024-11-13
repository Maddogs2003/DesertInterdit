class Case {
    private int x;
    private int y;
    private boolean exploree;
    private int sable;
    private boolean bloquee;
    private TypeCase type;
    private Piece piece;
    private boolean revealed;

    public Case(int x, int y, TypeCase type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.exploree = false;
        this.sable = 0;
        this.bloquee = false;
        this.revealed = false;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TypeCase getType() {
        return type;
    }

    public void setType(TypeCase type) {
        this.type = type;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean estExploree() {
        return exploree;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
        this.exploree = true;
    }

    public void explorer() {
        if (!exploree && !estBloquee()) {
            exploree = true;
            // rappel : mettre des actions supplémentaires ici (révéler des informations sur
            // la case ou mettre à jour l'interface utilisateur)
        }
    }

    public int getSable() {
        return this.sable;
    }

    public void ajouterSable(int sable) {
        this.sable += sable;
        if (this.sable >= 2) {
            this.bloquee = true;
        }
    }

    public void enleverSable(int sable) {
        this.sable -= sable;
        if (this.sable < 0) {
            this.sable = 0;
        }
        if (this.sable < 2) {
            this.bloquee = false;
        }
    }

    public boolean estBloquee() {
        return bloquee;
    }

    public void setBloquee(boolean b) {
        bloquee = b;
    }

    @Override
    public String toString() {
        return "Case{" +
                "exploree=" + exploree +
                ", sable=" + sable +
                ", bloquee=" + bloquee +
                '}';
    }
}