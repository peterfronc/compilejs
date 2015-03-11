package com.qubitproducts.compilejs.fs;

import static com.qubitproducts.compilejs.CompileJS.LOGGER;
import com.qubitproducts.compilejs.MainProcessor;
import static com.qubitproducts.compilejs.MainProcessor.LOG_LEVEL;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Peter Fronc <peter.fronc@qubitdigital.com>
 */
public class CFile
        implements FSFile {
  
  static public final char separatorChar = File.separatorChar;
  static public final String separator = File.separator;
  
  public static boolean isLog() {
    return LOG_LEVEL != MainProcessor.LogLevel.NONE;
  }
  
  public static void log(String msg) {
    switch (LOG_LEVEL) {
      case ALL:
        System.out.println(msg);
        LOGGER.fine(msg);
        break;
      case NONE:
        break;
      case FINE:
        LOGGER.fine(msg);
        break;
      default:
        System.out.println(msg);
    }
  }
  
  File plainFile = null;

  @Override
  public File getFile() {
    return plainFile;
  }

  private static Map<String, String> cache = new HashMap<String, String>();
  
  @Override
  public void clear() {
    cache.clear();
  }
  
  @Override
  public String getAsString() {
    try {
    String cached = cache.get(plainFile.getCanonicalPath());
    
    if (cached == null) {
      LineReader reader = new LineReader(plainFile);
      StringBuilder builder = new StringBuilder();
      String line = null;
      while((line = reader.readLine()) != null) {
        builder.append(line);
        builder.append('\n');
      }
    }
    
    } catch (IOException ioe) {
      if (isLog()) {
        log("Could not read file: " + plainFile.getName());
      }
    }
    return null;
  }

  public CFile(String pathname) {
    plainFile = new File(pathname);
  }

  public CFile(File file) {
    plainFile = file;
  }

  public CFile(FSFile file) {
    plainFile = new File(file.getPath());
  }

  public CFile(String parent, String child) {
    plainFile = new File(parent, child);
  }

  public CFile(File parent, String child) {
    plainFile = new File(parent, child);
  }

  public CFile(FSFile parent, String child) {
    plainFile = new File(parent.getPath(), child);
  }

  public CFile(URI uri) {
    plainFile = new File(uri);
  }

  @Override
  public String getName() {
    return plainFile.getName();
  }

  @Override
  public String getParent() {
    return plainFile.getParent();
  }

  @Override
  public CFile getParentFile() {
    return new CFile(plainFile.getParentFile());
  }

  @Override
  public String getPath() {
    return plainFile.getPath();
  }

  @Override
  public boolean isAbsolute() {
    return plainFile.isAbsolute();
  }

  @Override
  public String getAbsolutePath() {
    return plainFile.getAbsolutePath();
  }

  @Override
  public CFile getAbsoluteFile() {
    return new CFile(plainFile.getAbsoluteFile());
  }

  @Override
  public String getCanonicalPath() throws IOException {
    return plainFile.getCanonicalPath();
  }

  @Override
  public CFile getCanonicalFile() throws IOException {
    return new CFile(plainFile.getCanonicalFile());
  }

  @Override
  public boolean canRead() {
    return plainFile.canRead();
  }

  @Override
  public boolean canWrite() {
    return plainFile.canWrite();
  }

  @Override
  public boolean exists() {
    return plainFile.exists();
  }

  @Override
  public boolean isDirectory() {
    return plainFile.isDirectory();
  }

  @Override
  public boolean isFile() {
    return plainFile.isFile();
  }

  @Override
  public boolean isHidden() {
    return plainFile.isHidden();
  }

  @Override
  public long lastModified() {
    return plainFile.lastModified();
  }

  @Override
  public long length() {
    return plainFile.length();
  }

  @Override
  public boolean createNewFile() throws IOException {
    return plainFile.createNewFile();
  }

  @Override
  public boolean delete() {
    return plainFile.delete();
  }

  @Override
  public void deleteOnExit() {
    plainFile.deleteOnExit();
  }

  @Override
  public String[] list() {
    return plainFile.list();
  }

  @Override
  public String[] list(FilenameFilter filter) {
    return plainFile.list(filter);
  }

  @Override
  public FSFile[] listFiles() {
    File[] files = plainFile.listFiles();
    FSFile[] array = new CFile[files.length];
    for (int i = 0; i < files.length; i++) {
      array[i] = new CFile(files[i]);
    }
    return array;
  }

  @Override
  public FSFile[] listFiles(FilenameFilter filter) {
    File[] files = plainFile.listFiles(filter);
    FSFile[] array = new CFile[files.length];
    for (int i = 0; i < files.length; i++) {
      array[i] = new CFile(files[i]);
    }
    return array;
  }

  @Override
  public FSFile[] listFiles(FileFilter filter) {
    File[] files = plainFile.listFiles(filter);
    FSFile[] array = new CFile[files.length];
    for (int i = 0; i < files.length; i++) {
      array[i] = new CFile(files[i]);
    }
    return array;
  }

  @Override
  public boolean mkdir() {
    return plainFile.mkdir();
  }

  @Override
  public boolean mkdirs() {
    return plainFile.mkdirs();
  }

  @Override
  public boolean renameTo(FSFile dest) {
    return plainFile.renameTo((File) dest);
  }

  @Override
  public boolean setLastModified(long time) {
    return plainFile.setLastModified(time);
  }

  @Override
  public boolean setReadOnly() {
    return plainFile.setReadOnly();
  }

  @Override
  public boolean setWritable(boolean writable, boolean ownerOnly) {
    return plainFile.setWritable(writable, ownerOnly);
  }

  @Override
  public boolean setWritable(boolean writable) {
    return plainFile.setWritable(writable);
  }

  @Override
  public boolean setReadable(boolean readable, boolean ownerOnly) {
    return plainFile.setReadable(readable, ownerOnly);
  }

  @Override
  public boolean setReadable(boolean readable) {
    return plainFile.setReadable(readable);
  }

  @Override
  public boolean setExecutable(boolean executable, boolean ownerOnly) {
    return plainFile.setExecutable(executable, ownerOnly);
  }

  @Override
  public boolean setExecutable(boolean executable) {
    return plainFile.setExecutable(executable);
  }

  @Override
  public boolean canExecute() {
    return plainFile.canExecute();
  }

  public static File[] listRoots() {
    return File.listRoots();
  }

  @Override
  public long getTotalSpace() {
    return plainFile.getTotalSpace();
  }

  @Override
  public long getFreeSpace() {
    return plainFile.getFreeSpace();
  }

  @Override
  public long getUsableSpace() {
    return plainFile.getUsableSpace();
  }

  @Override
  public int compareTo(FSFile pathname) {
    return plainFile.compareTo((File) pathname);
  }

  public static CFile createTempFile(
          String prefix,
          String suffix,
          File directory)
          throws IOException {
    return new CFile(File.createTempFile(prefix, suffix, directory));
  }

  public static CFile createTempFile(String prefix, String suffix)
          throws IOException {
    return new CFile(File.createTempFile(prefix, suffix));
  }

  public FSFile getChild(FSFile location) {
    return this.getChild(location.getPath());
  }

  public FSFile getChild(String path) {
    return new CFile(this, path);
  }

  public LineReader getLineReader() throws FileNotFoundException {
    LineReader lr = new LineReader(plainFile);
    return lr;
  }

  public BufferedWriter getBufferedWriter() throws IOException {
    BufferedWriter writer = new BufferedWriter(
        new FileWriter(plainFile));
    return writer;
  }

  public BufferedWriter getBufferedWriter(boolean b) throws IOException {
    BufferedWriter writer = new BufferedWriter(
        new FileWriter(plainFile, b));
    return writer;
  }

  public BufferedReader getBufferedReader()
      throws FileNotFoundException {
    BufferedReader writer = new BufferedReader(
        new FileReader(plainFile));
    return writer;
  }

  public List<String> getLines() throws IOException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public List<String> saveLines() throws IOException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public List<String> saveLines(List<String> lines) throws IOException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
