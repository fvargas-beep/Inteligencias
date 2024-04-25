package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.Base64;
import objects.Tematica;
import objects.Texto;
import objects.Usuario;
import logicacreacional.UsuarioSingleton;
import interfaces.IUsuarioDAO;
import objectsDAO.TematicaDAO;
import objectsDAO.TextoDAO;
import objectsDAO.UsuarioDAO;
import org.opencv.videoio.VideoCapture;
import utilities.AnalisisSentimientos;
import utilities.ChatGPT;
import utilities.Email;
import utilities.ExtraerPalabrasClave;
import utilities.PDF;
import utilities.WordCloudGenerator;

@WebServlet(name = "home", urlPatterns = {"/home"})
@MultipartConfig(maxFileSize = 16177215) 
public class home extends HttpServlet {
  Usuario usuario = UsuarioSingleton.getInstancia();
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String tipo = request.getParameter("boton");
    switch(tipo){
      case "Registrar temática":
        agregarTematica(request, response);
        break;
      case "Registrar texto":
        agregarTexto(request, response);
        break;
      case "Consultar textos":
        buscarTextos(request, response);
        break;
      case "Análisis de sentimientos":
        analisisSentimientos(request, response);
        break;
      case "Generar wordcloud":
        wordCloud(request, response);
        break;
      case "Extraer palabras clave":
        keyWords(request, response);
        break;
      case "Consultar a ChatGPT":
        chatGPT(request, response);
        break;
      case "Enviar PDF":
        pdf(request, response);
        break;
      case "Generar archivo de audio":
        text_to_speech(request, response);
        break;
      case "cerrar_sesion":
        cerrarSesion(request, response);
        break;
      case "ver_usuarios":
        ver_usuarios(request, response);
        break;
      case "ver_tematicas":
        ver_tematicas(request, response);
        break;
      case "salir":
        salir(request, response);
        break;
      case "abc":
        x(request, response);
        break;
    }
  }
  
  private void x(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    VideoCapture x = new VideoCapture();
  }
  
  private void salir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    HttpSession session = request.getSession();
    TematicaDAO tematicaDAO = new TematicaDAO();
    session.setAttribute("tematicas", tematicaDAO.obtenerTematicas(usuario.getCedula()));
    response.sendRedirect("views/home.jsp");
  }
  
  private void ver_tematicas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    HttpSession session = request.getSession();
    TematicaDAO tematicaDAO = new TematicaDAO();
    session.setAttribute("tematicas", tematicaDAO.obtenerTematicasCompleto(usuario.getCedula()));
    response.sendRedirect("views/tematicas.jsp");
  }
  
  private void ver_usuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    HttpSession session = request.getSession();
    IUsuarioDAO usuarioDAO = new UsuarioDAO();
    session.setAttribute("usuarios", usuarioDAO.getUsuarios());   
    response.sendRedirect("views/usuarios.jsp");
  }
  
  private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    response.sendRedirect("./index.html");
  }
  
  private void text_to_speech(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    HttpSession session = request.getSession();
    int id = Integer.parseInt(request.getParameter("texto_selecionado"));
    TextoDAO textoDAO = new TextoDAO();
    utilities.TextToSpeech.text_to_speech(textoDAO.getTexto(id).getTexto());
    TematicaDAO tematicaDAO = new TematicaDAO();
    session.setAttribute("tematicas", tematicaDAO.obtenerTematicasCompleto(usuario.getCedula()));
    response.setContentType("audio/mpeg");
    response.sendRedirect("views/home.jsp");
  }
  
  private void pdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    HttpSession session = request.getSession();
    int id = Integer.parseInt(request.getParameter("texto_selecionado"));
    int cedula = usuario.getCedula();
    IUsuarioDAO usuarioDAO = new UsuarioDAO();
    String email = usuarioDAO.getEmail(cedula);
    Email.enviarEmail(email, PDF.crearPDF(id));
    PrintWriter out = response.getWriter(); 
    out.println("<script type=\"text/javascript\">"); 
    out.println("alert('EL reporte ha sido enviado a su correo electrónico.');"); 
    out.println("location='views/home.jsp';"); 
    out.println("</script>");
  }
  
  private void chatGPT(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    HttpSession session = request.getSession();
    int id = Integer.parseInt(request.getParameter("texto_selecionado"));
    TextoDAO textoDAO = new TextoDAO();
    IUsuarioDAO usuarioDAO = new UsuarioDAO();
    session.setAttribute("texto", textoDAO.getTexto(id));
    session.setAttribute("respuesta", ChatGPT.chatGPT(textoDAO.getTexto(id).getTexto()));
    session.setAttribute("usuario", usuarioDAO.getUsuario(textoDAO.getTexto(id).getUsuario_id()));
    response.sendRedirect("views/chatGPT.jsp");
  }
  
  private void keyWords(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    HttpSession session = request.getSession();
    int id = Integer.parseInt(request.getParameter("texto_selecionado"));
    TextoDAO textoDAO = new TextoDAO();
    IUsuarioDAO usuarioDAO = new UsuarioDAO();
    session.setAttribute("texto", textoDAO.getTexto(id));
    session.setAttribute("palabras", ExtraerPalabrasClave.extraer(textoDAO.getTexto(id).getTexto()));
    session.setAttribute("usuario", usuarioDAO.getUsuario(textoDAO.getTexto(id).getUsuario_id()));
    response.sendRedirect("views/keyWords.jsp");
  }
  
  private void wordCloud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    IUsuarioDAO usuarioDAO = new UsuarioDAO();
    HttpSession session = request.getSession();
    int id = Integer.parseInt(request.getParameter("texto_selecionado"));
    TextoDAO textoDAO = new TextoDAO();
    session.setAttribute("texto", textoDAO.getTexto(id));
    byte[] imagen = WordCloudGenerator.generar(textoDAO.getTexto(id).getTexto());
    response.setContentType("image/jpeg");
    String encodedByteArray = Base64.getEncoder().encodeToString(imagen);
    session.setAttribute("usuario", usuarioDAO.getUsuario(textoDAO.getTexto(id).getUsuario_id()));
    session.setAttribute("encodedByteArray", encodedByteArray);
    response.sendRedirect("views/wordCloud.jsp");
  }
  
  private void analisisSentimientos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    HttpSession session = request.getSession();
    TextoDAO textoDAO = new TextoDAO();
    IUsuarioDAO usuarioDAO = new UsuarioDAO();
    int id = Integer.parseInt(request.getParameter("texto_selecionado"));
    session.setAttribute("texto", textoDAO.getTexto(id));
    session.setAttribute("analisis", AnalisisSentimientos.analizarSentimiento(textoDAO.getTexto(id).getTexto()));
    session.setAttribute("usuario", usuarioDAO.getUsuario(textoDAO.getTexto(id).getUsuario_id()));
    response.sendRedirect("views/analisisSentimientos.jsp");
  }
  
  private void agregarTematica(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    HttpSession session = request.getSession();
    String nombre = request.getParameter("nombre");
    String descripcion = request.getParameter("descripcion");
    InputStream blob = null;
    Part filePart = request.getPart("imagen");
    if (filePart != null) {
      blob = filePart.getInputStream();
    }
    int cedula = usuario.getCedula();
    Tematica tematica = new Tematica(nombre, descripcion, cedula, blob);
    TematicaDAO tematica1 = new TematicaDAO();
    tematica1.insertarTematica(tematica);
    TematicaDAO tematicaDAO = new TematicaDAO();
    session.setAttribute("tematicas", tematicaDAO.obtenerTematicas(usuario.getCedula()));
    response.sendRedirect("views/home.jsp");
  }
  
  private void agregarTexto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    HttpSession session = request.getSession();
    String textoIngresado = request.getParameter("texto");
    String tematica_id = request.getParameter("tematica");
    int cedula = usuario.getCedula();
    Texto texto = new Texto(cedula, Integer.parseInt(tematica_id), textoIngresado);
    TextoDAO textoDAO = new TextoDAO();
    textoDAO.insertarTexto(texto);
    int tematica_id2 = Integer.parseInt(request.getParameter("tematica"));
    
    session.setAttribute("textos", textoDAO.getTextos(tematica_id2));
    TematicaDAO tematicaDAO = new TematicaDAO();
    session.setAttribute("tematicas", tematicaDAO.obtenerTematicas(cedula));
    response.sendRedirect("views/home.jsp");
  }
  
  private void buscarTextos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    HttpSession session = request.getSession();
    int tematica_id = Integer.parseInt(request.getParameter("tematica2"));
    TextoDAO textoDAO = new TextoDAO();
    session.setAttribute("textos", textoDAO.getTextos(tematica_id));
    TematicaDAO tematicaDAO = new TematicaDAO();
    session.setAttribute("tematicas", tematicaDAO.obtenerTematicas(usuario.getCedula()));
    
    response.sendRedirect("views/home.jsp");
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
