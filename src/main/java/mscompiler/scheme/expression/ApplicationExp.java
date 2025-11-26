package mscompiler.scheme.expression;

import java.util.List;
import java.util.stream.Collectors;

// import mscompiler.env.scheme.SchemeEnv;
// import mscompiler.env.scheme.SchemeEnvBinding;
// import mscompiler.utils.ListUtils;
// import mscompiler.value.LambdaVal;
// import mscompiler.value.PrimitiveVal;
// import mscompiler.value.Value;

// public record ApplicationExp(Expression function, List<Expression> args)
// implements Expression {

// public ApplicationExp(Expression function, List<Expression> args) {
// this.function = function;
// this.args = List.copyOf(args);
// }

// @Override
// public String toString() {
// String argsStr = args
// .stream()
// .map(Object::toString)
// .collect(Collectors.joining(" "));

// return "(" + function + (argsStr.isEmpty() ? "" : " " + argsStr) + ")";
// }

// @Override
// public Value interpret(SchemeEnv env) {
// Value fn = function.interpret(env);
// if (fn instanceof LambdaVal lambdaFn) {
// List<SchemeEnvBinding> bindings = ListUtils.zip(
// lambdaFn.params(),
// args,
// (param, arg) -> new SchemeEnvBinding(param, arg.interpret(Env)));
// SchemeEnv e = lambdaFn.env().extend(bindings);
// return lambdaFn.body().interpret(e);
// } else if (fn instanceof PrimitiveVal primFn) {
// return primFn.value().apply(env, args.toArray(new Expression[0]));
// } else {
// throw new RuntimeException("Not a function: " + fn);
// }
// }

// @Override
// public Void generateAsm() {
// // TODO Auto-generated method stub
// throw new UnsupportedOperationException("Unimplemented method
// 'generateAsm'");
// }

// }
