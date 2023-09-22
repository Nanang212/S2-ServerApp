package id.co.mii.serverapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// Untuk meidentifikasikan class person sebagai entity (bisa berelasi dengan entity lain)
@Entity
// Mendefinisikan class Person sebagai tabel di db dengan nama persons
@Table(name = "persons")
public class Person {
    // Memberitahu spring bahwa prperti id adalah sebuah primary key
    @Id
    // Memberitahu spring bahwa properti id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;

    public Person() {
    }

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
