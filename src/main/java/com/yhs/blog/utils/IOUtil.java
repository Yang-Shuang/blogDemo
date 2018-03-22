package com.yhs.blog.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by YangShuang
 * on 2018/3/22.
 */
public class IOUtil {
    public IOUtil() {
    }

    public static String inputStreamToString(InputStream is) {
        if(is == null) {
            return null;
        } else {
            byte[] data = inputStreamTobyte(is);
            return data == null?"":new String(data);
        }
    }

    public static byte[] inputStreamTobyte(InputStream is) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        boolean b = inputStreamToOutputStream(is, bos);
        return b?bos.toByteArray():null;
    }

    public static boolean inputStreamToOutputStream(InputStream is, OutputStream os) {
        try {
            if(is != null && os != null) {
                byte[] b = new byte[102400];
                boolean var3 = false;

                int i;
                while((i = is.read(b)) != -1) {
                    os.write(b, 0, i);
                }

                boolean var4 = true;
                return var4;
            }
        } catch (IOException var15) {
            var15.printStackTrace();
        } finally {
            try {
                os.flush();
                is.close();
                os.close();
            } catch (IOException var14) {
                var14.printStackTrace();
            }

        }

        return false;
    }
}
