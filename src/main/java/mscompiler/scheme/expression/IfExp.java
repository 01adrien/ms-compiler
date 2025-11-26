package mscompiler.scheme.expression;

import mscompiler.scheme.value.Value;

// public record IfExp(Expression condition, Expression consequent, Expression
// alternative) implements Expression {

// @Override
// public String toString() {
// return String.format("(if %s %s %s)", condition, consequent, alternative);
// }

// @Override
// public Value interpret(SchemeEnv env) {
// return condition.interpret(env).as(Boolean.class)
// ? consequent.interpret(env)
// : alternative.interpret(env);
// }

// @Override
// public Void generateAsm() {
// // TODO Auto-generated method stub
// throw new UnsupportedOperationException("Unimplemented method
// 'generateAsm'");
// }

// }