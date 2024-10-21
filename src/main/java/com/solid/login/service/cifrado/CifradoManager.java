package com.solid.login.service.cifrado;

import com.solid.login.interfaces.IEncriptarPass;
import com.solid.login.service.logger.LoggerManager;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CifradoManager implements IEncriptarPass {

    private IEncriptarPass encriptarPass;

    @Autowired
    public LoggerManager log;

    @Autowired
    private CifrarBase64Impl cifrarBase64;

    @Autowired
    private CifrarHashCodeImpl cifrarHashCode;

    @Autowired
    private CifrarCipherImpl cifrarCipher;

    @PostConstruct
    public void initCifrado(){
        setTipoCifrado( "base64" );
    }

    public void setTipoCifrado( String tipo ){
        log.info( this.getClass(), "Cambiando el tipo de cifrado antes: " + ( encriptarPass == null ? "null" : encriptarPass.getClass()) );
        if ( tipo.equals("base64") )
            encriptarPass = cifrarBase64;
        else if ( tipo.equals( "hashcode" ) )
            encriptarPass = cifrarHashCode;
        else if ( tipo.equals( "cipher" ) )
            encriptarPass = cifrarCipher;
        else
            log.warn( this.getClass(), "no aplico cambio..." );
        log.info( this.getClass(), "Cambiando el tipo de cifrado despues: " + encriptarPass.getClass() );
    }

    @Override
    public String cifrar(String cadena) throws Exception {
        return encriptarPass.cifrar( cadena );
    }

    @Override
    public String descifrar(String cadena) throws Exception {
        return encriptarPass.descifrar( cadena );
    }
}
