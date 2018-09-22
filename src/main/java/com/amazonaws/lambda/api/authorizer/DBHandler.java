package com.amazonaws.lambda.api.authorizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBHandler {
  private static Connection connection = ConnectionFactory.getConnection();
  private static String campoID = System.getenv("campoIDBD");
  private static String campoSenha = System.getenv("campoPswdBD");
  private static String tabelaUserBD = System.getenv("tabelaUserBD");
  private String hash = " ";
  private String id;

  public String getId() {
    return id;
  }

  public String getHash() {
    return hash;
  }

  DBHandler(String id) {
    this.id = id;

    // Busca os dados no banco de dados
    String sqlQuery = String.format("SELECT %s FROM %s WHERE %s = %s", campoID + ", " + campoSenha,
        tabelaUserBD, campoID, id);
    try {
      PreparedStatement statement = connection.prepareStatement(sqlQuery);
      ResultSet results = statement.executeQuery();
      if (results.next()) {
        id = results.getString(campoID);
        hash = results.getString(campoSenha);
      }
    } catch (Exception e) {
      System.out.println("Erro ao accesar banco de dados para autenticar acesso ao API");
      e.printStackTrace();
    }
  }
}
