package exceptions;

public class InvalidPrixException extends InvalidInputsException {
    private Double prix;
    public InvalidPrixException(String s, double prix) {
        super(s);
        this.prix = prix;
    }

    public Double getPrix() {
        return prix;
    }
}
