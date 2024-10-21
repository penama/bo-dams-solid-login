package com.solid.login.interfaces;

public interface IEncriptarPass {

    public String cifrar( String cadena ) throws Exception;
    public String descifrar( String cadena )throws Exception;
}
