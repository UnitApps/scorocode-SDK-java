package ru.profit_group.scorocode_sdk.dagger2_components;

import dagger.Component;
import ru.profit_group.scorocode_sdk.ScorocodeApi;
import ru.profit_group.scorocode_sdk.dagger2_modules.RetrofitModule;
import ru.profit_group.scorocode_sdk.dagger2_modules.ScorocodeApiModule;
import ru.profit_group.scorocode_sdk.dagger2_scopes.SingletonScope;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

@SingletonScope
@Component(modules = {RetrofitModule.class, ScorocodeApiModule.class})
public interface ScorocodeApiComponent {
    ScorocodeApi getScorocodeApi();
}
