package mscompiler.env;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import mscompiler.expression.Expression;
import mscompiler.lexer.Lexer;
import mscompiler.lexer.MsLexerBuilder;
import mscompiler.lexer.Token;
import mscompiler.parser.MsParser;
import mscompiler.value.Value;

public class Env implements Cloneable {

    private HashMap<String, Value> env = new HashMap<>();

    public Env(List<EnvBinding> bindings) {
        if (bindings != null) {
            bindings.stream().forEach((b) -> env.put(b.id(), b.val()));
        }
    }

    public Value lookup(String name) {

        if (!env.containsKey(name)) {
            throw new RuntimeException("Unboud symbol : " + name);
        }
        return env.get(name);
    }

    public boolean isPresent(String id) {
        return env.containsKey(id);
    }

    public void define(String id, Value value) {
        env.put(id, value);
    }

    public Env extend(List<EnvBinding> bindings) {
        Env extended = this.clone();
        extended.env.putAll((new Env(bindings)).env);
        return extended;
    }

    public void print() {
        env.forEach((k, v) -> System.out.println(k + " " + v));
    }

    @Override
    public Env clone() {
        Env copy = new Env(null);
        copy.env.putAll(this.env);
        return copy;
    }

    @Override
    public String toString() {
        return String.join("\n", env.keySet());
    }

    public void loadMsFile(String resourceName) {
        MsParser parser = new MsParser();
        Lexer lexer = new MsLexerBuilder().build();

        try (InputStream in = getClass().getResourceAsStream("/" + resourceName)) {
            if (in == null) {
                throw new RuntimeException("Fichier non trouvé dans les ressources : " + resourceName);
            }

            String content = new String(in.readAllBytes(), StandardCharsets.UTF_8);

            List<Token> tokens = lexer.tokenize(content);
            List<Expression> exps = parser.run(tokens);

            for (Expression exp : exps) {
                exp.interpret(this);
            }

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier : " + resourceName, e);
        }
    }

}
