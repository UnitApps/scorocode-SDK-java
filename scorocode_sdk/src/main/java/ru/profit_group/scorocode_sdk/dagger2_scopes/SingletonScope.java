package ru.profit_group.scorocode_sdk.dagger2_scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface SingletonScope {}
