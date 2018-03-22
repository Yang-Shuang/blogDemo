package com.yhs.blog.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 * Created by YangShuang
 * on 2018/3/22.
 */
public class FileUtil {

    public FileUtil() {
    }

    public static File getFile(String filePath) {
        File file = new File(filePath);
        return getFile(file);
    }

    public static File getFile(File file) {
        if(file != null && !file.exists()) {
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            try {
                file.createNewFile();
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

        return file;
    }

    public static void deleteFile(File file, boolean deleteDirectory) {
        if(file != null && file.exists()) {
            if(file.isDirectory()) {
                File[] var2 = file.listFiles();
                int var3 = var2.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    File item = var2[var4];
                    deleteFile(item, deleteDirectory);
                }

                if(deleteDirectory) {
                    file.delete();
                }
            } else {
                file.delete();
            }

        }
    }

    public static void deleteFile(String filepath) {
        deleteFile(new File(filepath), true);
    }

    public static boolean writeFile(String filePath, InputStream is, boolean isappend) {
        FileOutputStream fos = null;

        try {
            File saveFile = getFile(filePath);
            fos = new FileOutputStream(saveFile, isappend);
            return IOUtil.inputStreamToOutputStream(is, fos);
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public static boolean writeFile(String filePath, String str, boolean isappend) {
        return writeFile(filePath, str.getBytes(), isappend);
    }

    public static boolean writeFile(String filePath, byte[] bytes, boolean isappend) {
        FileOutputStream fos = null;

        try {
            File saveFile = getFile(filePath);
            fos = new FileOutputStream(saveFile, isappend);
            fos.write(bytes);
            fos.flush();
            boolean var5 = true;
            return var5;
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                if(fos != null) {
                    fos.close();
                }
            } catch (Exception var14) {
                ;
            }

        }

        return false;
    }

    public static String readFile(String filePath) {
        return readFile(getFile(filePath));
    }

    public static String readFile(File file) {
        byte[] data = readFileToByte(file);
        return data == null?"":new String(data);
    }

    public static byte[] readFileToByte(File file) {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            byte[] var2 = IOUtil.inputStreamTobyte(fis);
            return var2;
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException var11) {
                    ;
                }
            }

        }

        return null;
    }

    public static boolean unZip(String zipfileName) {
        String f = zipfileName.substring(0, zipfileName.length() - 4);

        try {
            ZipEntry zipEntry;
            for(ZipInputStream zis = new ZipInputStream(new FileInputStream(zipfileName)); (zipEntry = zis.getNextEntry()) != null; zis.closeEntry()) {
                File file = new File(f, zipEntry.getName());
                if(!zipEntry.isDirectory()) {
                    File parent = file.getParentFile();
                    if(parent != null && !parent.exists()) {
                        parent.mkdirs();
                    }

                    FileOutputStream fileOut = new FileOutputStream(file);
                    byte[] buf = new byte[102400];

                    int readedBytes;
                    while((readedBytes = zis.read(buf)) > 0) {
                        fileOut.write(buf, 0, readedBytes);
                    }

                    fileOut.close();
                }
            }

            return true;
        } catch (Exception var9) {
            var9.printStackTrace();
            return false;
        }
    }
}
