package com.solid.login.Controller;

import com.solid.login.interfaces.IConfigRest;
import com.solid.login.service.cifrado.CifradoManager;
import com.solid.login.service.logger.LoggerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigControllerImpl implements IConfigRest {

    @Autowired
    public CifradoManager cifradoManager;

    @Autowired
    public LoggerManager loggerManager;

    @Override
    public void setConfigTipoCifrar(String tipo) {
        cifradoManager.setTipoCifrado( tipo );
    }

    @Override
    public void setConfigTipoLogin(String tipo) {
        loggerManager.setTipoLogger( tipo );
    }
}
