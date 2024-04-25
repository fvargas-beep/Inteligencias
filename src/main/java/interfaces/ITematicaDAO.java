package interfaces;

import java.util.ArrayList;
import objects.Tematica;

public interface ITematicaDAO {
  public void insertarTematica(Tematica pTematica);
  public ArrayList<Tematica> obtenerTematicas(int pUsuario_id);
  public ArrayList<Tematica> obtenerTematicasCompleto(int pUsuario_id);
}