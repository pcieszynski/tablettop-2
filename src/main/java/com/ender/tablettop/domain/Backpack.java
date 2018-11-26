package com.ender.tablettop.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Backpack.
 */
@Entity
@Table(name = "backpack")
public class Backpack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToOne    @JoinColumn(unique = true)
    private Character character;

    @ManyToMany
    @JoinTable(name = "backpack_legs",
               joinColumns = @JoinColumn(name = "backpacks_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "legs_id", referencedColumnName = "id"))
    private Set<Legs> legs = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "backpack_boots",
               joinColumns = @JoinColumn(name = "backpacks_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "boots_id", referencedColumnName = "id"))
    private Set<Boots> boots = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "backpack_gloves",
               joinColumns = @JoinColumn(name = "backpacks_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "gloves_id", referencedColumnName = "id"))
    private Set<Gloves> gloves = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "backpack_righthand",
               joinColumns = @JoinColumn(name = "backpacks_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "righthands_id", referencedColumnName = "id"))
    private Set<RightHand> righthands = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "backpack_lefthand",
               joinColumns = @JoinColumn(name = "backpacks_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "lefthands_id", referencedColumnName = "id"))
    private Set<LeftHand> lefthands = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "backpack_armour",
               joinColumns = @JoinColumn(name = "backpacks_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "armours_id", referencedColumnName = "id"))
    private Set<Armour> armours = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "backpack_helmet",
               joinColumns = @JoinColumn(name = "backpacks_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "helmets_id", referencedColumnName = "id"))
    private Set<Helmet> helmets = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "backpack_item",
               joinColumns = @JoinColumn(name = "backpacks_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "items_id", referencedColumnName = "id"))
    private Set<Item> items = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getCharacter() {
        return character;
    }

    public Backpack character(Character character) {
        this.character = character;
        return this;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Set<Legs> getLegs() {
        return legs;
    }

    public Backpack legs(Set<Legs> legs) {
        this.legs = legs;
        return this;
    }

    public Backpack addLegs(Legs legs) {
        this.legs.add(legs);
        legs.getBackpacks().add(this);
        return this;
    }

    public Backpack removeLegs(Legs legs) {
        this.legs.remove(legs);
        legs.getBackpacks().remove(this);
        return this;
    }

    public void setLegs(Set<Legs> legs) {
        this.legs = legs;
    }

    public Set<Boots> getBoots() {
        return boots;
    }

    public Backpack boots(Set<Boots> boots) {
        this.boots = boots;
        return this;
    }

    public Backpack addBoots(Boots boots) {
        this.boots.add(boots);
        boots.getBackpacks().add(this);
        return this;
    }

    public Backpack removeBoots(Boots boots) {
        this.boots.remove(boots);
        boots.getBackpacks().remove(this);
        return this;
    }

    public void setBoots(Set<Boots> boots) {
        this.boots = boots;
    }

    public Set<Gloves> getGloves() {
        return gloves;
    }

    public Backpack gloves(Set<Gloves> gloves) {
        this.gloves = gloves;
        return this;
    }

    public Backpack addGloves(Gloves gloves) {
        this.gloves.add(gloves);
        gloves.getBackpacks().add(this);
        return this;
    }

    public Backpack removeGloves(Gloves gloves) {
        this.gloves.remove(gloves);
        gloves.getBackpacks().remove(this);
        return this;
    }

    public void setGloves(Set<Gloves> gloves) {
        this.gloves = gloves;
    }

    public Set<RightHand> getRighthands() {
        return righthands;
    }

    public Backpack righthands(Set<RightHand> rightHands) {
        this.righthands = rightHands;
        return this;
    }

    public Backpack addRighthand(RightHand rightHand) {
        this.righthands.add(rightHand);
        rightHand.getBackpacks().add(this);
        return this;
    }

    public Backpack removeRighthand(RightHand rightHand) {
        this.righthands.remove(rightHand);
        rightHand.getBackpacks().remove(this);
        return this;
    }

    public void setRighthands(Set<RightHand> rightHands) {
        this.righthands = rightHands;
    }

    public Set<LeftHand> getLefthands() {
        return lefthands;
    }

    public Backpack lefthands(Set<LeftHand> leftHands) {
        this.lefthands = leftHands;
        return this;
    }

    public Backpack addLefthand(LeftHand leftHand) {
        this.lefthands.add(leftHand);
        leftHand.getBackpacks().add(this);
        return this;
    }

    public Backpack removeLefthand(LeftHand leftHand) {
        this.lefthands.remove(leftHand);
        leftHand.getBackpacks().remove(this);
        return this;
    }

    public void setLefthands(Set<LeftHand> leftHands) {
        this.lefthands = leftHands;
    }

    public Set<Armour> getArmours() {
        return armours;
    }

    public Backpack armours(Set<Armour> armours) {
        this.armours = armours;
        return this;
    }

    public Backpack addArmour(Armour armour) {
        this.armours.add(armour);
        armour.getBackpacks().add(this);
        return this;
    }

    public Backpack removeArmour(Armour armour) {
        this.armours.remove(armour);
        armour.getBackpacks().remove(this);
        return this;
    }

    public void setArmours(Set<Armour> armours) {
        this.armours = armours;
    }

    public Set<Helmet> getHelmets() {
        return helmets;
    }

    public Backpack helmets(Set<Helmet> helmets) {
        this.helmets = helmets;
        return this;
    }

    public Backpack addHelmet(Helmet helmet) {
        this.helmets.add(helmet);
        helmet.getBackpacks().add(this);
        return this;
    }

    public Backpack removeHelmet(Helmet helmet) {
        this.helmets.remove(helmet);
        helmet.getBackpacks().remove(this);
        return this;
    }

    public void setHelmets(Set<Helmet> helmets) {
        this.helmets = helmets;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Backpack items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public Backpack addItem(Item item) {
        this.items.add(item);
        item.getBackpacks().add(this);
        return this;
    }

    public Backpack removeItem(Item item) {
        this.items.remove(item);
        item.getBackpacks().remove(this);
        return this;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Backpack backpack = (Backpack) o;
        if (backpack.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), backpack.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Backpack{" +
            "id=" + getId() +
            "}";
    }
}
