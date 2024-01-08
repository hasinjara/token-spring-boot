package com.demo.token.models;

public class Retour {

    String erreur;

    String message;
    Object data;

    public Retour(String erreur, String message, Object data) {
        this.erreur = erreur;
        this.message = message;
        this.data = data;
    }

    public Retour(Object data)  {
        try {
            this.data = data;
            this.erreur = "aucun";
            this.message = "Reussi";
        }catch (Exception e) {
            this.data = null;
            this.erreur = e.getMessage();
            this.message = "Echoue";
        }

    }

    public Retour(Object data, String message)  {
        try {
            this.data = data;
            this.erreur = "aucun";
            this.message = message;
        }catch (Exception e) {
            this.data = null;
            this.erreur = e.getMessage();
            this.message = "Echoue";
        }

    }

    public Retour(String erreur, Object data) {
        this.erreur = erreur;
        this.data = data;
    }

    public Retour() {
    }

    public String getErreur() {
        return erreur;
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
