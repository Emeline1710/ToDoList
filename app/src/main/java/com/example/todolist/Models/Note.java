package com.example.todolist.Models;

// Cette classe représente la table note de la base de données
public class Note {
    private int id;
    private String title;
    private String text;
    private String date;

    // Constructeur par défaut
    public Note(){}

    // Constructeur avec titre et texte de la note
    public Note(String title, String text){
        this.title = title;
        this.text = text;
    }

    // Getters et setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Méthode toString pour voir tous les attributs de l'objet Note
    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
