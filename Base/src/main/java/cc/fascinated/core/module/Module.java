package cc.fascinated.core.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public abstract class Module {

    private final String name;
    @Setter private String path;
    private final ModulePriority priority;

    public abstract void load();
    public abstract void unload();
}
