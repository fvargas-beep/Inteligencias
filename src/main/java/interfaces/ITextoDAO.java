package interfaces;

import java.util.ArrayList;
import objects.Texto;

public interface ITextoDAO {
  public void insertarTexto(Texto pTexto);
  public ArrayList<Texto> getTextos(int pTematica_id);
  public ArrayList<Texto> getTextos();
  public Texto getTexto(int texto_id);
}
