package mscompiler.scheme.env;
// package mscompiler.env.scheme;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// import mscompiler.value.NumberVal;
// import mscompiler.expression.scheme.Expression;
// import mscompiler.value.BooleanVal;
// import mscompiler.value.ConsVal;
// import mscompiler.value.NilVal;
// import mscompiler.value.PrimitiveVal;
// import mscompiler.value.Value;
// import mscompiler.value.ValueType;

// public class Core {

// public static SchemeEnv load() {
// List<SchemeEnvBinding> primitives = new ArrayList<>();

// primitives.add(new SchemeEnvBinding("+", new PrimitiveVal(addFunc())));
// primitives.add(new SchemeEnvBinding("-", new PrimitiveVal(subFunc())));
// primitives.add(new SchemeEnvBinding("*", new PrimitiveVal(mulFunc())));
// primitives.add(new SchemeEnvBinding("/", new PrimitiveVal(divFunc())));
// primitives.add(new SchemeEnvBinding("mod", new PrimitiveVal(modFunc())));

// primitives.add(new SchemeEnvBinding("and", new PrimitiveVal(andFunc())));
// primitives.add(new SchemeEnvBinding("or", new PrimitiveVal(orFunc())));
// primitives.add(new SchemeEnvBinding("not", new PrimitiveVal(notFunc())));

// primitives.add(new SchemeEnvBinding("<", new PrimitiveVal(ltFunc())));
// primitives.add(new SchemeEnvBinding("<=", new PrimitiveVal(leFunc())));
// primitives.add(new SchemeEnvBinding(">", new PrimitiveVal(gtFunc())));
// primitives.add(new SchemeEnvBinding(">=", new PrimitiveVal(geFunc())));
// primitives.add(new SchemeEnvBinding("=", new PrimitiveVal(numEqFunc())));
// primitives.add(new SchemeEnvBinding("eqv?", new PrimitiveVal(eqvFunc())));

// primitives.add(new SchemeEnvBinding("print", new PrimitiveVal(printFunc())));
// primitives.add(new SchemeEnvBinding("cons", new PrimitiveVal(consFunc())));
// primitives.add(new SchemeEnvBinding("car", new PrimitiveVal(carFunc())));
// primitives.add(new SchemeEnvBinding("cdr", new PrimitiveVal(cdrFunc())));

// primitives.add(new SchemeEnvBinding("number?", new
// PrimitiveVal(typePredicate(ValueType.NUMBER))));
// primitives.add(new SchemeEnvBinding("string?", new
// PrimitiveVal(typePredicate(ValueType.STRING))));
// primitives.add(new SchemeEnvBinding("boolean?", new
// PrimitiveVal(typePredicate(ValueType.BOOLEAN))));
// primitives.add(new SchemeEnvBinding("pair?", new
// PrimitiveVal(typePredicate(ValueType.CONS))));
// primitives.add(new SchemeEnvBinding("null?", new
// PrimitiveVal(typePredicate(ValueType.NIL))));
// primitives.add(new SchemeEnvBinding("closure?", new
// PrimitiveVal(typePredicate(ValueType.LAMBDA))));
// primitives.add(new SchemeEnvBinding("primitive?", new
// PrimitiveVal(typePredicate(ValueType.PRIMITIVE))));
// primitives.add(new SchemeEnvBinding("symbol?", new
// PrimitiveVal(typePredicate(ValueType.SYMBOL))));

// primitives.add(new SchemeEnvBinding("nil", NilVal.nil));

// SchemeEnv env = new SchemeEnv(primitives);

// env.loadMsFile("stdlib.ms");

// return env;
// }

// private static Primitive addFunc() {
// return (env, args) -> {

// requireMinArgs("+", args, 2);

// return new NumberVal(
// Arrays.stream(args)
// .mapToDouble(expr -> expr.interpret(Env).as(Double.class))
// .reduce(0, Double::sum));
// };
// }

// private static Primitive subFunc() {
// return (env, args) -> {

// requireMinArgs("-", args, 1);

// double[] nums = Arrays.stream(args)
// .mapToDouble(expr -> expr.interpret(Env).as(Double.class))
// .toArray();
// if (nums.length == 0)
// return new NumberVal(0);
// double result = Arrays.stream(nums, 1, nums.length)
// .reduce(nums[0], (a, b) -> a - b);
// return new NumberVal(result);
// };
// }

// private static Primitive mulFunc() {
// return (env, args) -> {

// requireMinArgs("*", args, 2);

// return new NumberVal(
// Arrays.stream(args)
// .mapToDouble(expr -> expr.interpret(Env).as(Double.class))
// .reduce(1, (a, b) -> a * b));
// };
// }

// private static Primitive divFunc() {
// return (env, args) -> {

// requireMinArgs("/", args, 2);

// double[] nums = Arrays.stream(args)
// .mapToDouble(expr -> expr.interpret(Env).as(Double.class))
// .toArray();
// if (nums.length == 0)
// return new NumberVal(1);
// double result = Arrays.stream(nums, 1, nums.length)
// .reduce(nums[0], (a, b) -> a / b);
// return new NumberVal(result);
// };
// }

