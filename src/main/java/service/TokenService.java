package service;


import exeptions.PersonExeption;
import io.jsonwebtoken.*;
import model.Person;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

public class TokenService {
    private static final String key = "fsd45f4h38fh34h_001";
    private static Integer daysExp = 90;

    public static Integer getDaysExp() {
        return daysExp;
    }

    public static void setDaysExp(Integer daysExp) {
        TokenService.daysExp = daysExp;
    }

    private static String generateSimple() {
        int len = 40;
        String charsCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
        String nums = "0123456789";
        String passSymbols = charsCaps + nums;
        Random rnd = new Random();
        //char[] password = new char[len];
        String key = "";

        for (int i = 0; i < len; i++) {
            key += passSymbols.charAt(rnd.nextInt(passSymbols.length()));
        }
        return key;

    }

    public String create(Person person) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(person.getId().toString())
                .setExpiration(Date.valueOf(LocalDate.now().plusDays(daysExp)))
                .claim("name", person.getName())
                .signWith(signatureAlgorithm, key.getBytes());
        return jwtBuilder.compact();
    }

    public Integer decode(String swt) throws PersonExeption {
        if (swt == null) {
            throw new PersonExeption("Fail. Token is null.");
        }
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key.getBytes())
                    .parseClaimsJws(Validation.isTokenOK(swt)).getBody();
            return Integer.parseInt(claims.getId());
        } catch (SignatureException e) {
            throw new PersonExeption("Your token is bull shit.");
        }
    }
}
