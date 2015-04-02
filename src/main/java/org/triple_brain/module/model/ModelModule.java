/*
 * Copyright Vincent Blouin under the Mozilla Public License 1.1
 */

package org.triple_brain.module.model;

import com.google.inject.AbstractModule;

import javax.inject.Singleton;

public class ModelModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(UserNameGenerator.class).to(UserNameGeneratorImpl.class).in(Singleton.class);
    }

}