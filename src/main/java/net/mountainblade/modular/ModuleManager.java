/**
 * Copyright (C) 2014 MountainBlade (http://mountainblade.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.mountainblade.modular;

import com.google.common.base.Optional;
import net.mountainblade.modular.impl.ModuleRegistry;

import java.io.File;
import java.net.URI;
import java.util.Collection;

/**
 * Represents a module manager.
 *
 * @author spaceemotion
 * @version 1.0
 */
public interface ModuleManager extends Module {

    /**
     * Simply provides a module instance and stores it in the registry.
     *
     * @param module the module
     * @see #provide(Module)
     */
    <T extends Module> T provideSimple(T module);

    /**
     * Stored the given module instance in the registry, but also executes all injection magic and calls the usual
     * methods.
     *
     * @param module the module
     * @see #provideSimple(Module)
     */
    <T extends Module> T provide(T module);

    /**
     * Load modules from a URI.
     *
     * @param uri        The URI to load the modules from
     * @param filters    An array of {@link net.mountainblade.modular.Filter filters} to use
     * @return The collection of all successfully loaded modules.
     */
    Collection<Module> loadModules(URI uri, Filter... filters);

    // anything, except classpath
    Collection<Module> loadModules(File file, Filter... filters);

    // package names
    Collection<Module> loadModules(String resource, Filter... filters);

    // specific class
    <M extends Module> M loadModule(Class<M> moduleClass, Filter... filters);

    /**
     * Gets a specific module by its class.
     *
     * @param module    The module class
     * @return An optional for the module instance
     */
    <M extends Module> Optional<M> getModule(Class<M> module);

    /**
     * Gets information about a specific module.
     *
     * @param module    The module class
     * @return An optional for the information
     */
    Optional<ModuleInformation> getInformation(Class<? extends Module> module);

    /**
     * Gets the registry containing all registered (loaded) Modules.
     *
     * @return The underlying module registry
     */
    ModuleRegistry getRegistry();

    /**
     * Tells the manager to shut down and destroy all loaded modules.
     * <br>
     * This can be useful for cases in which you want to have a clear, fresh start.
     */
    void shutdown();

}
