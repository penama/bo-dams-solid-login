package com.solid.login.service.cifrado;

import com.solid.login.interfaces.IEncriptarPass;
import org.springframework.stereotype.Service;

@Service
public class CifrarHashCodeImpl implements IEncriptarPass {

    @Override
    public String cifrar(String cadena) throws Exception {
        return "" + cadena.hashCode();
    }

    @Override
    public String descifrar(String cadena) throws Exception {
        return "";
    }

}
