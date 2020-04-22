package com.bridgelabz.bookstore.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.bridgelabz.bookstore.exception.BookException;

public class UserToken {


    public static final String TOKEN_SECRET = "s4T2zOIWHMM1sxq";

    public static String createToken(Long id){
        try {
            System.out.println(id);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            String token = JWT.create()
                    .withClaim("userId", id)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
            //log Token Signing Failed
        }
        return null;
    }

    public static Long tokenVerify(String token)
    {
        Long userid;
       // try {
            Verification verification=JWT.require(Algorithm.HMAC256(UserToken.TOKEN_SECRET));
            JWTVerifier jwtverifier=verification.build();
            DecodedJWT decodedjwt=jwtverifier.verify(token);
            Claim claim=decodedjwt.getClaim("userId");
            userid=claim.asLong();

            return userid;
      //  }
      //  catch(Exception exception)
//        {
//            throw new BookException(100,"Token Not Verified");
//        }
    }

}
