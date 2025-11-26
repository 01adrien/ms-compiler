package mscompiler.scheme.expression;

// import mscompiler.env.scheme.SchemeEnv;
// import mscompiler.value.BooleanVal;
// import mscompiler.value.Value;

// public record BooleanExp(boolean value) implements Expression {

// public BooleanExp(String str) {
// this(parseBoolean(str));
// }

// private static boolean parseBoolean(String str) {
// return switch (str) {
// case "true", "#t" -> true;
// case "false", "#f" -> false;
// default -> throw new RuntimeException("Invalid boolean string: " + str);
// };
// }

// @Override
// public String toString() {
// return Boolean.toString(value);
// }

// @Override
// public Value interpret(SchemeEnv env) {
// return new BooleanVal(value);
// }

// @Override
// public Void generateAsm() {
// // TODO Auto-generated method stub
// throw new UnsupportedOperationException("Unimplemented method
// 'generateAsm'");
// }

// }
