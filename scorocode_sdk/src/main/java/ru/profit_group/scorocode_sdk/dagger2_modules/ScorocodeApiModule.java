package ru.profit_group.scorocode_sdk.dagger2_modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.profit_group.scorocode_sdk.ScorocodeApi;
import ru.profit_group.scorocode_sdk.dagger2_scopes.SingletonScope;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

@Module
public class ScorocodeApiModule {
    @Provides
    @SingletonScope
    public ScorocodeApi scorocodeApi(Retrofit retrofit) {
        return retrofit.create(ScorocodeApi.class);
    }
}
