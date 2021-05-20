package client;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class MapTest {

    @Test
    public void mapTest() {
        var dados = new HashMap<Integer,Integer>();
        dados.put(1,100);
        dados.put(2,200);
        dados.put(3,300);

        System.out.println(dados);

        dados.computeIfPresent(4,(k, v) -> v + 1);

        System.out.println(dados);
    }

}
