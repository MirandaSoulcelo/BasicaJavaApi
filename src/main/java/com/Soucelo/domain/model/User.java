package com.Soucelo.domain.model;

public class User
{
    private Long id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private Boolean isExcluded;
    private Boolean isAdmin;

    public User(Long id,
                String name,
                String email,
                String password,
                String cpf,
                Boolean isExcluded,
                Boolean isAdmin)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.isExcluded = isExcluded;
        this.isAdmin = isAdmin;
    }



    public User() {}

    public User(String nome,
                String email,
                String password,
                String cpf,
                boolean isExcluded,
                boolean isAdmin)
    {
        this.name = nome;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.isExcluded = isExcluded;
        this.isAdmin = isAdmin;
    }

    public Long getId(){ return id; }
    public String getName(){ return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getCpf() { return cpf; }
    public Boolean isExcluded() { return isExcluded; }
    public Boolean isAdmin() { return isAdmin; }
    public void setPassword(String password) { this.password = password; }
}
