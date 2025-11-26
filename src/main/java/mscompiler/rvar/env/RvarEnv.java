package mscompiler.rvar.env;

import java.util.List;

import mscompiler.lib.env.Env;
import mscompiler.lib.env.EnvBinding;

public class RvarEnv extends Env<String, Integer> {

    public RvarEnv(List<EnvBinding<String, Integer>> bindings) {
        super(bindings);
    }

    @Override
    public RvarEnv extend(List<EnvBinding<String, Integer>> bindings) {
        return super.extend(bindings).cast(RvarEnv.class);
    }

}
