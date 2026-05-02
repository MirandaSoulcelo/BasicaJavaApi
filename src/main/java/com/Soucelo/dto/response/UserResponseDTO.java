package com.Soucelo.dto.response;

public class UserResponseDTO
{
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private Boolean isAdmin;

    public UserResponseDTO(Long id,
                           String name,
                           String email,
                           String cpf,
                           Boolean isAdmin)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.isAdmin = isAdmin;
    }

    public Long getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getEmail()
    {
        return email;
    }
    public String getCpf()
    {
        return cpf;
    }
    public Boolean isAdmin()
    {
        return isAdmin;
    }
}