// private static Primitive modFunc() {
// return (env, args) -> {

// requireArgsCount("mod", args, 2);

// Double a = args[0].interpret(env).as(Double.class);
// Double b = args[1].interpret(env).as(Double.class);

// return new NumberVal(a % b);

// };
// }

// private static Primitive andFunc() {
// return (env, args) -> {

// requireMinArgs("and", args, 2);

// return new BooleanVal(
// Arrays.stream(args)
// .map(expr -> expr.interpret(Env).as(Boolean.class))
// .reduce(true, (a, b) -> a && b));
// };
// }

// private static Primitive orFunc() {
// return (env, args) -> {

// requireMinArgs("or", args, 2);

// return new BooleanVal(
// Arrays.stream(args)
// .map(expr -> expr.interpret(Env).as(Boolean.class))
// .reduce(false, (a, b) -> a || b));
// };
// }

// private static Primitive notFunc() {
// return (env, args) -> {

// requireArgsCount("not", args, 1);

// return new BooleanVal(
// !args[0].interpret(env).as(Boolean.class));
// };
// }

// private static Primitive ltFunc() {
// return (env, args) -> {

// requireMinArgs("<", args, 2);

// double[] nums = Arrays.stream(args)
// .mapToDouble(expr -> expr.interpret(Env).as(Double.class))
// .toArray();

// for (int i = 0; i < nums.length - 1; i++)
// if (!(nums[i] < nums[i + 1]))
// return new BooleanVal(false);
// return new BooleanVal(true);
// };
// }

// private static Primitive leFunc() {
// return (env, args) -> {

// requireMinArgs("<=", args, 2);

// double[] nums = Arrays.stream(args)
// .mapToDouble(expr -> expr.interpret(Env).as(Double.class))
// .toArray();

// for (int i = 0; i < nums.length - 1; i++)
// if (!(nums[i] <= nums[i + 1]))
// return new BooleanVal(false);
// return new BooleanVal(true);
// };
// }

// private static Primitive gtFunc() {
// return (env, args) -> {

// requireMinArgs(">", args, 2);

// double[] nums = Arrays.stream(args)
// .mapToDouble(expr -> expr.interpret(Env).as(Double.class))
// .toArray();
// for (int i = 0; i < nums.length - 1; i++)
// if (!(nums[i] > nums[i + 1]))
// return new BooleanVal(false);
// return new BooleanVal(true);
// };
// }

// private static Primitive geFunc() {
// return (env, args) -> {

// requireMinArgs(">=", args, 2);

// double[] nums = Arrays.stream(args)
// .mapToDouble(expr -> expr.interpret(Env).as(Double.class))
// .toArray();
// for (int i = 0; i < nums.length - 1; i++)
// if (!(nums[i] >= nums[i + 1]))
// return new BooleanVal(false);
// return new BooleanVal(true);
// };
// }

// private static Primitive numEqFunc() {
// return (env, args) -> {

// requireMinArgs("=", args, 2);

// Value[] values = Arrays.stream(args)
// .map(expr -> expr.interpret(Env))
// .toArray(Value[]::new);

// for (int i = 0; i < values.length - 1; i++) {
// if (!values[i].eq(values[i + 1])) {
// return new BooleanVal(false);
// }
// }
// return new BooleanVal(true);
// };
// }

// private static Primitive eqvFunc() {
// return (env, args) -> {

// requireArgsCount("eqv", args, 2);

// Value a = args[0].interpret(env);
// Value b = args[1].interpret(env);

// return new BooleanVal(a.eq(b));
// };
// }

// private static Primitive printFunc() {
// return (env, args) -> {
// for (Expression expr : args) {
// Value v = expr.interpret(env);
// System.out.print(v.asString());
// }
// System.out.println();
// return NilVal.nil;
// };
// }

// private static Primitive consFunc() {
// return (env, args) -> {

// requireArgsCount("cons", args, 2);

// Value car = args[0].interpret(env);
// Value cdr = args[1].interpret(env);

// boolean isList = cdr.type() == ValueType.CONS || cdr.type() == ValueType.NIL;

// return new ConsVal(car, isList ? cdr : new ConsVal(cdr, NilVal.nil));
// };
// }

// private static Primitive carFunc() {
// return (env, args) -> {

// requireArgsCount("car", args, 1);

// return ((ConsVal) args[0].interpret(env)).car();
// };
// }

// private static Primitive cdrFunc() {
// return (env, args) -> {

// requireArgsCount("cdr", args, 1);

// return ((ConsVal) args[0].interpret(env)).cdr();
// };
// }

// private static Primitive typePredicate(ValueType type) {
// return (env, args) -> {

// requireArgsCount("predicate", args, 1);

// return new BooleanVal(args[0].interpret(env).type() == type);
// };
// }

// private static void requireArgsCount(String name, Expression[] args, int
// expected) {
// if (args.length != expected) {
// throw new RuntimeException(name + " expects exactly " + expected + "
// arguments, got " + args.length);
// }
// }

// private static void requireMinArgs(String name, Expression[] args, int min) {
// if (args.length < min) {
// throw new RuntimeException(name + " expects at least " + min + " arguments,
// got " + args.length);
// }
// }
// }
