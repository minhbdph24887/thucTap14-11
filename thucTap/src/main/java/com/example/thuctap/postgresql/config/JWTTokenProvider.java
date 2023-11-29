package com.example.thuctap.postgresql.config;

import com.example.thuctap.postgresql.entity.Account;
import com.example.thuctap.postgresql.entity.UserDetailCustom;
import com.example.thuctap.postgresql.repository.AccountRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@Transactional
public class JWTTokenProvider {
    // khởi tạo chuỗi bí mật chỉ có một phía biết hoặc Keys.secretKeyFor(SignatureAlgorithm.HS512)
    @Value("${jwt.secret}")
    private static String JWT_SECRET;

    @Autowired
    AccountRepository accountRepository;

    // tạo ra jwt từ thông tin Account
    public String generateToken(UserDetailCustom account) {
        Date date = new Date();
        // thời gian hiệu lực của chuỗi jwt (7 ngày)
        long JWT_EXPIRATION = 604800000L;
        Date expiryDate = new Date(date.getTime() + JWT_EXPIRATION); //là thời điểm mà token JWT sẽ hết hạn
        Account account1 = accountRepository.findAccountByUserName(account.getUsername()).get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("idAccount", account1.getIdAccount());
        claims.put("codeAccount", account1.getCodeAccount());
        claims.put("nameAccount", account1.getNameAccount());
        claims.put("role", account1.getRole());
        return Jwts.builder()                           // tạo chuỗi json web token từ idAccount của Account
                .setClaims(claims)                      // đặt các yêu cầu cho token
                .setIssuedAt(date)                      // đặt thời gian phát hành cho token
                .setExpiration(expiryDate)              // đặt thời gian hết hạn cho token
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)                    // kí token bằng thuật toán HS256 và một khóa bí mật
                .compact();                             // tạo token từ các thông tin đã đặt và trả về dưới dạng chuỗi
    }

    // lấy thông tin của Account từ jwt
    public String getAccountIdFromJWT(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJwt(token).getBody();
        return String.valueOf(claims.get("idAccount"));
    }

    // validateToken
    public boolean validateToken(String authToken) {
        try {
            SecretKey secretKey1 = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
            Jwts.parserBuilder().setSigningKey(secretKey1).build().parseClaimsJwt(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT Token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT Token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT Token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
        }
        return false;
    }

}
