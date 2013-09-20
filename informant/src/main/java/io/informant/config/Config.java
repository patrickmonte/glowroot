/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.informant.config;

import java.util.List;

import checkers.igj.quals.Immutable;
import checkers.igj.quals.ReadOnly;
import com.google.common.collect.ImmutableList;

/**
 * @author Trask Stalnaker
 * @since 0.5
 */
@Immutable
class Config {

    private final GeneralConfig generalConfig;
    private final CoarseProfilingConfig coarseProfilingConfig;
    private final FineProfilingConfig fineProfilingConfig;
    private final UserOverridesConfig userOverridesConfig;
    private final StorageConfig storageConfig;
    private final ImmutableList<PluginConfig> pluginConfigs;
    private final ImmutableList<PointcutConfig> adhocPointcutConfigs;

    static Config getDefault(@ReadOnly List<PluginDescriptor> pluginDescriptors) {
        return new Config(GeneralConfig.getDefault(), CoarseProfilingConfig.getDefault(),
                FineProfilingConfig.getDefault(), UserOverridesConfig.getDefault(),
                StorageConfig.getDefault(), createPluginConfigs(pluginDescriptors),
                ImmutableList.<PointcutConfig>of());
    }

    static Builder builder(Config base) {
        return new Builder(base);
    }

    Config(GeneralConfig generalConfig, CoarseProfilingConfig coarseProfilingConfig,
            FineProfilingConfig fineProfilingConfig, UserOverridesConfig userOverridesConfig,
            StorageConfig storageConfig, ImmutableList<PluginConfig> pluginConfigs,
            ImmutableList<PointcutConfig> adhocPointcutConfigs) {
        this.generalConfig = generalConfig;
        this.coarseProfilingConfig = coarseProfilingConfig;
        this.fineProfilingConfig = fineProfilingConfig;
        this.userOverridesConfig = userOverridesConfig;
        this.storageConfig = storageConfig;
        this.pluginConfigs = pluginConfigs;
        this.adhocPointcutConfigs = adhocPointcutConfigs;
    }

    GeneralConfig getGeneralConfig() {
        return generalConfig;
    }

    CoarseProfilingConfig getCoarseProfilingConfig() {
        return coarseProfilingConfig;
    }

    FineProfilingConfig getFineProfilingConfig() {
        return fineProfilingConfig;
    }

    UserOverridesConfig getUserOverridesConfig() {
        return userOverridesConfig;
    }

    StorageConfig getStorageConfig() {
        return storageConfig;
    }

    ImmutableList<PluginConfig> getPluginConfigs() {
        return pluginConfigs;
    }

    ImmutableList<PointcutConfig> getAdhocPointcutConfigs() {
        return adhocPointcutConfigs;
    }

    private static ImmutableList<PluginConfig> createPluginConfigs(
            @ReadOnly List<PluginDescriptor> pluginDescriptors) {
        ImmutableList.Builder<PluginConfig> pluginConfigs = ImmutableList.builder();
        for (PluginDescriptor pluginDescriptor : pluginDescriptors) {
            pluginConfigs.add(PluginConfig.getDefault(pluginDescriptor));
        }
        return pluginConfigs.build();
    }

    static class Builder {

        private GeneralConfig generalConfig;
        private CoarseProfilingConfig coarseProfilingConfig;
        private FineProfilingConfig fineProfilingConfig;
        private UserOverridesConfig userOverridesConfig;
        private StorageConfig storageConfig;
        private ImmutableList<PluginConfig> pluginConfigs;
        private ImmutableList<PointcutConfig> adhocPointcutConfigs;

        private Builder(Config base) {
            generalConfig = base.generalConfig;
            coarseProfilingConfig = base.coarseProfilingConfig;
            fineProfilingConfig = base.fineProfilingConfig;
            userOverridesConfig = base.userOverridesConfig;
            storageConfig = base.storageConfig;
            pluginConfigs = base.pluginConfigs;
            adhocPointcutConfigs = base.adhocPointcutConfigs;
        }
        Builder generalConfig(GeneralConfig generalConfig) {
            this.generalConfig = generalConfig;
            return this;
        }
        Builder coarseProfilingConfig(CoarseProfilingConfig coarseProfilingConfig) {
            this.coarseProfilingConfig = coarseProfilingConfig;
            return this;
        }
        Builder fineProfilingConfig(FineProfilingConfig fineProfilingConfig) {
            this.fineProfilingConfig = fineProfilingConfig;
            return this;
        }
        Builder userOverridesConfig(UserOverridesConfig userOverridesConfig) {
            this.userOverridesConfig = userOverridesConfig;
            return this;
        }
        Builder storageConfig(StorageConfig storageConfig) {
            this.storageConfig = storageConfig;
            return this;
        }
        Builder pluginConfigs(ImmutableList<PluginConfig> pluginConfigs) {
            this.pluginConfigs = pluginConfigs;
            return this;
        }
        Builder adhocPointcutConfigs(ImmutableList<PointcutConfig> adhocPointcutConfigs) {
            this.adhocPointcutConfigs = adhocPointcutConfigs;
            return this;
        }
        Config build() {
            return new Config(generalConfig, coarseProfilingConfig, fineProfilingConfig,
                    userOverridesConfig, storageConfig, pluginConfigs, adhocPointcutConfigs);
        }
    }
}
