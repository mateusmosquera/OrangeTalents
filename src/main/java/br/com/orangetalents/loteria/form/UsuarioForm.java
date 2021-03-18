package br.com.orangetalents.loteria.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioForm {

    @NotNull @NotEmpty @Email
    private String email;

}
