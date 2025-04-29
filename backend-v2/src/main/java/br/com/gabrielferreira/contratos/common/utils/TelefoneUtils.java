package br.com.gabrielferreira.contratos.common.utils;

import org.apache.commons.lang3.StringUtils;

public class TelefoneUtils {

    private TelefoneUtils() {}

    public static String toMascaraTelefone(String ddd, String numero) {
        if (StringUtils.isNotBlank(ddd) && StringUtils.isNotBlank(numero)) {
            String numeroCompleto = ddd.concat(numero);
            if (numero.length() == 8) {
                return numeroCompleto.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
            } else if (numero.length() == 9) {
                return numeroCompleto.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
            }
        }
        return null;
    }
}
