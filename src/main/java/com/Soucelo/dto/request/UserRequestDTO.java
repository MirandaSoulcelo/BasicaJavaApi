package com.Soucelo.dto.request;

public class UserRequestDTO
{
    private String name;
    private String email;
    private String cpf;
    private String password;
    private Boolean isAdmin = false;
    private Boolean isExcluded = false;

    public UserRequestDTO() {}

    public UserRequestDTO(String name,
                          String email,
                          String cpf,
                          String password,
                          Boolean isExcluded,
                          Boolean isAdmin)
    {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        this.isExcluded = isExcluded;
        this.isAdmin = isAdmin;
    }

    public String getName()
    {
        return name;
    }
    public String getEmail() {return email;}
    public String getCpf()
    {
        return cpf;
    }
    public String getPassword()
    {
        return password;
    }
    public Boolean isExcluded()
    {
        return isExcluded;
    }
    public Boolean isAdmin()
    {
        return isAdmin;
    }
}
