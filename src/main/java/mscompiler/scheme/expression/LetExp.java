package mscompiler.scheme.expression;

import java.util.List;
import java.util.stream.Collectors;

import mscompiler.lib.expression.LetBinding;
import mscompiler.scheme.env.SchemeEnvBinding;
import mscompiler.scheme.value.Value;

// public record LetExp(List<LetBinding> bindings, Expression exp) implements
// Expression {

// public LetExp(List<LetBinding> bindings, Expression exp) {
// this.bindings = List.copyOf(bindings);
// this.exp = exp;
// }

// @Override
// public String toString() {
// String bindingsStr = bindings.stream()
// .map(LetBinding::toString)
// .collect(Collectors.joining(" "));
// return "(let (" + bindingsStr + ") " + exp + ")";
// }

// @Override
// public Value interpret(SchemeEnv env) {
// List<SchemeEnvBinding> newBindings = bindings
// .stream()
// .map((b) -> new SchemeEnvBinding(b.id(), b.exp().interpret(env)))
// .toList();
// return exp.interpret(env.extend(newBindings));
// }

// @Override
// public Void generateAsm() {
// // TODO Auto-generated method stub
// throw new UnsupportedOperationException("Unimplemented method
// 'generateAsm'");
// }

// }
