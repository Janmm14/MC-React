package com.volmit.react.content.sampler;

import com.volmit.react.React;
import com.volmit.react.api.sampler.ReactCachedSampler;
import com.volmit.react.legacyutil.Form;
import com.volmit.react.legacyutil.ThreadUtilizationMonitor;
import net.kyori.adventure.text.Component;

public class SamplerTickTime extends ReactCachedSampler {
    public static final String ID = "tick-time";
    private ThreadUtilizationMonitor monitor;

    public SamplerTickTime() {
        super(ID, 50);
    }

    @Override
    public void start() {
        super.start();
        monitor = new ThreadUtilizationMonitor(React.serverThread, 100);
        monitor.start();
    }

    @Override
    public void stop() {
        monitor.interrupt();
        super.stop();
    }

    @Override
    public double onSample() {
        return monitor.getAverage();
    }

    @Override
    public String format(double t) {
        return formattedValue(t) + formattedSuffix(t);
    }

    @Override
    public Component format(Component value, Component suffix) {
        return Component.empty().append(value).append(suffix);
    }

    @Override
    public String formattedValue(double t) {
        return Form.durationSplit(t, 0)[0];
    }

    @Override
    public String formattedSuffix(double t) {
        return Form.durationSplit(t, 0)[1];
    }
}