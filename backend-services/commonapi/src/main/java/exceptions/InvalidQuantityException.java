package exceptions;

public class InvalidQuantityException extends InvalidInputsException {

    private int quantity;

    public InvalidQuantityException(String s, int quantite) {
        super(s);
        this.quantity = quantite;
    }

    public int getQuantity() {
        return quantity;
    }
}
