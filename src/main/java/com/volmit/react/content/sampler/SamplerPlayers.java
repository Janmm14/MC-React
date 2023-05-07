package com.volmit.react.content.sampler;

import com.volmit.react.React;
import com.volmit.react.api.sampler.ReactCachedSampler;
import com.volmit.react.util.Form;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class SamplerPlayers extends ReactCachedSampler implements Listener {
    public static final String ID = "players";

    public SamplerPlayers() {
        super(ID, 250);
    }

    @Override
    public void start() {
        React.instance.registerListener(this);
    }

    @Override
    public void stop() {
        React.instance.unregisterListener(this);
    }

    @Override
    public double onSample() {
        return Bukkit.getServer().getOnlinePlayers().size();
    }

    @Override
    public String formattedValue(double t) {
        return Form.f(Math.round(t));
    }

    @Override
    public String formattedSuffix(double t) {
        return "PLR";
    }
}
