package com.volmit.react.content.sampler;

import com.volmit.react.api.sampler.ReactCachedSampler;
import com.volmit.react.legacyutil.Form;

public class SamplerMemoryUsed extends ReactCachedSampler {
    public static final String ID = "memory-used";
    private final Runtime runtime;

    public SamplerMemoryUsed() {
        super(ID, 50);
        this.runtime = Runtime.getRuntime();
    }

    @Override
    public double onSample() {
        return runtime.totalMemory() - runtime.freeMemory();
    }

    @Override
    public String formattedValue(double t) {
        String[] s = Form.memSizeSplit((long) t, 1);
        if (s[1].equalsIgnoreCase("mb")) {
            return Form.memSizeSplit((long) t, 0)[0];
        } else {
            return s[0];
        }
    }

    @Override
    public String formattedSuffix(double t) {
        return Form.memSizeSplit((long) t, 1)[1];
    }
}