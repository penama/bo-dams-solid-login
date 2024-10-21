package com.solid.login.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping( "/api/v1/config" )
public interface IConfigRest {

    @PutMapping("/cifrar/{tipo}")
    public void setConfigTipoCifrar( @PathVariable String tipo);

    @PutMapping( "/logger/{tipo}" )
    public void setConfigTipoLogin( @PathVariable String tipo );
}
