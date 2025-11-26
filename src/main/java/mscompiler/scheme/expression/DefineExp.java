package mscompiler.scheme.expression;

// import mscompiler.env.scheme.SchemeEnv;
// import mscompiler.value.Value;

// public record DefineExp(String id, Expression exp) implements Expression {

// @Override
// public String toString() {
// return "(define " + id + " " + exp.toString() + ")";
// }

// @Override
// public Value interpret(SchemeEnv env) {
// Value value = exp.interpret(env);
// env.define(id, value);
// return value;
// }

// @Override
// public Void generateAsm() {
// // TODO Auto-generated method stub
// throw new UnsupportedOperationException("Unimplemented method
// 'generateAsm'");
// }

// }