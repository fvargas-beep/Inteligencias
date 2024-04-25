package objectsDAO;

import java.sql.Connection;
import utilities.ConexionDB;


public class DAO {
  protected final Connection con = ConexionDB.getCon();
}
