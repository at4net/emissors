/*
 * Copyright 2016 gdeignacio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.caib.scsp.utils.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author gdeignacio
 */

/*

public class FileSystem {
    
    public static File filesPath = null;

    public static File createFile(String path, String filename) {
        
        

        File file = null;

        path = (path == null) ? System.getProperty("user.home") : path;
        
        
        

        return file;
    }

}
*/

public class FileSystem {

  //protected static final Logger log = Logger.getLogger(FileSystem.class);
  
  
  public static File filesPath = null;
  
  public static File getFilesPath() {
    if (filesPath == null) {
      String path = System.getProperty("FileSystemManager.Path");
      if (path == null) {
        path = System.getProperty("user.home");
        File f = new File(path, ".FileSystemManager");
        if (!f.mkdirs()) {
          filesPath = new File(System.getProperty("java.io.tmpdir"), ".FileSystemManager");
          filesPath.mkdirs();
        } else {
          filesPath = f;
        }
      } else {
        filesPath = new File(path);
      }
    }    
    return filesPath;
  }
  

  public static void setFilesPath(File newPath) {
    if (newPath != null) {
      filesPath = newPath;
    }
  }
  

  public static File getFile(Long id) {
    File newFile = new File(getFilesPath(), String.valueOf(id));
    return newFile;
  }

  
  public static String getChecksum(Long id) throws Exception {
    return Utils.getChecksum(new FileInputStream(new File(getFilesPath(), String.valueOf(id))));
  }

  public static File crearFitxer(InputStream is, Long dstId) throws I18NException {
    File newFile = new File(getFilesPath(), String.valueOf(dstId));
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(newFile, false); 
      copy(is, fos );
      fos.flush();
    } catch(Exception e) {
      throw new I18NException(e, "error.createfile",
          new I18NArgumentString(String.valueOf(dstId)));
    } finally {
      try { fos.close(); } catch (Throwable e) { }
    }
    return newFile;
  }
  
  public static File crearFitxer(File input, Long dstId) throws I18NException {
    File newFile = new File(getFilesPath(), String.valueOf(dstId));
    copy(input, newFile);
    return newFile;
  }



  /**
   * Mou el fitxer 'src' a un fitxer amb ID 'dstID'
   * 
   * @param src
   *          Fitxer origen
   * @param dstId
   *          ID del fitxer destí
   * @return Nou fitxer creat
   * @throws Exception
   */
  public static File sobreescriureFitxer(File src, Long dstId) {
    File newFile = new File(getFilesPath(), String.valueOf(dstId));
    if (newFile.exists()) {
      if (!newFile.delete()) {
        log.warn("sobreescriureFitxer:: El fitxer desti existeix i no s'ha pogut borrar: " 
            + newFile.getAbsolutePath());
      }
    }
    // TODO Substituir per FileUtils.moveFile()
    if (!src.renameTo(newFile)) {
      log.warn("sobreescriureFitxer:: No s'ha renombrat el fitxer de [" + src.getAbsolutePath()
          + "] a [" + newFile.getAbsolutePath() + "]");
      printMiniStackTrace();
    }
    return newFile;
  }



  public static void copy(File input, File output) throws I18NException {
    try {
      FileInputStream fis = new FileInputStream(input);
      FileOutputStream fos = new FileOutputStream(output); 
      copy(fis, fos );
      fis.close();
      fos.flush();
      fos.close();
    } catch(Exception e) {
      throw new I18NException(e, "error.copyfile", 
         new I18NArgumentString(String.valueOf(input == null? "null" : input.getAbsolutePath())),
         new I18NArgumentString(String.valueOf(output == null? "null" : output.getAbsolutePath())));
    }
  }

  public static void copy(InputStream input, OutputStream output)
      throws IOException {
    byte[] buffer = new byte[4096];
    int n = 0;
    while (-1 != (n = input.read(buffer))) {
      output.write(buffer, 0, n);
    }
  }

  /**
   * 
   * @param id
   * @return true si l'arxiu no existeix o s'ha borrat. false en els altres
   *         casos.
   */
  public static boolean eliminarArxiu(Long id) {
    File file = new File(getFilesPath(), String.valueOf(id));
    if (file.exists()) {
      if (!file.delete()) {
        file.deleteOnExit();
        log.warn("Per algun motiu desconegut no s'ha pogut borrar l'arxiu "
            + file.getAbsolutePath());
        printMiniStackTrace();
        return false;
      }
    } else {
      log.warn("El fitxer " + file.getAbsolutePath() + " no existeix");
    }
    return true;
  }


  private static void printMiniStackTrace() {
    int count = 8;
    for (StackTraceElement stack : new Exception().getStackTrace()) {
      log.warn(stack.toString());
      if (0 == count--) {
        break;
      }
    }
  }
  
  
  /**
   * 
   * @param fitxers
   * @return true si tots els arxius s'han borrat.
   *          false si algun fitxer no s'ha pogut borrar
   */
  public static boolean eliminarArxius(Set<Long> fitxers) {
    boolean resultat = true;
    if (fitxers != null && !fitxers.isEmpty()) {
      for (Long fitxerID : fitxers) {
        resultat = resultat && eliminarArxiu(fitxerID);
      }
    }
    return resultat;
  }
  

  
  public static File getTmpFile(Long dstId) {
    return new File(FileSystemManager.getFilesPath(), String.valueOf(dstId) + ".tmp");
  }
  
  public static byte[] getFileContent(long fitxerID) throws FileNotFoundException, IOException {
    byte[] bytes;
    File file = getFile(fitxerID);
    FileInputStream fin = null;
    FileChannel ch = null;
    
    try {
        fin = new FileInputStream(file);
        ch = fin.getChannel();
        int size = (int) ch.size();
        MappedByteBuffer buf = ch.map(MapMode.READ_ONLY, 0, size);
        bytes = new byte[size];
        buf.get(bytes);

    } finally {
        try {
            if (fin != null) {
                fin.close();
            }
            if (ch != null) {
                ch.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return bytes;
  }


}


