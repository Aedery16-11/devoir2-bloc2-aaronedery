package sio.devoir2sio1b.Model;

public class Action
{
    private int idAction;
    private String nomAction;
    private double montantAchat;
    private int quantite;

    public Action(int idAction, String nomAction, double montantAchat, int quantite) {
        this.idAction = idAction;
        this.nomAction = nomAction;
        this.montantAchat = montantAchat;
        this.quantite = quantite;
    }

    public int getIdAction() {
        return idAction;
    }

    public String getNomAction() {
        return nomAction;
    }

    public double getMontantAchat() {
        return montantAchat;
    }

    public int getQuantite() {
        return quantite;
    }
}
