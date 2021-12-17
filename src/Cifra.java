import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Alunos: Akinn e Danilo

class Teste {
    public static void main(String[] args) {
        Cifra rot13 = new Cifra(13);

        System.out.println(rot13.cifrar("Ifsc"));
        System.out.println(rot13.cifrar("segurança da informação"));

        System.out.println(rot13.decifrar("Txpx ng doúqg"));
        System.out.println(rot13.decifrar("ôglcúngno"));
    }
}

public class Cifra {
    private final List<Character>
        alfabeto = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'),
        alfabetoRotacionado = new ArrayList<>();

    public Cifra(int rotacao) {
        incluirRotacaoNoAlfabeto(rotacao);
    }

    public String cifrar(String frase) {
        frase = Normalizer.normalize(frase, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        StringBuilder cifrada = new StringBuilder();
        for(char letra : frase.toCharArray()) {
            if(letra != ' ') {
                if(Character.isUpperCase(letra)) {
                    letra = Character.toLowerCase(letra);
                    letra = procurarArrays(alfabeto, alfabetoRotacionado, letra);
                    letra = Character.toUpperCase(letra);
                    cifrada.append(letra);
                } else {
                    letra = procurarArrays(alfabeto, alfabetoRotacionado, letra);
                    cifrada.append(letra);
                }
            } else {
                cifrada.append(letra);
            }
        }


        return cifrada.toString();
    }

    public String decifrar(String frase) {
        frase = Normalizer.normalize(frase, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        StringBuilder decifrada = new StringBuilder();
        for(char letra : frase.toCharArray()) {
            if(letra != ' ') {
                if(Character.isUpperCase(letra)) {
                    letra = Character.toLowerCase(letra);
                    letra = procurarArrays(alfabetoRotacionado, alfabeto, letra);
                    letra = Character.toUpperCase(letra);
                    decifrada.append(letra);
                } else {
                    letra = procurarArrays(alfabetoRotacionado, alfabeto, letra);
                    decifrada.append(letra);
                }
            } else {
                decifrada.append(letra);
            }
        }


        return decifrada.toString();
    }

    private void incluirRotacaoNoAlfabeto(int rotacao) {
        alfabetoRotacionado.addAll(alfabeto.subList(rotacao - 1, alfabeto.size()));
        alfabetoRotacionado.addAll(alfabeto.subList(0, rotacao - 1));
    }

    private char procurarArrays(List<Character> parametro, List<Character> procurar, char letra) {
        int index = parametro.indexOf(letra);
        return procurar.get(index);
    }
}