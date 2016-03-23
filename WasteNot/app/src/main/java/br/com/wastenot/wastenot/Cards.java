package br.com.wastenot.wastenot;

import java.io.Serializable;

/**
 * Created by ssl on 3/21/16.
 */
public class Cards implements Serializable {

    private int id;
    private String layout;
    private String name;
    private String manaCost;
    private String cmc;
    private String colors;
    private String type;
    private String supertype;
    private String types;
    private String subtypes;
    private String rarity;
    private String text;
    private String flavor;
    private String artist;
    private String number;
    private String power;
    private String toughness;
    private String imageName;
    private String multiverseid;
    private String card_id;


    public Cards( String layout, String name, String manaCost, String cmc, String colors, String type, String supertype, String types, String subtypes, String rarity, String text, String flavor, String artist, String number, String power, String toughness, String imageName, String multiverseid, String card_id) {

        this.layout = layout;
        this.name = name;
        this.manaCost = manaCost;
        this.cmc = cmc;
        this.colors = colors;
        this.type = type;
        this.supertype = supertype;
        this.types = types;
        this.subtypes = subtypes;
        this.rarity = rarity;
        this.text = text;
        this.flavor = flavor;
        this.artist = artist;
        this.number = number;
        this.power = power;
        this.toughness = toughness;
        this.imageName = imageName;
        this.multiverseid = multiverseid;
        this.card_id = card_id;
    }

    public Cards() {

    }

    public Cards(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public String getCmc() {
        return cmc;
    }

    public void setCmc(String cmc) {
        this.cmc = cmc;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSupertype() {
        return supertype;
    }

    public void setSupertype(String supertype) {
        this.supertype = supertype;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(String subtypes) {
        this.subtypes = subtypes;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getMultiverseid() {
        return multiverseid;
    }

    public void setMultiverseid(String multiverseid) {
        this.multiverseid = multiverseid;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }
}
