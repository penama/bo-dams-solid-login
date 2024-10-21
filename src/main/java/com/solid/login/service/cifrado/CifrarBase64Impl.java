package com.solid.login.service.cifrado;

import com.solid.login.interfaces.IEncriptarPass;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class CifrarBase64Impl implements IEncriptarPass {
    @Override
    public String cifrar(String cadena) throws Exception {
        return Base64.getEncoder().encodeToString(cadena.getBytes());
    }

    @Override
    public String descifrar(String cadena) throws Exception{
        return new String(Base64.getDecoder().decode(cadena));
    }
}
