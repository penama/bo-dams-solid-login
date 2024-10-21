package com.solid.login.service.cifrado;

import com.solid.login.interfaces.IEncriptarPass;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class CifrarCipherImpl implements IEncriptarPass {

    @Override
    public String cifrar(String cadena) throws Exception {
        final byte[] bytes = cadena.getBytes(StandardCharsets.UTF_8);
        final Cipher aes = obtieneCipher(true);
        final byte[] cifrado = aes.doFinal(bytes);
        return new String(cifrado, StandardCharsets.UTF_8);
    }

    @Override
    public String descifrar(String cadena) throws Exception {
        final Cipher aes = obtieneCipher(false);
        final byte[] cifrado = cadena.getBytes(StandardCharsets.UTF_8);
        final byte[] bytes = aes.doFinal(cifrado);
        final String sinCifrar = new String(bytes, "UTF-8");
        return sinCifrar;
    }

    private Cipher obtieneCipher(boolean paraCifrar) throws Exception {
//        final String frase = "FraseLargaConDiferentesLetrasNumerosYCaracteresEspeciales_áÁéÉíÍóÓúÚüÜñÑ1234567890!#%$&()=%_NO_USAR_ESTA_FRASE!_";
        final String frase = "DiplomadoArquitecturaMicroServicios2024";
        final MessageDigest digest = MessageDigest.getInstance("SHA");
        digest.update(frase.getBytes("UTF-8"));
        final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
        final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        if (paraCifrar) {
            aes.init(Cipher.ENCRYPT_MODE, key);
        } else {
            aes.init(Cipher.DECRYPT_MODE, key);
        }
        return aes;
    }


}
