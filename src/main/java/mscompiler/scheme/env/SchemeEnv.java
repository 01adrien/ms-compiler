package mscompiler.scheme.env;
// package mscompiler.env.scheme;

// import java.io.IOException;
// import java.io.InputStream;
// import java.nio.charset.StandardCharsets;
// import java.util.HashMap;
// import java.util.List;

// import mscompiler.env.common.Env;
// import mscompiler.env.common.EnvBinding;
// import mscompiler.lexer.common.Lexer;
// import mscompiler.lexer.scheme.SchemeLexerBuilder;
// import mscompiler.parser.scheme.MsParser;
// import mscompiler.value.Value;

// public class SchemeEnv extends Env<String, Value> {

// private HashMap<String, Value> env = new HashMap<>();

// public SchemeEnv(List<EnvBinding<String, Value>> bindings) {
// super(bindings);
// }

// public void define(String id, Value value) {
// env.put(id, value);
// }

// // public void loadMsFile(String resourceName) {
// // MsParser parser = new MsParser();
// // Lexer lexer = new SchemeLexerBuilder().build();

// // try (InputStream in = getClass().getResourceAsStream("/" + resourceName))
// {
// // if (in == null) {
// // throw new RuntimeException("Fichier non trouvé dans les ressources : " +
// // resourceName);
// // }

// // String content = new String(in.readAllBytes(), StandardCharsets.UTF_8);

// // List<Token> tokens = lexer.tokenize(content);
// // List<Expression> exps = parser.run(tokens);

// // for (Expression exp : exps) {
// // exp.interpret(this);
// // }

// // } catch (IOException e) {
// // throw new RuntimeException("Erreur lors de la lecture du fichier : " +
// // resourceName, e);
// // }
// // }

// }
