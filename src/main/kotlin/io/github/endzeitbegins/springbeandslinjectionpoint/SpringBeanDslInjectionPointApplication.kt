package io.github.endzeitbegins.springbeandslinjectionpoint

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.beans.factory.InjectionPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.context.support.BeanDefinitionDsl.Scope.PROTOTYPE
import org.springframework.context.support.beans

@SpringBootApplication
class SpringBeanDslInjectionPointApplication

fun main(args: Array<String>) {
    runApplication<SpringBeanDslInjectionPointApplication>(*args) {

        addInitializers(
            beans {
                bean(scope = PROTOTYPE) {
                    val injectionPoint = ref<InjectionPoint>()

                    LoggerFactory.getLogger(
                        injectionPoint.methodParameter?.containingClass // constructor
                            ?: injectionPoint.field?.declaringClass // or field injection
                    )
                }
                bean<MyService>()
            }
        )

    }
}

open class MyService(val logger: Logger) {
    init {
        logger.error("Wo-hoo!")
    }
}