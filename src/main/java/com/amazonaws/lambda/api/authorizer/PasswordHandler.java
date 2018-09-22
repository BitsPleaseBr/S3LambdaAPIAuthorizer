package com.amazonaws.lambda.api.authorizer;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Classe para armazenamento de password
 * 
 * @author Diogo
 */
public class PasswordHandler {
  /**
   * Usado para fazer hash da senha do usuário no cliente de acesso ao sistema. Utiliza o algoritmo
   * BCrypt para fazer o hash da senha.
   * 
   * @param password senha do usuário
   * @param email e-mail do usuário, usado para fazer o salt.
   * @return Hash da senha com o parâmetro de encriptação e o hash prefixados
   */
  public static String clientPswdHash(String password, String email) {
    final String prefixo = "$2a$10$"; // String prefixado no salt com parâmetros
                                      // de hashing para o algoritmo
    String salt = email + email.length() + email; // O salt recebe um sufixo
                                                  // para garantir um tamanho
                                                  // mínimo
                                                  // necessário

    // É necessário remover qualquer caractere que não estejam em Base64.
    salt = removerCaracteresInvalidos(salt);
    salt = prefixo + salt;
    String hash = BCrypt.hashpw(password, salt);
    return hash;
  }

  public static boolean compararHashClient(String senha, byte[] hash) {
    String hashString = new String(hash, StandardCharsets.UTF_8);
    return BCrypt.checkpw(senha, hashString);
  }

  private static String removerCaracteresInvalidos(String salt) {
    salt = salt.replaceAll("[^A-Za-z0-9/.]", "a");
    return salt;
  }

  public static boolean isHash(String hash) {
    Pattern pattern = Pattern.compile("^\\$2a\\$10\\$.*");
    Matcher matcher = pattern.matcher(hash);
    return matcher.matches();
  }

  // Construtor privado evita o instanciamento da classe
  private PasswordHandler() {

  }
}
