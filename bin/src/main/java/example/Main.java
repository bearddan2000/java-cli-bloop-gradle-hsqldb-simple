package example;

import org.apache.log4j.PropertyConfigurator;

public class Main {

  public static void main(String[] args) {
    PropertyConfigurator.configure("/app/src/main/resources/log4j.xml");
    String databaseName = "test";

    // Create a variable for the connection string.
    String connectionStr = "jdbc:hsqldb:mem:" + databaseName;
    example.dto.Dog dog = new example.dto.Dog("org.hsqldb.jdbcDriver", connectionStr);
    dog.operation("00", example.dto.SQLOPT.CREATE);
    dog.operation("01", example.dto.SQLOPT.INSERT);

    example.db.print.output.IOutput output = new example.db.print.output.ToConsole();
    for ( String tbl : new String[]{"dog"})
      example.db.DBQuery.query(connectionStr, tbl, output);
  }
}
