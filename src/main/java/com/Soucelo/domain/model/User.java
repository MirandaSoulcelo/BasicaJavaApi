package com.Soucelo.domain.model;

public class User
{
    private Long id;
    private String name;
    private String email;

    public User(Long id,
                String name,
                String email)
    {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public User() {}

    public User(String nome,
                String email)
    {
        this.name= nome;
        this.email = email;
    }

    public Long getId(){ return id; }
    public String getName(){ return name; }
    public String getEmail() { return email; }
}
