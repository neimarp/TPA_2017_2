package hash;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class HashEngineDefault implements HashEngine {

    @Override
    public int hashCode(Object key) {
        return calcCodeHash(key);
    }

    protected int calcCodeHash(Object k) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(k);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] data = bos.toByteArray();

        long hash = 0;
        for (int i = 0; i < data.length; i++) {
            hash += ((long) (data[i]) * ((long) Math.pow(27, i)));
        }
        return Math.abs((int) hash);
    }

}
