package mscompiler.rvar.env;

import java.util.List;

import mscompiler.lib.env.Env;
import mscompiler.lib.env.EnvBinding;
import mscompiler.rvar.value.RvarVal;

public class RvarEnv extends Env<RvarVal> {

    public RvarEnv(List<EnvBinding<RvarVal>> bindings) {
        super(bindings);
    }

    @Override
    public RvarEnv extend(List<EnvBinding<RvarVal>> bindings) {
        return super.extend(bindings).cast(RvarEnv.class);
    }

}
