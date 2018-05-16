package com.crossover.techtrial.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "title")
public class Title implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -481073315751589931L;

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
