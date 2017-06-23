package com.sampleapp.di

class DaggerUtils {

    companion object{

        @Suppress("UNCHECKED_CAST")
        fun <T> createComponent(componentClass: Class<T>, vararg dependencies: Any): T {
            val fqn = componentClass.name

            val packageName = componentClass.`package`.name
            // Accounts for inner classes, ie MyApplication$Component
            val simpleName = fqn.substring(packageName.length + 1)
            val generatedName = (packageName + ".Dagger" + simpleName).replace('$', '_')

            try {
                val generatedClass = Class.forName(generatedName)
                val builder = generatedClass.getMethod("builder").invoke(null)

                for (method in builder.javaClass.declaredMethods) {
                    val params = method.parameterTypes
                    if (params.size == 1) {
                        val dependencyClass = params[0]
                        for (dependency in dependencies) {
                            if (dependencyClass.isAssignableFrom(dependency.javaClass)) {
                                method.invoke(builder, dependency)
                                break
                            }
                        }
                    }
                }
                // noinspection
                return builder.javaClass.getMethod("build").invoke(builder) as T
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        }
    }
}