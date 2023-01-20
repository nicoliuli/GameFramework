package com.game.core.dto;

public class Equip {
    private Integer id;

    private Integer position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Equip{" +
                "id=" + id +
                ", position=" + position +
                '}';
    }
}
