package Main.model;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by Maxim on 17.10.2016.
 */
public class ImageDataSet {
    String name;
    byte[] data;
    int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }
    public String getImageForShow(){
        byte[] encodeBase64 = Base64.encodeBase64(data);
        String base64Encoded = null;
        try {
            base64Encoded = new String(encodeBase64, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64Encoded;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
