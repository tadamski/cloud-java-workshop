package org.tadamski.examples.cloud;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "foo")
@NamedQueries({
        @NamedQuery(name="Foo.findByName",
                query="SELECT f FROM Foo f WHERE f.name = :name"),
})
public class Foo {

    @Id
    @JsonIgnore
    private int id;

    @Column(length = 30)
    private String name;

    @Column(length = 30)
    private int blipCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlipCount() {
        return blipCount;
    }

    public void setBlipCount(int blipCount) {
        this.blipCount = blipCount;
    }
}
